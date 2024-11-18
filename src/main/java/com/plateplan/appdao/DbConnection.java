package com.plateplan.appdao;
import com.plateplan.user.User;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DbConnection {

    private static final String USERNAME = "root1";
    private static final String PASSWORD = "root";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/plateplan";

    private static DbConnection instance = null;
    private Connection connection;
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private DbConnection() {
  
    }

    public static DbConnection getInstance() {
        if (instance == null) {
            synchronized (DbConnection.class) {
                if (instance == null) {
                    instance = new DbConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
    	this.connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        return connection;
    }

    public void createDatabaseAndTable() {
        try (Statement statement = getConnection().createStatement()) {
            // Create the database if it does not exist
            String createDBSQL = "CREATE DATABASE IF NOT EXISTS plateplan DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;";
            statement.executeUpdate(createDBSQL);

            // Reconnect to the newly created database, if necessary
            connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

            // Create the 'users' table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS `users` (" +
                                    "`userid` INT NOT NULL AUTO_INCREMENT, " +
                                    "`username` CHAR(40) COLLATE utf8_unicode_ci NOT NULL, " +
                                    "`password` CHAR(40) COLLATE utf8_unicode_ci DEFAULT NULL, " +
                                    "`email` CHAR(50) COLLATE utf8_unicode_ci, " +
                                    "PRIMARY KEY (`userid`)" +
                                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;";
            statement.executeUpdate(createTableSQL);
            System.out.println("Database and table created!");

            // Load data from the database into user objects
            loadUserData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadUserData() {
        String query = "SELECT * FROM users";
        try (ResultSet rs = connection.prepareStatement(query).executeQuery()) {
            while (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");

                User newUser = new User(username, password, email);
                System.out.println("User " + username + " added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

/* To get the connection
Connection conn = DbConnection.getInstance().getConnection();

//To create the table (if needed again)
DbConnection.getInstance().createDatabaseAndTable();

//To close the connection (recommended on application shutdown)
DbConnection.getInstance().closeConnection(); */