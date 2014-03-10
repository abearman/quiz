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
	      </ul>
	  </div><!-- /.container-fluid -->
	</nav>
	
	<div class="container">
	
		<h3><%= quizName %> by <%= quizCreator %></h3>
		<h5><%= descriptionOfQuiz %></h5>
		
		<h4>Statistics Summary</h4>
		<ul>
		<li>Average Score: <%= averageScoreString %>/<%= numQuestions %></li>
		<li>Median Score: <%= medianScoreString %>/<%= numQuestions %></li>
		<li>Average Time: <%= averageTimeString %> sec</li>
		</ul>
		
		<h4>Top Performers on <%= quizName %></h4>
		<%
		ArrayList<TopScorer> topScorers = quiz.getTopScorers();
		out.println("<ul>");
		int topScorerUpperLimit = topScorers.size();
		if (topScorers.size() > 5) topScorerUpperLimit = 5;
		for (int i = 0; i < topScorerUpperLimit; i++){
			TopScorer topScorer = topScorers.get(i);
			double elapsedTimeInSeconds = (topScorer.getTimeTaken())/1000;
			out.println("<li><b>Name:</b> " + topScorer.getLoginName() + 
					" <b>Time taken:</b> " + elapsedTimeInSeconds + 
					" sec <b>Number of Correct Questions:</b> " + topScorer.getNumCorrectQuestions() + 
					"</li>");
		}
		out.println("</ul>");
		%>
		
		<h4>Top Performers in the last day on <%= quizName %></h4>
		<%
		ArrayList<TopScorer> topScorersPastDay = quiz.getTopScorersPastDay();
		out.println("<ul>");
		for (int i = 0; i < topScorersPastDay.size(); i++){
			TopScorer topScorerPastDay = topScorersPastDay.get(i);
			double elapsedTimeInSeconds = (topScorerPastDay.getTimeTaken())/1000;
			out.println("<li><b>Name:</b> " + topScorerPastDay.getLoginName() + 
					" <b>Time taken:</b> " + elapsedTimeInSeconds + 
					" sec <b>Number of Correct Questions:</b> " + topScorerPastDay.getNumCorrectQuestions() + 
					"</li>");
		}
		out.println("</ul>");
		%>
		
		<h4>Recent Test Takers of  <%= quizName %></h4>
		<%
		ArrayList<HistoryObject> recentTestTakers = quiz.getRecentQuizTakers();
		out.println("<ul>");
		for (int i = 0; i < recentTestTakers.size(); i++){
			HistoryObject history = recentTestTakers.get(i);
			double elapsedTimeInSeconds = ((Long)history.getElapsedTime()).doubleValue()/1000;
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String historyTime = sdf.format(history.getDate());
			out.println("<li><b>Name:</b> " + history.getUserName() +
					" <b>Date:</b> " + historyTime + 
					" <b>Time Taken:</b> " + elapsedTimeInSeconds + 
					" sec <b>Number of Questions Correct:</b> " + history.getNumQuestionsCorrect()
					+ "</li>");
		}
		out.println("</ul>"); 
		%>
		
	</div>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>