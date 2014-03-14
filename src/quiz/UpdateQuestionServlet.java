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
 * Servlet implementation class UpdateQuestionServlet
 */
@WebServlet("/UpdateQuestionServlet")
public class UpdateQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateQuestionServlet() {
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
		Quiz quiz = (Quiz)request.getSession().getAttribute("editQuiz");
		ArrayList<Question> questions = (ArrayList<Question>)(request.getSession().getAttribute("editQuizQuestions"));
		Question question = (Question)request.getSession().getAttribute("editQuestion");
		String questionString = request.getParameter("question");
		int questionType = question.getQuestionType();
		int questionNum = question.getQuestionNumber();
		int numAnswers = question.getAnswer().size();
		
		//remove this question
		dal.removeQuestion(quiz.getQuizName(), question);
		quiz.removeQuestion(question);
		
		//read form and add the question
		ArrayList<String> answersList = new ArrayList<String>();
		for(int i = 1; i <=numAnswers; i++)
		{
			answersList.add(request.getParameter("answer"+i));
		}
		
		if(questionType == Question.QUESTION_RESPONSE)
		{
			quiz.addQuestion(new QuestionResponse(questionString, answersList, questionNum));
		}
		if(questionType == Question.FILL_IN_THE_BLANK)
		{
			quiz.addQuestion(new FillInTheBlank(questionString, answersList, questionNum));
		}
		if(questionType == Question.MULTIPLE_CHOICE)
		{
			int numChoices = ((MultipleChoice)question).getChoices().size();
			ArrayList<String> choicesList = new ArrayList<String>();
			for(int i = 1; i <=numChoices; i++)
			{
				choicesList.add(request.getParameter("choice"+i));
			}
			quiz.addQuestion(new MultipleChoice(questionString, answersList, questionNum, choicesList));
		}
		if(questionType == Question.PICTURE_RESPONSE)
		{
			String imageURL = request.getParameter("imageURL");
			quiz.addQuestion(new PictureResponse(questionString, answersList, questionNum,imageURL));
		}
		if(questionType == Question.MultiAnswer_MultipleChoice)
		{
			int numChoices = ((MultiAnswerMultipleChoice)question).getChoices().size();
			ArrayList<String> choicesList = new ArrayList<String>();
			for(int i = 1; i <=numChoices; i++)
			{
				choicesList.add(request.getParameter("choice"+i));
			}
			quiz.addQuestion(new MultiAnswerMultipleChoice(questionString, answersList, questionNum, choicesList));
		}
		RequestDispatcher dispatch = request.getRequestDispatcher("editQuiz.jsp");
		dispatch.forward(request,response);
	}

}
