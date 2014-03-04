package quiz;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Servlet implementation class QuizCreationServlet
 */
@WebServlet("/QuizCreationServlet")
public class QuizCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizCreationServlet() {
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
		String quizName = request.getParameter("quizName");
		String quizDescription = request.getParameter("quizDescription");
		boolean isRandom = false;
		if(request.getParameter("isRandom").equals("true"))
		{
			isRandom = true;
		}
		boolean isMultiplePage = false;
		if(request.getParameter("isMultiplePage").equals("true"))
		{
			isMultiplePage = true;
		}
		boolean isImmediateCorrection = false;
		if(request.getParameter("isImmediateCorrection").equals("true"))
		{
			isImmediateCorrection = true;
		}
		boolean canBeTakenInPracticeMode = false;
		DAL dal = (DAL)getServletContext().getAttribute("DAL");
		if(dal.doesQuizExist(quizName)) //if quizName already exists
		{
			RequestDispatcher dispatch = request.getRequestDispatcher("quizNameTaken.jsp");
			dispatch.forward(request, response);
		}
		else //quizName is unique
		{
			User user = (User)request.getSession().getAttribute("user");
			Quiz quiz = new Quiz(dal,quizName, quizDescription, isRandom, isMultiplePage, isImmediateCorrection,
					canBeTakenInPracticeMode, user.getLoginName(),new Date(), 0);
			request.getSession().setAttribute("quizCreated", quiz);
			RequestDispatcher dispatch = request.getRequestDispatcher("addQuestions.jsp");
			dispatch.forward(request, response);
		}
	}

}
