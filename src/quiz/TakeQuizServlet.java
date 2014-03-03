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
 * Servlet implementation class TakeQuizServlet
 */
@WebServlet("/TakeQuizServlet")
public class TakeQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeQuizServlet() {
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
		quiz.initializeUsersAnswers();
		
		request.getSession().setAttribute("quiz", quiz);
		
		if (quiz.isMultiplePage()){
			int questionType = questions.get(0).getQuestionType();
			if (questionType == Question.QUESTION_RESPONSE){
				RequestDispatcher dispatch = request.getRequestDispatcher("singleQuestionResponse.jsp");
				dispatch.forward(request,response);
			}else if (questionType == Question.FILL_IN_THE_BLANK){
				RequestDispatcher dispatch = request.getRequestDispatcher("singleFillInTheBlank.jsp");
				dispatch.forward(request,response);
			}else if (questionType == Question.MULTIPLE_CHOICE){
				RequestDispatcher dispatch = request.getRequestDispatcher("singleMultipleChoice.jsp");
				dispatch.forward(request,response);
			}else if (questionType == Question.PICTURE_RESPONSE){
				RequestDispatcher dispatch = request.getRequestDispatcher("singlePictureResponse.jsp");
				dispatch.forward(request,response);
			}
			
		}else{
			
			
			
			//TODO display single page jsp
			
			
			
			
		}
	}

}
