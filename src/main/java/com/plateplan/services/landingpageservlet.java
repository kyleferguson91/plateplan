package com.plateplan.services;
import com.plateplan.email.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plateplan.appdao.DbConnection;
import com.plateplan.email.Email;
import com.plateplan.user.User;

/**
 * Servlet implementation class landingpageservlet
 */
@WebServlet("/landing")
public class landingpageservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public landingpageservlet() {
        super();
  
    }
    
    @Override
    public void init() throws ServletException {
    
    
    	
    	
    	// TODO Auto-generated method stub
    	super.init();
    	DbConnection connection = DbConnection.getInstance();
    	System.out.println("attempting database connection");
	connection.createDatabaseAndTable();
    connection.closeConnection();
    	
    System.out.println("checking for static user list creation items");
    User.showUsersList();
    
    //System.out.println("send email test");
	//Email.sendEmail("plateplan0@gmail.com","test","test");
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
      
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
	 dispatcher.forward(request, response);
	//	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
		System.out.println("landing page called destroy");
		super.destroy();
		
	}
}
