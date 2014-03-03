package quiz;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;

/**
 * Servlet implementation class AddQuestionsServlet
 */
@WebServlet("/AddQuestionsServlet")
public class AddQuestionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestionsServlet() {
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
		//TODO request should have the quiz stored some how
		int questionType = Integer.parseInt(request.getParameter("questionType"));
		if(questionType == Question.QUESTION_RESPONSE)
		{
			String question = request.getParameter("question");
			String answers = request.getParameter("answers");
			ArrayList<String> answersList = Question.createArray(answers);
			System.out.println("Question: "+question);
			System.out.println("Answers: "+answers);
			//TODO add question to quiz
		}
		if(questionType == Question.FILL_IN_THE_BLANK)
		{
			String question = request.getParameter("question");
			String answers = request.getParameter("answers");
			ArrayList<String> answersList = Question.createArray(answers);
			System.out.println("Question: "+question);
			System.out.println("Answers: "+answers);
			//TODO add question to quiz
		}
		if(questionType == Question.MULTIPLE_CHOICE)
		{
			String question = request.getParameter("question");
			String answers = request.getParameter("answers");
			ArrayList<String> answersList = Question.createArray(answers);
			String choices = request.getParameter("choices");
			ArrayList<String> choicesList = Question.createArray(choices);
			System.out.println("Question: "+question);
			System.out.println("Answers: "+answers);
			System.out.println("Choices: " + choices);
			//TODO add question to quiz
		}
		if(questionType == Question.PICTURE_RESPONSE)
		{
			String question = request.getParameter("question");
			String answers = request.getParameter("answers");
			String imageURL = request.getParameter("imageURL");
			System.out.println("Question: "+question);
			System.out.println("Answers: "+answers);
			System.out.println("imageURL: " + imageURL);
			//TODO add question to quiz
		}
		RequestDispatcher dispatch = request.getRequestDispatcher("addQuestions.jsp");
		dispatch.forward(request,response);
	}

}
