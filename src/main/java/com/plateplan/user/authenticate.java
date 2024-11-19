package com.plateplan.user;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plateplan.appdao.ApplicationDao;

/**
 * Servlet implementation class autnticate
 */
@WebServlet("/authenticate")
public class authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public authenticate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("auth get then redirect");
		  response.sendRedirect("/PlatePlan/landing");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	System.out.println("do post via submit credentials");

		// the value of this above parameter will be the value of the button clicked
		//System.out.println(request.getParameter("username"));
		//System.out.println(request.getParameter("password"));

		System.out.println("auth post then redirect " + request.getParameter("action"));

		
		
		//register button clicked, redirect
		if (request.getParameter("action").equals("register"))
		{
			
			System.out.println("register clicked from landing");
			response.sendRedirect("/PlatePlan/Register");
		}
		if (request.getParameter("action").equals("forgotpassword"))
		{
			response.sendRedirect("/PlatePlan/forgotpassword.html");
		}
		if (request.getParameter("action").equals("guest"))
		{
			
			System.out.println("loop users");
			
			for (int i = 0; i<User.users.size(); i++)
			{
				System.out.println(User.users.get(i).getUsername());
			}
			
			
			
			
	//		User currentUser = new User("guest", "giest");
			User currentUser = User.getUserByUsername("guest");
	        HttpSession session = request.getSession();
	        session.setAttribute("user", currentUser);
			
			//guest mode clicked, enter guest user page
			System.out.println("set guest mode redirect home");
			response.sendRedirect("/PlatePlan/userhomepage.jsp");
			
		}
		
		// we can query for the button clicked and the parameters passed
		if (request.getParameter("action").equals("submit"))
		{
			// we have submitted vis submit button
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			System.out.println("username: " + username + " password: " + password);
			
			
		
			
			try {
				if (ApplicationDao.isAuthenticated(username, password))
				{
					System.out.println("user is in the database!");
		

				System.out.println("checking for user profile in list");
					// find the user
				User currentUser = User.getUserByUsername(username);
					if (	//find the user
					
						 currentUser  != null
					)
					{
						System.out.println("found user profile in list");
	
						
						//authenticate the user 
						
						currentUser.setIsAuthenticated(true);
			
						// any debug
						currentUser.broadcast();
						
					
						System.out.println("creating user session");
						
					  
				        HttpSession session = request.getSession();
				        session.setAttribute("user", currentUser);
						
						
					}
					
					
			

				
					
					

					
					  response.sendRedirect("/PlatePlan/userhomepage.jsp");
				}
				else {
					System.out.println("user is not in database! or email is unverified");
					// redirect to register page
					  response.sendRedirect("/PlatePlan/notregistered.html");
				}
			} catch (SQLException e) {
				System.out.println("application dao authentication calling error " + e.getMessage());
			}
		}
		
		
		
		
		
		
		
		//  response.sendRedirect("/PlatePlan/landing");
	
		//doGet(request, response);
	}

}
