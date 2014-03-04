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
		Quiz quizCreated = (Quiz)request.getSession().getAttribute("quizCreated");
		int questionType = Integer.parseInt(request.getParameter("questionType"));
		int numAnswers = Integer.parseInt(request.getParameter("numAnswers"));
		ArrayList<String> answersList = new ArrayList<String>();
		for(int i = 1; i <=numAnswers; i++)
		{
			answersList.add(request.getParameter("answer"+i));
		}
		if(questionType == Question.QUESTION_RESPONSE)
		{
			String question = request.getParameter("question");
			System.out.println("Question: "+question);
			//System.out.println("Answers: "+answers);
			quizCreated.addQuestion(new QuestionResponse(question, answersList, quizCreated.getNextQuestionNum()));
		}
		if(questionType == Question.FILL_IN_THE_BLANK)
		{
			String question = request.getParameter("question");
			System.out.println("Question: "+question);
			//System.out.println("Answers: "+answers);
			quizCreated.addQuestion(new FillInTheBlank(question, answersList, quizCreated.getNextQuestionNum()));
		}
		if(questionType == Question.MULTIPLE_CHOICE)
		{
			String question = request.getParameter("question");
			String choices = request.getParameter("choices");
			ArrayList<String> choicesList = Question.createArray(choices);
			System.out.println("Question: "+question);
			//System.out.println("Answers: "+answers);
			System.out.println("Choices: " + choices);
			quizCreated.addQuestion(new MultipleChoice(question, answersList, quizCreated.getNextQuestionNum(), choicesList));
		}
		if(questionType == Question.PICTURE_RESPONSE)
		{
			String question = request.getParameter("question");
			String imageURL = request.getParameter("imageURL");
			System.out.println("Question: "+question);
			//System.out.println("Answers: "+answers);
			System.out.println("imageURL: " + imageURL);
			quizCreated.addQuestion(new PictureResponse(question, answersList, quizCreated.getNextQuestionNum(),imageURL));
		}
		RequestDispatcher dispatch = request.getRequestDispatcher("addQuestions.jsp");
		dispatch.forward(request,response);
	}

}
