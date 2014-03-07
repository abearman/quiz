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
User user = (User)request.getSession().getAttribute("user");
Quiz quiz = (Quiz)request.getSession().getAttribute("quiz");
ArrayList<Question> questions = quiz.getQuestions();
ArrayList<String> usersAnswers = quiz.getAnswers();
long elapsedTime = (Long)request.getSession().getAttribute("elapsedTime");
double elapsedTimeInSeconds = ((Long)elapsedTime).doubleValue()/1000;
int numQuestionsCorrect = (Integer)request.getSession().getAttribute("numQuestionsCorrect");
int totalNumQuestions = questions.size();
String friendAttempt = request.getParameter("friendAttempt");

%>

<h3><%= quiz.getQuizName() %> by <%= quiz.getCreatorName() %></h3>

<h4>Score: <%= numQuestionsCorrect %>/<%= totalNumQuestions %></h4>
<h4>Time Taken: <%= elapsedTimeInSeconds %> seconds</h4>

<h4>Details:</h4>
<ul>
<%
for (int i = 0; i < questions.size(); i++){
	String correctAnswer = questions.get(i).getAnswer().get(0);
	String usersAnswer = usersAnswers.get(i);
	out.println("<li><b>Your Answer:</b> " + usersAnswer + " <b>Correct Answer:</b> " + correctAnswer + " </li>");
}
%>
</ul>


<div>
	<form name ="frm" action ="SendMessageServlet" method = "post">
			<input type="hidden" name="messageType" value ="ChallengeMessage">
			<input type="hidden" name="quiz" value=<%=quiz.getQuizName()%>>
			<input type="hidden" name="fromUser" value=<%=user.getLoginName()%>>
			<h5>Challenge a friend to take this quiz!<input type = "text" name="toUser"></h5>
		<input type = "submit" value = "Challenge"/>
		You are not friends with the user <%=friendAttempt%>. Try again!
	</form>
</div>
		


	<%
	if (user.getIsAdministrator()) {
		%> Go back <a href="administratorHomepage.jsp">home</a><% 
	} else {
		%> Go back <a href="userHomepage.jsp">home</a><% 
	}%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>