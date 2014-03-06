package quiz;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateAnswersServlet
 */
@WebServlet("/UpdateAnswersServlet")
public class UpdateAnswersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAnswersServlet() {
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
		String answer = (String)request.getParameter("answer");
		Quiz quiz = (Quiz)request.getSession().getAttribute("quiz");
		quiz.addAnswers(answer);
		
		if (quiz.isImmediateCorrection()){
			Question question = quiz.getQuestions().get(quiz.getCurrentQuestionNum());
			int questionType = question.getQuestionType();
			boolean answerCorrect = question.answerIsCorrect(answer);
			
			if (answerCorrect){
				if (questionType == Question.QUESTION_RESPONSE){
					RequestDispatcher dispatch = request.getRequestDispatcher("correctQuestionResponseAnswer.jsp");
					dispatch.forward(request,response);
				}else if (questionType == Question.FILL_IN_THE_BLANK){
					RequestDispatcher dispatch = request.getRequestDispatcher("correctQuestionResponseAnswer.jsp");
					dispatch.forward(request,response);
				}else if (questionType == Question.MULTIPLE_CHOICE){
					RequestDispatcher dispatch = request.getRequestDispatcher("correctMultipleChoiceAnswer.jsp");
					dispatch.forward(request,response);
				}else if (questionType == Question.PICTURE_RESPONSE){
					RequestDispatcher dispatch = request.getRequestDispatcher("correctPictureResponseAnswer.jsp");
					dispatch.forward(request,response);
				}
			}else{
				if (questionType == Question.QUESTION_RESPONSE){
					RequestDispatcher dispatch = request.getRequestDispatcher("incorrectQuestionResponseAnswer.jsp");
					dispatch.forward(request,response);
				}else if (questionType == Question.FILL_IN_THE_BLANK){
					RequestDispatcher dispatch = request.getRequestDispatcher("incorrectQuestionResponseAnswer.jsp");
					dispatch.forward(request,response);
				}else if (questionType == Question.MULTIPLE_CHOICE){
					RequestDispatcher dispatch = request.getRequestDispatcher("incorrectMultipleChoiceAnswer.jsp");
					dispatch.forward(request,response);
				}else if (questionType == Question.PICTURE_RESPONSE){
					RequestDispatcher dispatch = request.getRequestDispatcher("incorrectPictureResponseAnswer.jsp");
					dispatch.forward(request,response);
				}
			}
		}else{
			
			if (quiz.getCurrentQuestionNum() == quiz.getQuestions().size() - 1){
				RequestDispatcher dispatch = request.getRequestDispatcher("/DoneWithQuizServlet");
				dispatch.forward(request,response);
			}else{
				Question nextQuestion = quiz.getQuestions().get(quiz.getNextQuestionNum());
				int questionType = nextQuestion.getQuestionType();
				
				if (questionType == Question.QUESTION_RESPONSE){
					RequestDispatcher dispatch = request.getRequestDispatcher("singleQuestionResponse.jsp");
					dispatch.forward(request,response);
				}else if (questionType == Question.FILL_IN_THE_BLANK){
					RequestDispatcher dispatch = request.getRequestDispatcher("singleQuestionResponse.jsp");
					dispatch.forward(request,response);
				}else if (questionType == Question.MULTIPLE_CHOICE){
					RequestDispatcher dispatch = request.getRequestDispatcher("singleMultipleChoice.jsp");
					dispatch.forward(request,response);
				}else if (questionType == Question.PICTURE_RESPONSE){
					RequestDispatcher dispatch = request.getRequestDispatcher("singlePictureResponse.jsp");
					dispatch.forward(request,response);
				}
			}
		}
	}

}
