package quiz;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DoneWithQuizServlet
 */
@WebServlet("/DoneWithQuizServlet")
public class DoneWithQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoneWithQuizServlet() {
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
		DAL dal = (DAL)request.getServletContext().getAttribute("DAL");
		String loginName = (String)request.getSession().getAttribute("loginName");
		
		Quiz quiz = (Quiz)request.getSession().getAttribute("quiz");
		User user = (User)request.getSession().getAttribute("user");
		ArrayList<Question> questions = quiz.getQuestions();
		ArrayList<String> answers = quiz.getAnswers(); 
		
		long endTime = System.currentTimeMillis();
		long startTime = (Long)request.getSession().getAttribute("startTime");
		long elapsedTime = endTime - startTime;
		quiz.setLengthOfCompletion(elapsedTime);
		request.getSession().setAttribute("elapsedTime", elapsedTime);
		
		int numQuestionsCorrect = 0;
		for (int i = 0; i < questions.size(); i++){
			System.out.println("questions.get(i) is " + questions.get(i).getQuestion());
			System.out.println("answers.get(i) is " + answers.get(i));
			if (questions.get(i).answerIsCorrect(answers.get(i))){
				numQuestionsCorrect++;
			}
		}
		quiz.setNumQuestionsCorrect(numQuestionsCorrect);
		request.getSession().setAttribute("numQuestionsCorrect", numQuestionsCorrect);
		
		//update database
		//add this to the history
		//add to top scorers
		//increment number of times taken on this quiz
		java.util.Date now = new java.util.Date();
		dal.addToHistoryListForUser(user.getLoginName(), quiz.getQuizName(), numQuestionsCorrect, elapsedTime, now);
		quiz.addTopScorer(new TopScorer(user.getLoginName(), numQuestionsCorrect, elapsedTime, dal));
		quiz.incrementNumTimesTaken();
		
		if (dal.isHighestScorerForQuiz(loginName, quiz.getQuizName())) {
			dal.addAchievementForUser(loginName, Achievements.I_AM_THE_GREATEST);
		}
		
		int numQuizzesTaken = dal.getNumberQuizzesTakenForUser(loginName);
		if (numQuizzesTaken >= 10) {
			dal.addAchievementForUser(loginName, Achievements.QUIZ_MACHINE);
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher("quizResults.jsp");
		dispatch.forward(request,response);
	}

}
