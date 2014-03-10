package quiz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.servlet.*;

/**
 * Servlet implementation class CorrectSinglePageQuizServlet
 */
@WebServlet("/CorrectSinglePageQuizServlet")
public class CorrectSinglePageQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CorrectSinglePageQuizServlet() {
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
		Quiz quiz = (Quiz)request.getSession().getAttribute("quiz");
		ArrayList<Question> questions = quiz.getQuestions();
		for(int i = 0; i < questions.size(); i++)
		{
			if(questions.get(i).getQuestionType()==Question.MultiAnswer_MultipleChoice)
			{
				String answerField = "answer"+i;
				String[] answers = request.getParameterValues(answerField);
				String input ="";
				for(int j = 0; j<answers.length; j++)
				{
					input+=answers[j];
					input+="\n";
				}
				quiz.addAnswers(input);
			}
			else
			{
				String answerField = "answer"+i;
				String answer = request.getParameter(answerField);
				quiz.addAnswers(answer);
			}
		}
		RequestDispatcher dispatch = request.getRequestDispatcher("/DoneWithQuizServlet");
		dispatch.forward(request, response);
	}

}
