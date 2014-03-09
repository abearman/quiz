<%@ page import="java.util.*, quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Question Response</title>

	<style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #000000;
      }
      
      .form-quiz {
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
    </style>
	
	<link href="../assets/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href='http://fonts.googleapis.com/css?family=Fugaz+One' rel='stylesheet' type='text/css'>
</head>

<body style="background-color:#f5f5f5">

	<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <div><a class="navbar-brand" style="color:white; font-family:'Fugaz One', cursive; font-size:40px">Quizzler</a></div>
	    </div>
	  </div><!-- /.container-fluid -->
	</nav>

	<%
		Quiz quiz = (Quiz)request.getSession().getAttribute("quiz");
		int questionNum = quiz.getCurrentQuestionNum();
		Question thisQuestion = quiz.getQuestions().get(questionNum);
		ArrayList<String> choices = ((MultipleChoice)thisQuestion).getChoices();
	%>
	
	<div class="form-quiz" style="width:400px">

		<h3><%= quiz.getQuizName() %> by <%= quiz.getCreatorName() %></h3>
		<h4>Question <%= quiz.getCurrentQuestionNum()+1 %>: <%= thisQuestion.getQuestion() %></h4>

		<form action="" method="post">
		<%
			for (int i = 0; i < choices.size(); i++){
				out.println("<input type=\"radio\" name=\"answer\" value=\"" + choices.get(i) + "\"/> " + choices.get(i) + "<br>");
			}
		%>
		</form>

		<h5>Sorry, your answer was incorrect.</h5>
		<h5>Correct Answer: <%= thisQuestion.getAnswer().get(0) %></h5>

		<%
		if (questionNum == quiz.getQuestions().size()-1){
			out.println("<form action=\"DoneWithQuizServlet\" method=\"post\">");
			out.println("<input type=\"submit\" class=\"btn btn-primary\" value=\"Done\">");
		}else{
			out.println("<form action=\"NextQuestionServlet\" method=\"post\">");
			out.println("<input type=\"submit\" class=\"btn btn-primary\" value=\"Next Question\">");
		}
		%>

	</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>