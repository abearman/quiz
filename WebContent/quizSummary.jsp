<%@ page import="java.util.*, quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<%
	
	/*
	String quizName = request.getParameter("quizName");
	DAL dal = (DAL)request.getServletContext().getAttribute("dal");
	Quiz quiz = new Quiz(dal,quizName);
	*/
	
	//TODO need to access the quiz in place of this dummy quiz for testing
	Quiz quiz = new Quiz(new DAL(), "dummyQuiz","good for nothing",true,true,true,true,"Pavitra",new Date(),5); 
	String quizName = quiz.getQuizName();
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
	
	<h3>Top Performers on <%= quizName %></h3>
	
	<h3>Top Performers in the last day on <%= quizName %></h3>
	
	<h3>Statistics Summary</h3>
	<ul>
	<li>Average Score: <%= averageScore %></li>
	<li>Median Score: <%= medianScore %></li>
	<li>Average Time: <%= averageTime %></li>
	</ul>
	
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>