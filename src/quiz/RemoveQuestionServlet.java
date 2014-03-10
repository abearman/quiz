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
 * Servlet implementation class RemoveQuestionServlet
 */
@WebServlet("/RemoveQuestionServlet")
public class RemoveQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveQuestionServlet() {
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
		String questionNumString = (String)request.getParameter("answer");
		int questionNum = 0;
		try{
			questionNum = Integer.parseInt(questionNumString);
			
			if (questionNum > 0 && questionNum <= questions.size()){
				Question question = questions.get(questionNum-1);
				dal.removeQuestion(quiz.getQuizName(), question);
				quiz.removeQuestion(question);
				RequestDispatcher dispatch = request.getRequestDispatcher("editQuiz.jsp");
				dispatch.forward(request,response);
			}else{
				//not a valid question num
				RequestDispatcher dispatch = request.getRequestDispatcher("editQuizInvalid.jsp");
				dispatch.forward(request,response);
			}
			
		}catch (NumberFormatException e){
			//not a valid question num
			RequestDispatcher dispatch = request.getRequestDispatcher("editQuizInvalid.jsp");
			dispatch.forward(request,response);
		}
	}

}
