<%@ page import="java.util.*, quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>
	<%
	Quiz quiz = (Quiz)request.getSession().getAttribute("quiz");
	out.print(quiz.getQuizName()); %>
	</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>


<body>
	<h3><%= quiz.getQuizName() %> by <%= quiz.getCreatorName() %></h3>
	<form action ="CorrectSinglePageQuizServlet" method="post">
		<% 
			ArrayList<Question> questions = quiz.getQuestions();
			for(int i =0; i<questions.size(); i++)
			{
				Question q = questions.get(i);
				if(q.getQuestionType()==Question.QUESTION_RESPONSE)
				{
					out.print("<h4>Question ");
					out.print(i+": ");
					out.print(q.getQuestion()+"</h4>");
					out.print("Your Answer: <input type=\"text\" name=\"answer"+i+"\"/>");
				}
				if(q.getQuestionType()==Question.FILL_IN_THE_BLANK)
				{
					out.print("<h4>Question ");
					out.print(i+": ");
					out.print(q.getQuestion()+"</h4>");
					out.print("Your Answer: <input type=\"text\" name=\"answer"+i+"\"/>");	
				}
				if(q.getQuestionType()==Question.MULTIPLE_CHOICE)
				{
					out.print("<h4>Question ");
					out.print(i+": ");
					out.print(q.getQuestion()+"</h4>");
					ArrayList<String> choices = ((MultipleChoice)q).getChoices();
					for (int c = 0; c < choices.size(); c++){
						out.println("<input type=\"radio\" name=\"answer" +i+ "\" value=\"" + choices.get(c) + "\"/> " + choices.get(c) + "<br>");
					}
				}
				if(q.getQuestionType()==Question.PICTURE_RESPONSE)
				{
					out.print("<h4>Question ");
					out.print(i+": ");
					out.print(q.getQuestion()+"</h4>");
					out.print("<img src=\""+((PictureResponse)q).getImageURL()+"\">");
					out.print("Your Answer: <input type=\"text\" name=\"answer\" />");
				}
			}
		%>
		<br><input type="submit" value="Done">
	</form>

</body>
</html>