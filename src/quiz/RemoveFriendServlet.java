package quiz;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveFriendServlet
 */
@WebServlet("/RemoveFriendServlet")
public class RemoveFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveFriendServlet() {
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
		String user1 = (String)request.getSession().getAttribute("loginName");
		String user2 = (String)request.getParameter("user2");
		
		dal.removeFriendPair(user1, user2);
		
		User user = (User)request.getSession().getAttribute("user");
		if (user.getIsAdministrator()) {
			RequestDispatcher dispatch = request.getRequestDispatcher("administratorHomepage.jsp");
			dispatch.forward(request,response);
		} else {
			RequestDispatcher dispatch = request.getRequestDispatcher("userHomepage.jsp");
			dispatch.forward(request,response);
		}
	}

}
