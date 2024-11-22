package com.plateplan.recipes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class MealDBService {
    private static final String API_BASE_URL = "https://www.themealdb.com/api/json/v1/1";

    public List<Recipe> searchRecipes(List<String> ingredients) throws Exception {
        if (ingredients == null || ingredients.isEmpty()) {
            throw new IllegalArgumentException("Ingredients list cannot be null or empty.");
        }

        String ingredient = URLEncoder.encode(ingredients.get(0), "UTF-8");
        String urlStr = API_BASE_URL + "/filter.php?i=" + ingredient;

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new Exception("Failed to connect to API: HTTP error code " + conn.getResponseCode());
        }

        List<Recipe> basicRecipes;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            basicRecipes = parseRecipesJson(response.toString());
         
        } finally {
            conn.disconnect();
        }

        // Enhance recipes with detailed information
        List<Recipe> detailedRecipes = new ArrayList<>();
        for (Recipe basicRecipe : basicRecipes) {
            try {
                Recipe detailedRecipe = getRecipeDetails(basicRecipe.getMealDbId());
                detailedRecipes.add(detailedRecipe);
            } catch (Exception e) {
                e.printStackTrace();
                // If we fail to get details, add the basic recipe
                detailedRecipes.add(basicRecipe);
            }
        }

        return detailedRecipes;
          
    }


    public Recipe getRecipeDetails(String mealId) throws Exception {
        String urlStr = API_BASE_URL + "/lookup.php?i=" + mealId;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new Exception("Failed to get recipe details: HTTP error code " + conn.getResponseCode());
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return parseRecipeDetails(response.toString());
        } finally {
            conn.disconnect();
        }
    }

    private Recipe parseRecipeDetails(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray mealsArray = jsonObject.getAsJsonArray("meals");

        if (mealsArray == null || mealsArray.size() == 0) {
            return null;
        }

        JsonObject mealObject = mealsArray.get(0).getAsJsonObject();
        Recipe.RecipeBuilder builder = new Recipe.RecipeBuilder()
                .mealDbId(getJsonString(mealObject, "idMeal"))
                .name(getJsonString(mealObject, "strMeal"))
                .thumbnailUrl(getJsonString(mealObject, "strMealThumb"));

        // Parse ingredients and measurements
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            String ingredientName = getJsonString(mealObject, "strIngredient" + i);
            String measure = getJsonString(mealObject, "strMeasure" + i);

            if (ingredientName != null && !ingredientName.trim().isEmpty()) {
                Ingredient ingredient = new Ingredient.IngredientBuilder()
                        .name(ingredientName)
                        .quantity(parseQuantity(measure))
                        .unit(parseUnit(measure))
                        .build();
                builder.addIngredient(ingredient);
                ingredients.add(ingredient);
            }
        }

        // Calculate nutritional information based on ingredients
        NutritionalInfo nutritionalInfo = calculateNutritionalInfo(ingredients);
        builder.calories(nutritionalInfo.calories)
                .protein(nutritionalInfo.protein)
                .carbs(nutritionalInfo.carbs)
                .fat(nutritionalInfo.fat);

        return builder.build();
    }

    private String getJsonString(JsonObject json, String key) {
        JsonElement element = json.get(key);
        return element != null && !element.isJsonNull() ? element.getAsString() : null;
    }

    // Helper class for nutritional calculations
    private static class NutritionalInfo {
        int calories, protein, carbs, fat;
    }

    private NutritionalInfo calculateNutritionalInfo(List<Ingredient> ingredients) {
        // This is a simplified calculation. In a real application, you would want to:
        // 1. Have a database of nutritional information for ingredients
        // 2. Consider portion sizes
        // 3. Account for cooking methods
        NutritionalInfo info = new NutritionalInfo();

        // Dummy calculations for example
        for (Ingredient ingredient : ingredients) {
            // These would be looked up from a nutrition database in a real application
            info.calories += 100; // Example value
            info.protein += 5; // Example value
            info.carbs += 10; // Example value
            info.fat += 3; // Example value
        }

        return info;
    }

    private double parseQuantity(String measure) {
        if (measure == null || measure.trim().isEmpty()) {
            return 0.0;
        }

        try {
            // Extract numeric value from measure string
            String numericPart = measure.replaceAll("[^0-9.]", "");
            return numericPart.isEmpty() ? 0.0 : Double.parseDouble(numericPart);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private String parseUnit(String measure) {
        if (measure == null || measure.trim().isEmpty()) {
            return "";
        }

        // Extract unit part (non-numeric part) from measure string
        return measure.replaceAll("[0-9.]", "").trim();
    }
    
    /**
     * Parses a JSON response from TheMealDB API to create a list of Recipe objects.
     *
     * @param json The JSON response as a String.
     * @return List of Recipe objects parsed from the JSON.
     */
    public List<Recipe> parseRecipesJson(String json) {
        Gson gson = new Gson();
        
        MealDBResponse response = gson.fromJson(json, MealDBResponse.class);

        if (response.meals == null) {
            return new ArrayList<>(); // Return an empty list if there are no meals
        }

        return response.meals;
    }
    /**
     * Inner class to represent the structure of the JSON response from the MealDB API.
     */
    private static class MealDBResponse {
        @SerializedName("meals")
        private List<Recipe> meals;
    }
}