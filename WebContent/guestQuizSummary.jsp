<%@ page import="java.util.*, java.text.*, quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<%
	String quizName = request.getParameter("quizName");
	DAL dal = (DAL)request.getServletContext().getAttribute("DAL");
	Quiz quiz = new Quiz(dal, quizName);
	int numQuestions = quiz.getQuestions().size();
	%>
	
	<title><%= quizName %></title>
	<style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #000000;
      }
      
      blockquote{
      	background:#f5f5f5;
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
	
	<%
	
	String descriptionOfQuiz = quiz.getDescriptionOfQuiz();
	String quizCreator = quiz.getCreatorName();
	
	double statistics[] = quiz.getStatisticsSummary();
	double averageScore, medianScore, averageTime, averageTimeInSeconds;
	if (Double.isNaN(statistics[0])){
		averageScore = 0.0;
		medianScore = 0.0;
		averageTime = 0.0;
		averageTimeInSeconds = 0.0;
	}else{
		averageScore = statistics[0];
		medianScore = statistics[1];
		averageTime = statistics[2];
		averageTimeInSeconds = averageTime/1000;
	}
	DecimalFormat df = new DecimalFormat("#.##");
	String averageScoreString = df.format(averageScore);
	String medianScoreString = df.format(medianScore);
	String averageTimeString = df.format(averageTimeInSeconds);
	%>
	
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
	
		<form class="navbar-form navbar-left" role="search" name="searchForUser" action="SearchForUserServlet" method="post">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search for friends" name="usernameToSearch">
				<button type="submit" class="btn btn-default">Submit</button>
			</div>	
		</form>
		
	      <ul class="nav navbar-nav navbar-right">
	      	<li class="active"><a href="guestHomepage.jsp">Home</a></li>
	      	<li class="active"><a href="index.html">Log In</a>
	      	<li class="active"><a href="createAccount.html">Create Account</a>
	      </ul>
	  </div><!-- /.container-fluid -->
	</nav>
	
	<div class="form-quiz" style="width:1000px">

		<h3><%= quizName %> by <%= quizCreator %></h3>
		<h4><%= descriptionOfQuiz %></h4>
		
		<blockquote>
			<ul class="list-inline">
				<li><strong>Average Score</strong></li>
				<li><%= averageScoreString %>/<%= numQuestions %></li>
			</ul>
			<ul class="list-inline">
				<li><strong>Median Score</strong></li>
				<li><%= medianScoreString %>/<%= numQuestions %></li>
			</ul>
			<ul class="list-inline">
				<li><strong>Average Time</strong></li>
				<li><%= averageTimeString %> sec</li>
			</ul>	
		</blockquote>		
		
		<h4>Top Performers on <%= quizName %></h4>
		<table class="table table-striped">
			<tr>
				<th>Name</th>
				<th>Time Taken</th>
				<th>Number of Questions Correct</th>
			</tr>
		<%
		ArrayList<TopScorer> topScorers = quiz.getTopScorers();
		int topScorerUpperLimit = topScorers.size();
		if (topScorers.size() > 5) topScorerUpperLimit = 5;
		for (int i = 0; i < topScorerUpperLimit; i++){
			TopScorer topScorer = topScorers.get(i);
			double elapsedTimeInSeconds = (topScorer.getTimeTaken())/1000;
			out.println("<tr>");
			out.println("<td>" + topScorer.getLoginName() + "</td>");
			out.println("<td>" + elapsedTimeInSeconds + " sec</td>");
			out.println("<td>" + topScorer.getNumCorrectQuestions() + "</td>");
			out.println("</tr>");
		}
		%>
		</table>
		
		
		<h4>Top Performers in the last day on <%= quizName %></h4>
		<table class="table table-striped">
			<tr>
				<th>Name</th>
				<th>Time Taken</th>
				<th>Number of Questions Correct</th>
			</tr>
		<%
		ArrayList<TopScorer> topScorersPastDay = quiz.getTopScorersPastDay();
		for (int i = 0; i < topScorersPastDay.size(); i++){
			TopScorer topScorerPastDay = topScorersPastDay.get(i);
			double elapsedTimeInSeconds = (topScorerPastDay.getTimeTaken())/1000;
			out.println("<tr>");
			out.println("<td>" + topScorerPastDay.getLoginName() + "</td>");
			out.println("<td>" + elapsedTimeInSeconds + " sec</td>");
			out.println("<td>" + topScorerPastDay.getNumCorrectQuestions() + "</td>");
			out.println("</tr>");
		}
		%>
		</table>
		
		
		<h4>Recent Test Takers of  <%= quizName %></h4>
		<table class="table table-striped">
			<tr>
				<th>Name</th>
				<th>Time Taken</th>
				<th>Number of Questions Correct</th>
			</tr>
		<%
		ArrayList<HistoryObject> recentTestTakers = quiz.getRecentQuizTakers();
		for (int i = 0; i < recentTestTakers.size(); i++){
			HistoryObject history = recentTestTakers.get(i);
			double elapsedTimeInSeconds = ((Long)history.getElapsedTime()).doubleValue()/1000;
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String historyTime = sdf.format(history.getDate());
			out.println("<tr>");
			out.println("<td>" + history.getUserName() + "</td>");
			out.println("<td>" + elapsedTimeInSeconds + " sec</td>");
			out.println("<td>" + history.getNumQuestionsCorrect() + "</td>");
			out.println("</tr>");
		}
		%>
		</table>
		
	
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> 
	<script type="text/javascript" src="http://twitter.github.com/bootstrap/assets/js/bootstrap-dropdown.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>