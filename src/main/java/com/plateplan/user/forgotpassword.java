package com.plateplan.user;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plateplan.appdao.DbConnection;
import com.plateplan.email.Email;

/**
 * Servlet implementation class forgotpassword
 */
@WebServlet("/forgotpassword")
public class forgotpassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public forgotpassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		
		
		response.sendRedirect("landing");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("This is where we do the forgot password stuff, find username submitted, grab email from sql, reset password in database, email the user the changed password");
		
		//check for password in database and send to email on file
		  PreparedStatement stmt = null;
		
		String query = "select password from users where username = ?";
	
		
		String username = request.getParameter("username");
		Connection conn = null;
		  try {
			conn = DbConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		           
					try {
						stmt = conn.prepareStatement("SELECT password, email FROM users WHERE username = ?");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}   
		            try {
						stmt.setString(1, username);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
		            try (ResultSet rs = stmt.executeQuery()) {
		                if (rs.next()) {
		                     String password = rs.getString("password");
		                     String email = rs.getString("email");
		     	            System.out.println("match found " + password + email);
		     	            
		     	            // send the password to the user
		     	            

		    	            new Thread(() -> {
		    	            	Email.sendEmail(email, "Your password is", "Your plate plan password is " + password);;
		    	            }).start();
		    		
		              
		                } else {
		                 System.out.println("No user found in forgot password servlet.");
		                }
		            } catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        
	
		            doGet(request, response);
	} 
		
		
		
		

             
	}
         
		
	
	


