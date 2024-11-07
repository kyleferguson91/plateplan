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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ingredientServletSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("ingredients servlet");
		//handle search logic here, 
		

		
		
		MealDBService apicall = new MealDBService();
		
		
		
		List<Recipe> recipes;
		
		List<String> ingredients = new ArrayList();
		String ingredient = request.getParameter("ingredients");
		ingredients.add(ingredient);
		System.out.println(ingredient);
		try {
			recipes = apicall.searchRecipes(ingredients);
		System.out.println(recipes.toString());
		
		
		
			// we have a string of all the available recipes
			  HttpSession session = request.getSession();
		      
			
			  System.out.println(recipes);
			  int size = recipes.size();
			  
			  for (int i = 0; i<size;i++)
			  {
				  System.out.println(recipes.get(i).getMealDbId() + " " + recipes.get(i).getName() 
						  + " " + recipes.get(i).getThumbnailUrl());
			  }
		        
			     session.setAttribute("size", size);
			     session.setAttribute("recipes", recipes);
			  
		        response.sendRedirect("/PlatePlan/userhomepage.jsp");
			
		        
		        
		        
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//response.sendRedirect("/PlatePlan/userhomepage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ingredients servlet");
		doGet(request, response);
	}

}
