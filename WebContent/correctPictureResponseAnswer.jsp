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
//Quiz quiz = (Quiz)request.getSession().getAttribute("quiz");
Quiz quiz = new Quiz(new DAL(), "dummyQuiz","another quiz",true,true,true,true,"Pavitra",new Date(),5);
ArrayList<String> answers = new ArrayList<String>();
answers.add("answer1"); answers.add("answer2"); answers.add("answer3");
quiz.addQuestion(new Question("Who was the first president?",answers,0));
quiz.addQuestion(new Question("Who was the second president?",answers,1));
quiz.addQuestion(new Question("Who was the third president?",answers,2));

int questionNum = quiz.getCurrentQuestionNum();
Question thisQuestion = quiz.getQuestions().get(questionNum);
%>

<h3><%= quiz.getQuizName() %> by <%= quiz.getCreatorName() %></h3>
<h4>Question <%= quiz.getCurrentQuestionNum()+1 %>: <%= thisQuestion.getQuestion() %></h4>
<img src="<%= ((PictureResponse)thisQuestion).getImageURL() %>" height="250" width="250"><br>

<form action="" method="post">
	Your Answer: <input type="text" name="answer" />
</form>

<h5>Your Answer: <%= quiz.getAnswers().get(quiz.getCurrentQuestionNum()) %></h5>
<h5>Your answer was correct!</h5>

<%
if (questionNum == quiz.getQuestions().size()-1){
	out.println("<form action=\"DoneWithQuizServlet\" method=\"post\">");
	out.println("<input type=\"submit\" class=\"btn btn-primary\" value=\"Done\">");
}else{
	out.println("<form action=\"NextQuestionServlet\" method=\"post\">");
	out.println("<input type=\"submit\" class=\"btn btn-primary\" value=\"Next Question\">");
}
%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>