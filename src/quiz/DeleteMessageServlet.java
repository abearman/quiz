package quiz;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteMessageServlet
 */
@WebServlet("/DeleteMessageServlet")
public class DeleteMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMessageServlet() {
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
		String type = request.getParameter("type");
		if (type.equals(Message.NOTE_MESSAGE)) {
			String fromUser = request.getParameter("fromUser");
			String toUser = request.getParameter("toUser");
			String message = request.getParameter("message");
			dal.removeMessageWithQuery("DELETE FROM messages WHERE fromUser = \"" + fromUser + "\" AND toUser = \"" + toUser + "\" AND message = \"" + message + "\" AND messageType = \"" + type + "\";");
		} else if (type.equals(Message.CHALLENGE_MESSAGE)) {
			String fromUser = request.getParameter("fromUser");
			String toUser = request.getParameter("toUser");
			String message = request.getParameter("message");
			String quizName = request.getParameter("quizName");
			dal.removeMessageWithQuery("DELETE FROM messages WHERE fromUser = \"" + fromUser + "\" AND toUser = \"" + toUser + "\" AND message = \"" + message + "\" AND messageType = \"" + type + "\" AND quizName = \"" + quizName + "\";");
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher("messages.jsp");
		dispatch.forward(request, response);
	}

}
