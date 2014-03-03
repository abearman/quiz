package quiz;

import java.io.IOException;
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
		
		DAL dal = (DAL)getServletContext().getAttribute("DAL");
		String quizName = (String)request.getParameter("quizName");
		Quiz quiz = new Quiz(dal, quizName);
		ArrayList<Question> questions = quiz.getQuestions();
		ArrayList<String> answers = quiz.getAnswers();
		
		long endTime = System.currentTimeMillis();
		long startTime = (Long)request.getSession().getAttribute("startTime");
		long elapsedTime = endTime - startTime;
		quiz.setLengthOfCompletion(elapsedTime);
		request.getSession().setAttribute("elapsedTime", elapsedTime);
		
		int numQuestionsCorrect = 0;
		for (int i = 0; i < questions.size(); i++){
			if (questions.get(i).answerIsCorrect(answers.get(i))){
				numQuestionsCorrect++;
			}
		}
		quiz.setNumQuestionsCorrect(numQuestionsCorrect);
		request.getSession().setAttribute("numQuestionsCorrect", numQuestionsCorrect);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("quizResults.jsp");
		dispatch.forward(request,response);
	}

}
