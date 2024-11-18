package com.plateplan.services;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plateplan.appdao.ApplicationDao;
import com.plateplan.user.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("/PlatePlan/notregistered.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		
		try {
			if (ApplicationDao.addUser(username, password, email))
			{
				System.out.println("user has been registered redirect");
				
				//create a user object for this user!
				User user = new User(username, password, email);

			    
			   
			    request.getSession().setAttribute("user", user);
			    
			    
			    response.sendRedirect("/PlatePlan/userhomepage.jsp");
				
			}
			else {
				System.out.println("user not registered");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("new user to register " + username + " " + password + " " + email); 
		// TODO Auto-generated method stub
	//	response.sendRedirect("/PlatePlan/landing");

	}

}
