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
		
		//currently one ingredient, here we would parse the list likely based on a delimiter
		//and create an ingredient list of inputs to pass for multi-ingredient meals
		
		
		List<String> ingredients = new ArrayList();
		String ingredient = request.getParameter("ingredients");
		
		//only adding the parameter passed, no multi list
		ingredients.add(ingredient);
		
		System.out.println(ingredient);
		
		try {
			recipes = apicall.searchRecipes(ingredients);
	
		
		
		
			// we have a string of all the available recipes
			  HttpSession session = request.getSession();
		      
			  
			 // System.out.println(recipes);
			  int size = recipes.size();
			  
	
			  // here we have the results from the meal calls, we want to get these into our 
			  // html recipe cards, then add functionality from there
			  
			  for (int i = 0; i<size;i++)
			  {
				//  System.out.println(recipes.get(i).getMealDbId() + " " + recipes.get(i).getName() 
				//		  + " " + recipes.get(i).getThumbnailUrl());
			  }
			   
			  if (recipes != null && recipes.size() > 1)
			  {
				  	 session.setAttribute("recipe", recipes.get(0).getMealDbId());
				     session.setAttribute("size", size);
				     session.setAttribute("recipes", recipes);
			  }
			
			  else {
				  session.setAttribute("recipes", null);
			  }
			  
				        
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
