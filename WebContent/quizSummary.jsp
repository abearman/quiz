<%@ page import="java.util.*, quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<%
	
	
	String quizName = request.getParameter("quizName");
	DAL dal = (DAL)request.getServletContext().getAttribute("dal");
	Quiz quiz = new Quiz(dal,quizName);
	
	
	//TODO need to access the quiz in place of this dummy quiz for testing
	/*
	DAL dal = (DAL)request.getServletContext().getAttribute("dal");
	Quiz quiz = new Quiz(new DAL(), "dummyQuiz","good for nothing",true,true,true,true,"Pavitra",new Date(),5); 
	String quizName = quiz.getQuizName();
	*/
	%>
	
	<title><%= quizName %></title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
	
	<%
	User user = (User)session.getAttribute("user");
	
	String descriptionOfQuiz = quiz.getDescriptionOfQuiz();
	String quizCreator = quiz.getCreatorName();
	
	double statistics[] = quiz.getStatisticsSummary();
	double averageScore = statistics[0];
	double medianScore = statistics[1];
	double averageTime = statistics[2];
	%>
	
	<h2><%= quizName %> by <%= quizCreator %></h2>
	<h3><%= descriptionOfQuiz %></h3>
	
	<h3>Your Past Performance on <%= quizName %>:</h3>
	<%
	ArrayList<HistoryObject> usersHistory = user.getHistory();
	out.println("<ul>");
	for (int i = 0; i < usersHistory.size(); i++){
		HistoryObject history = usersHistory.get(i);
		if (history.getQuizName().equals(quizName)){
			out.println("<li>Date: " + history.getDateString() + 
					"Time Taken: " + history.getElapsedTime() + 
					"Number of Questions Correct: " + history.getNumQuestionsCorrect()
					+ "</li>");
		}
	}
	out.println("</ul>");
	%>
	
	<h3>Top Performers on <%= quizName %></h3>
	<%
	ArrayList<TopScorer> topScorers = quiz.getTopScorers();
	out.println("<ul>");
	for (int i = 0; i < topScorers.size(); i++){
		TopScorer topScorer = topScorers.get(i);
		out.println("<li>Name: " + topScorer.getLoginName() + 
				"Time taken: " + topScorer.getTimeTaken() + 
				"Number of Correct Questions: " + topScorer.getNumCorrectQuestions() + 
				"</li>");
	}
	out.println("</ul>");
	%>
	
	<h3>Top Performers in the last day on <%= quizName %></h3>
	<%
	ArrayList<TopScorer> topScorersPastDay = quiz.getTopScorersPastDay();
	out.println("<ul>");
	for (int i = 0; i < topScorersPastDay.size(); i++){
		TopScorer topScorerPastDay = topScorersPastDay.get(i);
		out.println("<li>Name: " + topScorerPastDay.getLoginName() + 
				"Time taken: " + topScorerPastDay.getTimeTaken() + 
				"Number of Correct Questions: " + topScorerPastDay.getNumCorrectQuestions() + 
				"</li>");
	}
	out.println("</ul>");
	%>
	
	<h3>Recent Test Takers of  <%= quizName %></h3>
	<%
	ArrayList<HistoryObject> quizHistory = dal.getAllHistoryLists(quizName);
	out.println("<ul>");
	for (int i = 0; i < quizHistory.size(); i++){
		HistoryObject history = quizHistory.get(i);
		out.println("<li>Date: " + history.getDateString() + 
				"Time Taken: " + history.getElapsedTime() + 
				"Number of Questions Correct: " + history.getNumQuestionsCorrect()
				+ "</li>");
	}
	out.println("</ul>");
	%>
	
	<h3>Statistics Summary</h3>
	<ul>
	<li>Average Score: <%= averageScore %></li>
	<li>Median Score: <%= medianScore %></li>
	<li>Average Time: <%= averageTime %></li>
	</ul>
	
	<form action="TakeQuizServlet" method="post">
	<input type="hidden" name="quizName" value="<%= quizName %>"/>
	<input type = "submit" value = "Take This Quiz">
	</form>
	
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>