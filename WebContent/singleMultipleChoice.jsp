<%@ page import="java.util.*, quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Multiple Choice</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
	
<%

Quiz quiz = (Quiz)request.getSession().getAttribute("quiz");
int questionNum = quiz.getCurrentQuestionNum();
Question thisQuestion = quiz.getQuestions().get(questionNum);
ArrayList<String> choices = ((MultipleChoice)thisQuestion).getChoices();

%>

<h3><%= quiz.getQuizName() %> by <%= quiz.getCreatorName() %></h3>
<h4>Question <%= quiz.getCurrentQuestionNum()+1 %>: <%= thisQuestion.getQuestion() %></h4>

<form action="UpdateAnswersServlet" method="post">
<%
	for (int i = 0; i < choices.size(); i++){
		out.println("<input type=\"radio\" name=\"answer\" value=\"" + choices.get(i) + "\"/> " + choices.get(i) + "<br>");
	}
%>
	<input type = "submit" class="btn btn-primary" />
</form>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>