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
	User user = (User)session.getAttribute("user");
	ArrayList<Message> messages = dal.getSevenMostRecentUserMessages(user);
	String username = (String)session.getAttribute("loginName");
	boolean hasNewMessages = dal.userHasNewMessages(username);
	
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
	        <li class="active"><a href="friends.jsp" style="margin:0; padding:0"><img src="friends-icon.jpg" height="50px" height="50px"/></a></li>
	        <li class="active"><a href="messages.jsp" style="margin:0; margin-left:10px; padding:0"><img src="messages.jpg" height="50px" height="50px"/></a></li>
	        
	        <%if (hasNewMessages) {
	        	%> 
				<li class="navbar-form">
				<div class="dropdown">
				  <button class="btn dropdown-toggle btn-danger" type="button" id="dropdownMenu1" data-toggle="dropdown"> Notifications <span class="caret"></span> </button>
				  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
				  	
				  	<% for (int i = 0; i < messages.size(); i++) {
				  		%><li role="presentation">
				  			<a role="menuitem" tabindex="-1" href="messages.jsp">
				  				<%
				  				Message message = messages.get(i);
				  				String type = message.getMessageType();
				  				String fromUser = message.getFromUser();%>
				  				<%=type%> from <%=fromUser%> 
				  			</a>
				  		</li>
				  	<%}%>
				    <li role="presentation" class="divider"></li>
				    <li role="presentation"><a role="menuitem" tabindex="-1" href="messages.jsp">See All</a></li>
				  </ul>
				</div>
			</li>
				<% 
	        } else {%>
	        	<li class="navbar-form">
				<div class="dropdown">
				  <button class="btn dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown"> Notifications <span class="caret"></span> </button>
				  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
				  	
				  	<% for (int i = 0; i < messages.size(); i++) {
				  		%><li role="presentation">
				  			<a role="menuitem" tabindex="-1" href="messages.jsp">
				  				<%
				  				Message message = messages.get(i);
				  				String type = message.getMessageType();
				  				String fromUser = message.getFromUser();%>
				  				<%=type%> from <%=fromUser%> 
				  			</a>
				  		</li>
				  	<%}%>
				    <li role="presentation" class="divider"></li>
				    <li role="presentation"><a role="menuitem" tabindex="-1" href="messages.jsp">See All</a></li>
				  </ul>
				</div>
			</li>
			<%}%>
			
			<%if (user.getIsAdministrator()) {
				%> <li class="active"><a href="administratorHomepage.jsp">Home</a></li><%
			} else {
				%> <li class="active"><a href="userHomepage.jsp">Home</a></li>
			<%}%>
			
	        <li class="active"><a href="friendProfile.jsp?friendName=<%=username%>"><%=username%></a></li>
	        <li style="padding-right:10px">
	        	<form class="navbar-form navbar-right" name="logOut" action="LogOutServlet" method="post">
					<button type="submit" class="btn btn-default">Log Out</button>
				</form>
	        </li>
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
		
		
		<h4>Your Past Performance on <%= quizName %>:</h4>
		<table class="table table-striped">
			<tr>
				<th>Date</th>
				<th>Time Taken</th>
				<th>Number of Questions Correct</th>
			</tr>
		<%
		ArrayList<HistoryObject> usersHistory = dal.getHistoryListForUser(user.getLoginName());
		
		ArrayList<HistoryObject> usersHistoryCappedAt5 = new ArrayList<HistoryObject>();
		int count = 0;
		int index = 0;
		while (count < 5 && index < usersHistory.size()){
			HistoryObject history = usersHistory.get(index);
			if (history.getQuizName().equals(quizName)){
				usersHistoryCappedAt5.add(history);
				count++;
			}
			index++;
		}
		for (int i = usersHistoryCappedAt5.size() - 1; i>=0; i--){
			HistoryObject history = usersHistoryCappedAt5.get(i);
			double elapsedTimeInSeconds = ((Long)history.getElapsedTime()).doubleValue()/1000;
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(history.getDate());
			out.println("<tr>");
			out.println("<td>" + currentTime + "</td>");
			out.println("<td>" + elapsedTimeInSeconds + " sec</td>");
			out.println("<td>" + history.getNumQuestionsCorrect() + "</td>");
			out.println("</tr>");
		}
		%>
		</table>
		
		
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
		
		<form action="TakeQuizServlet" method="post">
		<input type="hidden" name="quizName" value="<%= quizName %>"/>
		<input type = "submit" class="btn btn-primary" value = "Take This Quiz">
		</form>
		<br>
	
		<%
			String creatorName = quiz.getCreatorName();
			if (user.getLoginName().equals(creatorName)){
				out.println("<form action=\"EditQuizServlet\" method=\"post\">");
				out.println("<input type=\"hidden\" name=\"editQuizName\" value=\"" + quizName + "\"/>");
				out.println("<input type = \"submit\" class=\"btn btn-primary\" value = \"Edit This Quiz\">");
				out.println("</form>");
			}
		%>
	
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> 
	<script type="text/javascript" src="http://twitter.github.com/bootstrap/assets/js/bootstrap-dropdown.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>