package com.plateplan.recipes;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

/**
 * Servlet implementation class ingredientServletSearch
 */
@WebServlet("/ingredientss")
public class ingredientServletSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MealDBService apicall = new MealDBService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("ingredients servlet");

		String ingredient = request.getParameter("ingredients");
		if (ingredient == null || ingredient.trim().isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ingredient parameter is required");
			return;
		}

		List<String> ingredients = new ArrayList<>();
		ingredients.add(ingredient);

		try {
			List<Recipe> recipes = apicall.searchRecipes(ingredients);
			HttpSession session = request.getSession();

			if (recipes != null && !recipes.isEmpty()) {
				session.setAttribute("recipe", recipes.get(0).getMealDbId());
				session.setAttribute("size", recipes.size());
				session.setAttribute("recipes", recipes);

				// Add nutritional information to session for easy access in JSP
				session.setAttribute("calories", recipes.get(0).getCalories());
				session.setAttribute("protein", recipes.get(0).getProtein());
				session.setAttribute("carbs", recipes.get(0).getCarbs());
				session.setAttribute("fat", recipes.get(0).getFat());
				session.setAttribute("ingredients", recipes.get(0).getIngredients());
			} else {
				session.setAttribute("recipes", null);
			}

			response.sendRedirect("/PlatePlan/userhomepage.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Error processing recipe search: " + e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}