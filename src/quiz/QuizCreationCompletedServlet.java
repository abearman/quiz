package quiz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;

/**
 * Servlet implementation class QuizCreationCompletedServlet
 */
@WebServlet("/QuizCreationCompletedServlet")
public class QuizCreationCompletedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizCreationCompletedServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAL dal = (DAL)getServletContext().getAttribute("DAL");
		String loginName = (String)request.getSession().getAttribute("loginName");
		
		int numQuizzesCreated = dal.getNumberQuizzesCreatedForUser(loginName);
		if (numQuizzesCreated == 10) { //Created 10 quizzes
			dal.addAchievementForUser(loginName, Achievements.PRODIGIOUS_AUTHOR); 
		} else if (numQuizzesCreated == 5) { //Created 5 quizzes
			dal.addAchievementForUser(loginName, Achievements.PROLIFIC_AUTHOR); 
		} else if (numQuizzesCreated == 1) { //Created 1 quiz
			dal.addAchievementForUser(loginName, Achievements.AMATEUR_AUTHOR);
		}
		
		//forwards to the quizCreationSuccessful page
		RequestDispatcher dispatch = request.getRequestDispatcher("quizCreationSuccessful.jsp");
		dispatch.forward(request, response);
	}

}
