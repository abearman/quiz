package quiz;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AcceptFriendRequestServlet
 */
@WebServlet("/AcceptFriendRequestServlet")
public class AcceptFriendRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptFriendRequestServlet() {
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
		String requestor = (String)request.getParameter("requestor");
		String acceptor = (String)request.getParameter("acceptor");
		
		dal.addFriendPair(requestor, acceptor);
		dal.removeFriendRequestMessage(acceptor, requestor);
		
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
