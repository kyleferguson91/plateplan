package com.plateplan.recipes;

import com.google.gson.Gson;
import com.plateplan.recipes.MealDBService;
import com.plateplan.recipes.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet("/api/recipes/search")
public class MealDBController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final MealDBService mealDBService = new MealDBService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get ingredients from query parameters
        String ingredientsParam = request.getParameter("ingredients");
        if (ingredientsParam == null || ingredientsParam.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ingredients parameter is required.");
            return;
        }

        List<String> ingredients = Arrays.asList(ingredientsParam.split(","));
        ingredients.replaceAll(String::trim); // Trim each ingredient

        try {
            // Fetch recipes
            List<Recipe> recipes = mealDBService.searchRecipes(ingredients);

            // Convert the list of recipes to JSON
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(recipes);

            // Send the JSON response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(jsonResponse);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching recipes.");
        }
    }
}
