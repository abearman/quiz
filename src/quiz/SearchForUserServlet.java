package quiz;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchForUserServlet
 */
@WebServlet("/SearchForUserServlet")
public class SearchForUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchForUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAL dal = (DAL)getServletContext().getAttribute("DAL");
		String userToSearch = (String)request.getParameter("usernameToSearch");
		if (dal.accountExists(userToSearch)) {
			RequestDispatcher dispatch = request.getRequestDispatcher("friendProfile.jsp?friendName=" + userToSearch);		
			dispatch.forward(request,response);
		} else { //Profile was not found
			RequestDispatcher dispatch = request.getRequestDispatcher("profileNotFound.jsp?usernameToSearch=" + userToSearch);		
			dispatch.forward(request,response);
		}
	}

}
