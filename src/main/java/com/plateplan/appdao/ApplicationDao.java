package com.plateplan.appdao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.plateplan.user.User;

public class ApplicationDao {

	
	public ApplicationDao()
	{
		
	}
	
	
	public static Boolean isAuthenticated(String username, String password) throws SQLException
	{
		//case where email is not verified
		
		if (User.getUserByUsername(username).getEmailVerified() == false)
		{
			System.out.println("we have a username and password match but unverified email, let user know");

			return false;
		}
		
		
		
		
		//case 1, username, password and email is verified in database, allow user to login!
		String sql = "Select * from users where username = ? and password = ? and emailIsVerified = 1";
		ResultSet rs = null;
		Connection conn = null;
		conn = DbConnection.getInstance().getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the parameters for the query
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            // Execute the query
             rs = pstmt.executeQuery();
             if (rs.next()) {
                 //user found - authentication successful
             	return true;
                
             } else {
                 // user not found - authentication failed
             	return false;
               
             }
     				

        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        
        
return false;
	}

	public static Boolean addUser(String username, String password, String email, Boolean emailIsVerified) throws SQLException
	{
	
		Connection conn = null;
		
		// possibly add duplicate checking here in future, if duplicate cannot register!

		
		conn = DbConnection.getInstance().getConnection();
		
		String sql = "insert into users (username, password, email, emailIsVerified) values(?,?,?,?)";
		
       try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    	   
    	   System.out.println("adding user into database");
    	   
    	   pstmt.setString(1, username);
           pstmt.setString(2, password);
           pstmt.setString(3, email);
           pstmt.setBoolean(4, emailIsVerified);
           
           pstmt.executeUpdate();
           
           
           System.out.println("user added to database");
           
   		DbConnection.getInstance().closeConnection();
           return true;

       }
       catch (SQLException e) {
           e.printStackTrace();
           return false;

       }
      
	}


}
