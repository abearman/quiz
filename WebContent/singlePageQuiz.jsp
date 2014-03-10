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
	
	
	<div class="container">
		<h3><%= quiz.getQuizName() %> by <%= quiz.getCreatorName() %></h3>
		<form action ="CorrectSinglePageQuizServlet" class="form-quiz" method="post">
			<% 
				ArrayList<Question> questions = quiz.getQuestions();
				for(int i =0; i<questions.size(); i++)
				{
					Question q = questions.get(i);
					if(q.getQuestionType()==Question.QUESTION_RESPONSE)
					{
						out.print("<h4>Question ");
						out.print((i+1)+": ");
						out.print(q.getQuestion()+"</h4>");
						out.print("Your Answer: <input class=\"span2\" type=\"text\" name=\"answer"+i+"\"/>");
					}
					if(q.getQuestionType()==Question.FILL_IN_THE_BLANK)
					{
						out.print("<h4>Question ");
						out.print((i+1)+": ");
						out.print(q.getQuestion()+"</h4>");
						out.print("Your Answer: <input class=\"span2\" type=\"text\" name=\"answer"+i+"\"/>");	
					}
					if(q.getQuestionType()==Question.MULTIPLE_CHOICE)
					{
						out.print("<h4>Question ");
						out.print((i+1)+": ");
						out.print(q.getQuestion()+"</h4>");
						ArrayList<String> choices = ((MultipleChoice)q).getChoices();
						for (int c = 0; c < choices.size(); c++){
							out.println("<input type=\"radio\" name=\"answer" +i+ "\" value=\"" + choices.get(c) + "\"/> " + choices.get(c) + "<br>");
						}
					}
					if(q.getQuestionType()==Question.PICTURE_RESPONSE)
					{
						out.print("<h4>Question ");
						out.print((i+1)+": ");
						out.print(q.getQuestion()+"</h4>");
						out.print("<img src=\""+((PictureResponse)q).getImageURL()+"\" height=\"250\" width=\"250\"><br>");
						out.print("Your Answer: <input class=\"span2\" type=\"text\" name=\"answer"+i+ "\" />");
					}
					if(q.getQuestionType()==Question.MultiAnswer_MultipleChoice)
					{
						out.print("<h4>Question ");
						out.print((i+1)+": ");
						out.print(q.getQuestion()+"</h4>");
						ArrayList<String> choices = ((MultiAnswerMultipleChoice)q).getChoices();
						for (int c = 0; c < choices.size(); c++){
							out.println("<input type=\"checkbox\" name=\"answer" +i+ "\" value=\"" + choices.get(c) + "\"/> " + choices.get(c) + "<br>");
						}
					}
				}
			%>
			<br><input type="submit" class="btn btn-primary" value="Done">
		</form>
	</div>
</body>
</html>