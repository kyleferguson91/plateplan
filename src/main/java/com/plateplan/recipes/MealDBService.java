
package com.plateplan.recipes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
//import com.google.gson.Gson;
//import com.google.gson.annotations.SerializedName;

public class MealDBService {
    private static final String API_BASE_URL = "https://www.themealdb.com/api/json/v1/1";

    /**
     * Searches for recipes by a list of ingredients. Only recipes containing all
     * specified ingredients will be returned.
     *
     * @param ingredients List of ingredients to filter recipes by.
     * @return List of Recipe objects matching the specified ingredients.
     * @throws Exception if there is an issue with the API request or JSON parsing.
     */
    public List<Recipe> searchRecipes(List<String> ingredients) throws Exception {
        if (ingredients == null || ingredients.isEmpty()) {
            throw new IllegalArgumentException("Ingredients list cannot be null or empty.");
        }

        // Construct query URL with first ingredient for MVP
        String ingredient = URLEncoder.encode(ingredients.get(0), "UTF-8");
        String urlStr = API_BASE_URL + "/filter.php?i=" + ingredient;

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Check if request was successful
        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new Exception("Failed to connect to API: HTTP error code " + conn.getResponseCode());
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return parseRecipesJson(response.toString());
        } finally {
            conn.disconnect();
        }
    }


    /**
     * Parses a JSON response from TheMealDB API to create a list of Recipe objects.
     *
     * @param json The JSON response as a String.
     * @return List of Recipe objects parsed from the JSON.
     */
    private List<Recipe> parseRecipesJson(String json) {
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
