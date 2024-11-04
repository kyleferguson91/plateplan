import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

	

    private static final String USERNAME = "root1";
    private static final String PASSWORD = "root";
    private static final String CONN_STRING ="jdbc:mysql://localhost:3306/plateplan";
    

    public static Connection getConnectionToDatabase() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        try {
        	conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
          //  conn.setCatalog("plateplan");
        }
        catch (SQLException e)
        {
        	System.err.println(e);
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
        }
   
    
  

        return conn;
    }

    public static void createDatabaseAndTable() throws SQLException, ClassNotFoundException {
        String createDBSQL = "CREATE DATABASE IF NOT EXISTS plateplan DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;";
        String createTableSQL = "CREATE TABLE IF NOT EXISTS `users` ("
        		+ "`userid` INT NOT NULL AUTO_INCREMENT, "
                + "`username` char(40) COLLATE utf8_unicode_ci NOT NULL, "
                + "`password` char(40) COLLATE utf8_unicode_ci DEFAULT NULL, "
                + "`email` char(50) COLLATE utf8_unicode_ci, "
                + "PRIMARY KEY (`userid`)"
                + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;";

      
  Connection conn = getConnectionToDatabase();
             Statement stmt = conn.createStatement();

          
            stmt.executeUpdate(createDBSQL);
    
        Connection dbConn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                 Statement dbStmt = dbConn.createStatement(); 

              
                dbStmt.executeUpdate(createTableSQL);
            

       System.out.println("database and tables created!");
    //   DriverManager.deregisterDriver((Driver) dbConn);
        conn.close();   
        
    }
    
	
}
