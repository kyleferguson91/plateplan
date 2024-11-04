package com.plateplan.appdao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationDao {

	
	public ApplicationDao()
	{
		
	}
	
	
	public static Boolean isAuthenticated(String username, String password) throws SQLException
	{
		
		String sql = "Select * from users where username = ? and password = ?";
		ResultSet rs = null;
		Connection conn = null;

		try {
			 conn = DbConnection.getConnectionToDatabase();
		} catch (ClassNotFoundException e) {
			System.out.println("error in auth connection isAuthenticated() " + e.getMessage());
		
		} catch (SQLException e) {
			System.out.println("error in auth connection isAuthenticated() " + e.getMessage());
		}
		
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

	public static Boolean addUser(String username, String password, String email)
	{
	
		Connection conn = null;

		
		try {
			 conn = DbConnection.getConnectionToDatabase();
		} catch (ClassNotFoundException e) {
			System.out.println("error in auth connection isAuthenticated() " + e.getMessage());
		
		} catch (SQLException e) {
			System.out.println("error in auth connection isAuthenticated() " + e.getMessage());
		}
		
		String sql = "insert into users (username, password, email) values(?,?,?)";
		
       try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    	   
    	   System.out.println("adding user into database");
    	   
    	   pstmt.setString(1, username);
           pstmt.setString(2, password);
           pstmt.setString(3, email);
           
           pstmt.executeUpdate();
           
           
           System.out.println("user added to database");
           
           
           return true;

       }
       catch (SQLException e) {
           e.printStackTrace();
           return false;

       }
      
	}


}
