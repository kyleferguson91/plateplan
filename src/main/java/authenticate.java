

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
	
				        response.sendRedirect("/PlatePlan/userhomepage.jsp"); 
				     
					
				}
				else {
					System.out.println("user is not in database!");
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
