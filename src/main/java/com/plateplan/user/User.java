package com.plateplan.user;

import com.plateplan.recipes.*;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	//populate this list on application load, via the DB
	static final List<User> users = new ArrayList<User>();

	
	// main user info
	private String username;
	private String password;
	private String email;
	private Boolean isAuthenticated;
	private Boolean emailVerified = false;


	//users food info
	public List<Recipe> myRecipes = new ArrayList<Recipe>();
	
	

	
	
	
	public User(String username, String password, String email, Boolean emailIsVerified)
	{
		this.username = username;
		this.password = password;
		this.email = email;
		
		users.add(this);
	}
	
	public String getUsername()
	{
		return this.username;
	}

	public String getEmail()
	{
		return this.email;
		
	}
	
	public static User getUserByUsername(String username)
	{
		for (int i = 0; i<users.size(); i++)
		{
			if (users.get(i).getUsername().equals(username))
			{
				return users.get(i);
			}
		}
		return null;
	}

	public Boolean getIsAuthenticated() {
		return isAuthenticated;
	}

	public void setIsAuthenticated(Boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	
	
	public void addRecipe(Recipe recipe)
	{
		myRecipes.add(recipe);
	}
	
	public void broadcast()
	{
		System.out.println("Username is: " + this.username + " am i authenticated? " + this.isAuthenticated.toString() );
	}
	
	public List<Recipe>getRecipeList() 
	{
		return this.myRecipes;
	}

	public Boolean getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}
	
	
	public static void showUsersList()
	{
		for (User u : users)
		{
			System.out.println(u.getUsername());
		}
	}

}
