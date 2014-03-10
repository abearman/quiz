package quiz;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;

/**
 * Servlet implementation class AddQuestionsServlet
 */
@WebServlet("/SendMessageServlet")
public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendMessageServlet() {
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
		String fromUserString = (String) request.getParameter("fromUser");
		String toUserString = (String) request.getParameter("toUser");
		String messageType = (String) request.getParameter("messageType");

		if (messageType.equals(Message.NOTE_MESSAGE)) {
			String message = (String) request.getParameter("message");
			new NoteMessage(fromUserString, toUserString, message, dal);
			dal.setHasNewMessage(toUserString, true);
			RequestDispatcher dispatch = request.getRequestDispatcher("friendProfile.jsp?friendName=" + toUserString);
			dispatch.forward(request,response);
		} else if (messageType.equals(Message.CHALLENGE_MESSAGE)) {
			String quizString = (String) request.getParameter("quiz");
			Quiz quiz = new Quiz(dal, quizString);
			if (dal.areFriends(fromUserString, toUserString)) {
				new ChallengeMessage(fromUserString, toUserString, quiz, dal);
				dal.setHasNewMessage(toUserString, true);
				RequestDispatcher dispatch = request.getRequestDispatcher("quizResults.jsp");
				dispatch.forward(request,response);
			} else {
				RequestDispatcher dispatch = request.getRequestDispatcher("quizResultsBadChallenge.jsp?friendAttempt=" + toUserString);
				dispatch.forward(request,response);
			}
		}

	}

}
