<%@ page import="java.util.*, quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<%
	String quizName = request.getParameter("quizName");
	DAL dal = (DAL)request.getServletContext().getAttribute("DAL");
	Quiz quiz = new Quiz(dal,quizName);
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
	
	<h3><%= quizName %> by <%= quizCreator %></h3>
	<h5><%= descriptionOfQuiz %></h5>
	
	<h4>Your Past Performance on <%= quizName %>:</h4>
	<%
	ArrayList<HistoryObject> usersHistory = dal.getHistoryListForUser(user.getLoginName());
	out.println("<ul>");
	for (int i = 0; i < usersHistory.size(); i++){
		HistoryObject history = usersHistory.get(i);
		if (history.getQuizName().equals(quizName)){
			out.println("<li><b>Date:</b> " + history.getDateString() + 
					" <b>Time Taken:</b> " + history.getElapsedTime() + 
					" <b>Number of Questions Correct:</b> " + history.getNumQuestionsCorrect()
					+ "</li>");
		}
	}
	out.println("</ul>");
	%>
	
	<h4>Top Performers on <%= quizName %></h4>
	<%
	ArrayList<TopScorer> topScorers = quiz.getTopScorers();
	out.println("<ul>");
	for (int i = 0; i < topScorers.size(); i++){
		TopScorer topScorer = topScorers.get(i);
		out.println("<li><b>Name:</b> " + topScorer.getLoginName() + 
				" <b>Time taken:</b> " + topScorer.getTimeTaken() + 
				" <b>Number of Correct Questions:</b> " + topScorer.getNumCorrectQuestions() + 
				"</li>");
	}
	out.println("</ul>");
	%>
	
	<h4>Top Performers in the last day on <%= quizName %></h4>
	<%
	ArrayList<TopScorer> topScorersPastDay = quiz.getTopScorersPastDay();
	out.println("<ul>");
	for (int i = 0; i < topScorersPastDay.size(); i++){
		TopScorer topScorerPastDay = topScorersPastDay.get(i);
		out.println("<li><b>Name:</b> " + topScorerPastDay.getLoginName() + 
				" <b>Time taken:</b> " + topScorerPastDay.getTimeTaken() + 
				" <b>Number of Correct Questions:</b> " + topScorerPastDay.getNumCorrectQuestions() + 
				"</li>");
	}
	out.println("</ul>");
	%>
	
	<h4>Recent Test Takers of  <%= quizName %></h4>
	<%
	ArrayList<HistoryObject> quizHistory = dal.getAllHistoryLists(quizName);
	out.println("<ul>");
	for (int i = 0; i < quizHistory.size(); i++){
		HistoryObject history = quizHistory.get(i);
		out.println("<li><b>Name:</b> " + history.getUserName() +
				" <b>Date:</b> " + history.getDateString() + 
				" <b>Time Taken:</b> " + history.getElapsedTime() + 
				" <b>Number of Questions Correct:</b> " + history.getNumQuestionsCorrect()
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