package quiz;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet.
 * On a post request, checks whether entered username exists and the password
 * matches the username.  If it does, displays the welcome user page.  If it 
 * does not, displays the try again page.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountManager manager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * Gets username and password fields entered by the user and displays appropriate
	 * page depending on whether or not the username inputed exists and the password
	 * matches the username.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		manager = (AccountManager)getServletContext().getAttribute("manager");
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");
		
		request.getSession().setAttribute("loginName", username);
		
		if (manager.accountExists(username) && manager.isPasswordForAccount(username, password)){
			
			DAL dal = (DAL)getServletContext().getAttribute("DAL");
			User user = dal.getUser(username); //Gets User from database to store on session 
			request.getSession().setAttribute("user", user); //Sets the user as an attribute on the session
			
			if (user.getIsAdministrator()) { //display administrator homepage
				RequestDispatcher dispatch = request.getRequestDispatcher("administratorHomepage.jsp");
				dispatch.forward(request, response);
			} else { //display user homepage
				RequestDispatcher dispatch = request.getRequestDispatcher("userHomepage.jsp");
				dispatch.forward(request,response);
			}
		
		}else{
		
			//display try again page
			RequestDispatcher dispatch = request.getRequestDispatcher("tryAgainPage.html");
			dispatch.forward(request,response);
		}
	}
}
