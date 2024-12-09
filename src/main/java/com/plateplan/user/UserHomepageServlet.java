package com.plateplan.user;

import com.plateplan.recipes.Recipe;
import com.plateplan.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userhomepage")
public class UserHomepageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user from session
        User user = (User) request.getSession().getAttribute("user");
        List<Recipe> recipes = (List<Recipe>) request.getSession().getAttribute("recipes");

        // Handle selected recipe
        String recipeId = request.getParameter("recipeid");
        Recipe selectedRecipe = null;
        if (recipeId != null && recipes != null) {
            for (Recipe recipe : recipes) {
                if (recipe.getMealDbId().equals(recipeId)) {
                    selectedRecipe = recipe;
                    break;
                }
            }
        }

        // Set attributes for JSP
        request.setAttribute("user", user);
        request.setAttribute("recipes", recipes);
        request.setAttribute("selectedRecipe", selectedRecipe);

        // Forward to JSP
        request.getRequestDispatcher("/userhomepage.jsp").forward(request, response);
    }
}
