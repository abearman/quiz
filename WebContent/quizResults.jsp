<%@ page import="java.util.*, quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Question Response</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

<%

Quiz quiz = (Quiz)request.getSession().getAttribute("quiz");
ArrayList<Question> questions = quiz.getQuestions();
ArrayList<String> usersAnswers = quiz.getAnswers();
long elapsedTime = (Long)request.getSession().getAttribute("elapsedTime");
int numQuestionsCorrect = (Integer)request.getSession().getAttribute("numQuestionsCorrect");
int totalNumQuestions = questions.size();

%>

<h3><%= quiz.getQuizName() %> by <%= quiz.getCreatorName() %></h3>

<h4>Score: <%= numQuestionsCorrect %>/<%= totalNumQuestions %></h4>
<h4>Time Taken: <%= elapsedTime %></h4>

<h4>Details:</h4>
<ul>
<%
for (int i = 0; i < questions.size(); i++){
	String correctAnswer = questions.get(i).getAnswer().get(i);
	String usersAnswer = usersAnswers.get(i);
	out.println("<li>Your Answer: " + usersAnswer + " Correct Answer: " + correctAnswer + " </li>");
}
%>
</ul>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>