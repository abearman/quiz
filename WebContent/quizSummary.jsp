<%@ page import="java.util.*, quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<%
	//need to access the quiz in place of this dummy quiz
	Quiz dummyQuiz = new Quiz(new DAL(), "dummyQuiz","good for nothing",true,true,true,true,"Pavitra",new Date(),5); 
	String quizName = dummyQuiz.getQuizName();
	%>
	
	<title><%= quizName %></title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
	
	<%
	//need to access the quiz in place of this dummy quiz
	String descriptionOfQuiz = dummyQuiz.getDescriptionOfQuiz();
	String quizCreator = dummyQuiz.getCreatorName();
	%>
	
	<h2><%= quizName %> by <%= quizCreator %></h2>
	<h3><%= descriptionOfQuiz %></h3>
	
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>