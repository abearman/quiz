package quiz;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AccountCreationServlet.
 * When called with post, checks whether entered username is in use.
 * If it is, a webpage is displays stating that the username is in use.
 * If it is not, an account is created with given username and password.
 */
@WebServlet("/AccountCreationServlet")
public class AccountCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountManager manager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountCreationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * Reads in the username and password and displays appropriate webpage depending on 
	 * whether username is in use or not.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		manager = (AccountManager)getServletContext().getAttribute("manager");
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");
		
		//check if username is in use 
		if (manager.accountExists(username)){
			
			RequestDispatcher dispatch = request.getRequestDispatcher("accountExists.jsp");
			dispatch.forward(request,response);
			
		}else{
			
			//If username is not already in use
			//Create account with given username and password
			DAL dal = (DAL)getServletContext().getAttribute("DAL");
			User user = new User(username, password, dal); //The constructor handles putting the User in the database
			request.getSession().setAttribute("user", user); //Sets the user as an attribute on the session
			
			//display user homepage
			RequestDispatcher dispatch = request.getRequestDispatcher("userHomepage.jsp");
			dispatch.forward(request,response);
		}
	}

}
