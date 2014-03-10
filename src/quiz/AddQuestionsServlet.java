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
			quizCreated.addQuestion(new QuestionResponse(question, answersList, quizCreated.getNextQuestionNum()));
		}
		if(questionType == Question.FILL_IN_THE_BLANK)
		{
			String question = request.getParameter("question");
			quizCreated.addQuestion(new FillInTheBlank(question, answersList, quizCreated.getNextQuestionNum()));
		}
		if(questionType == Question.MULTIPLE_CHOICE)
		{
			String question = request.getParameter("question");
			ArrayList<String> choicesList = new ArrayList<String>();
			int numChoices = Integer.parseInt(request.getParameter("numChoices"));
			for(int i = 1; i <=numChoices; i++)
			{
				choicesList.add(request.getParameter("choice"+i));
			}
			quizCreated.addQuestion(new MultipleChoice(question, answersList, quizCreated.getNextQuestionNum(), choicesList));
		}
		if(questionType == Question.PICTURE_RESPONSE)
		{
			String question = request.getParameter("question");
			String imageURL = request.getParameter("imageURL");
			quizCreated.addQuestion(new PictureResponse(question, answersList, quizCreated.getNextQuestionNum(),imageURL));
		}
		if(questionType == Question.MultiAnswer_MultipleChoice)
		{
			String question = request.getParameter("question");
			ArrayList<String> choicesList = new ArrayList<String>();
			int numChoices = Integer.parseInt(request.getParameter("numChoices"));
			for(int i = 1; i <=numChoices; i++)
			{
				choicesList.add(request.getParameter("choice"+i));
			}
			quizCreated.addQuestion(new MultiAnswerMultipleChoice(question, answersList, quizCreated.getNextQuestionNum(), choicesList));
			/*System.out.println("Question: "+question);
			for(int i = 0; i < answersList.size(); i++)
			{
				System.out.println("Answer: "+answersList.get(i));
			}
			for(int i = 0; i < choicesList.size(); i++)
			{
				System.out.println("Choice: "+choicesList.get(i));
			}*/
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher("addQuestions.jsp");
		dispatch.forward(request,response);
	}

}
