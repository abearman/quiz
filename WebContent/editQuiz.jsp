<%@ page import="java.util.*, java.text.*, quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Edit Quiz</title>
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
		Quiz quiz = (Quiz)request.getSession().getAttribute("editQuiz");
		String quizName = quiz.getQuizName();
		DAL dal = (DAL)request.getServletContext().getAttribute("DAL");
		ArrayList<Question> questions = quiz.getQuestions();
		int numQuestions = questions.size();
		request.getSession().setAttribute("editQuizQuestions",questions);
	%>
	
	<div class="form-quiz" style="width:1100px">
		<h2><%= quizName %></h2>
		<h4>Quiz Details:</h4>
		<ul>
		<%
			for (int i = 0; i < numQuestions; i++){
				out.println("<li>" + (i+1) + ". " + questions.get(i).getQuestion() + "</li>");
			}
		%>
		</ul>
		
		<form action="RemoveQuestionServlet" method="post">
		<br>Remove Question #: <input type="text" class="span2" name="answer" />
		<input type = "submit" class="btn btn-primary"/>
		</form>
	
	</div>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>