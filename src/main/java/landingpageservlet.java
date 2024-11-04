

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    	DbConnection connection = new DbConnection();
    	try {
    		System.out.println("attempting database connection");
			connection.createDatabaseAndTable();
		} catch (ClassNotFoundException e) {
		
				System.out.println("error " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("error " + e.getMessage());
		}
    	
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

}
