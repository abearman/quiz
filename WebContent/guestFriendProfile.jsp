<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<%
	String friendName = request.getParameter("friendName");
	
	DAL dal = (DAL) request.getServletContext().getAttribute("DAL");
	ArrayList<String> userRecentlyCreatedQuizzes = dal.getUserRecentlyCreatedQuizzes(friendName);
	ArrayList<String> userRecentlyTakenQuizzes = dal.getUserRecentlyTakenQuizzes(friendName);
	String achievements = dal.getUserAchievements(friendName);
	ArrayList<String> friends = dal.getFriendListForUser(friendName);
	%>  
	
	<title> <%=friendName%>'s Profile </title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href='http://fonts.googleapis.com/css?family=Fugaz+One' rel='stylesheet' type='text/css'>
</head>

<body>

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

	<h2><%= friendName %>'s Profile</h2>

	<h2> Achievements: </h2>
	<ul>
		<% 
			for (int i = 0; i < achievements.length(); i++) {
				if (achievements.charAt(i) == '1') {
					%> <li> <%= Achievements.achievements[i] %></li> <% ;	
				}
			}
			%> 
	</ul>
	
	<h2> My Recently Created Quizzes: </h2> 
	<ul>
		<%
			for (int i = 0; i < userRecentlyCreatedQuizzes.size(); i++) {
				String quizName = userRecentlyCreatedQuizzes.get(i);
				%> <li> Quiz: <a href="guestQuizSummary.jsp?quizName=<%=quizName%>"> <%=quizName%> </a> </li> <% 
			}
		%>
	</ul>
	
	<h2> My Recently Taken Quizzes: </h2>
	<ul>
		<%
			for (int i = 0; i < userRecentlyTakenQuizzes.size(); i++) {
				String quizName = userRecentlyTakenQuizzes.get(i);
				%> <li> Quiz: <a href="guestQuizSummary.jsp?quizName=<%=quizName%>"> <%=quizName%> </a> </li> <% 
			}
		%>
	</ul>
	
	<h2> <%=friendName%>'s Friends: </h2>
	<ul>
		<%
			for (int i = 0; i < friends.size(); i++) {
				String friend = friends.get(i);
				%> <li> <a href="guestFriendProfile.jsp?friendName=<%=friend%>"> <%=friend%> </a> </li> <%
			}
		%>
	</ul>

</body>
</html>






