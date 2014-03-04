<%@ page import="java.util.*, quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Quiz Creation Successful</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

	<h3>Quiz Creation was Successful!</h3>
	
	<%
	Quiz quizCreated = (Quiz)request.getSession().getAttribute("quizCreated");
	String quizSummaryURL = "quizSummary.jsp?quizName=" + quizCreated.getQuizName();
	%>
	
	Check out your <a href="<%= quizSummaryURL %>">quiz</a><br>
	Go back to your <a href="userHomepage.jsp">homepage</a>
	
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>