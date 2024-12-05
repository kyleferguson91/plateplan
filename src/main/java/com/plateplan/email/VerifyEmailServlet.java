package com.plateplan.email;

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
import com.plateplan.user.User;

/**
 * Servlet implementation class VerifyEmailServlet
 */
@WebServlet("/verify")
public class VerifyEmailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Connection connection = null;
        String email = request.getParameter("email");
        String userName = null;
        
        System.out.println("Email passed back is " + email);
        
        System.out.println("Verify it is in database, set user to true");
        
		try {
			connection = DbConnection.getInstance().getConnection();
		} catch (SQLException e) {
			System.out.println("error in db connection of verify");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String query = "SELECT * FROM users WHERE email = ?";
    

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email); 
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
   
                     userName = rs.getString("username");
                    System.out.println("User: " + userName + " has an email set the boolean to true!");
                }
                
                //set verified in the database
                String queryverify = "UPDATE users SET emailIsVerified = ? WHERE username = ?";

                try (PreparedStatement state = connection.prepareStatement(queryverify)) {
                    // Set the new email and user_id values
                	state.setBoolean(1, true); // Set the new email
                	state.setString(2, userName); // Set the user_id (for example, 123)

                    // Execute the update
                    int rowsAffected = state.executeUpdate();

                    // Check how many rows were updated
                    System.out.println("Rows affected: " + rowsAffected);
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle the exception
                }
            
                if (       
                User.getUserByUsername(userName) != null)
                {
                	System.out.println("user is not null, verify on object");
                	User.getUserByUsername(userName).setEmailVerified(true);
                	
                }
                else
                {
                	System.out.println("user is null, check user return");
                }
                
                response.sendRedirect("/PlatePlan/");
                
            //close the connection
                DbConnection.getInstance().closeConnection();
            }
        } catch (SQLException e) {
        	System.out.println("sql error in email verification check");
            e.printStackTrace();
        }

        }

 // will check that the email exists in the database, if so set the user of said emails status to true 
        // and add that to the user object!
        
        


}
