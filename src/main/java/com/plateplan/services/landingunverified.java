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
@WebServlet("/landingunverified")
public class landingunverified extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public landingunverified() {
        super();
  
    }
    
    @Override
    public void init() throws ServletException {
    
    
    	
    	
    	// TODO Auto-generated method stub
    	super.init();

    	
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
		
		System.out.println("landingpageverified called destroy");
		super.destroy();
		
	}
}
