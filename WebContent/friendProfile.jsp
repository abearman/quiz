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
	String username = (String)session.getAttribute("loginName");
	User user = (User)session.getAttribute("user");
	
	DAL dal = (DAL) request.getServletContext().getAttribute("DAL");
	ArrayList<String> userRecentlyCreatedQuizzes = dal.getUserRecentlyCreatedQuizzes(friendName);
	ArrayList<String> userRecentlyTakenQuizzes = dal.getUserRecentlyTakenQuizzes(friendName);
	String achievements = dal.getUserAchievements(friendName);
	ArrayList<String> friends = dal.getFriendListForUser(friendName);
	boolean hasNewMessages = dal.userHasNewMessages(username);
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
	      <div><a class="navbar-brand" href="/" style="color:white; font-family:'Fugaz One', cursive; font-size:40px">Quizzler</a></div>
	    </div>
	
		<form class="navbar-form navbar-left" role="search" name="searchForUser" action="SearchForUserServlet" method="post">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search for friends" name="usernameToSearch">
				<button type="submit" class="btn btn-default">Submit</button>
				
			</div>	
		</form>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
	          <ul class="dropdown-menu">
	            <li><a href="#">Action</a></li>
	            <li><a href="#">Another action</a></li>
	            <li><a href="#">Something else here</a></li>
	            <li class="divider"></li>
	            <li><a href="#">Separated link</a></li>
	            <li class="divider"></li>
	            <li><a href="#">One more separated link</a></li>
	          </ul>
	        </li>
	      </ul>
	      
	      <ul class="nav navbar-nav navbar-right">
	        <li class="active"><a href="friends.jsp" style="margin:0; padding:0"><img src="friends-icon.jpg" height="50px" height="50px"/></a></li>
	        <li class="active"><a href="messages.jsp" style="margin:0; margin-left:10px; padding:0"><img src="messages.jpg" height="50px" height="50px"/></a></li>
	        
	        <%if (hasNewMessages) {
	        	%> <li class="active"><a href="messages.jsp" style="margin:0; margin-left:10px; padding:0"><img src="exclamation-red.jpg" height="50px" height="50px"/></a></li> <% 
	        } else {%>
	        	<li class="active"><a href="messages.jsp" style="margin:0; margin-left:10px; padding:0"><img src="exclamation.jpg" height="50px" height="50px"/></a></li>
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
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>

	<h2><%= friendName %>'s Profile</h2>
	
		<a href="sendMessage.jsp?toUser=<%=friendName%>&fromUser=<%=username%>"><button> Send Message </button></a>
	
	<%
	if (!friendName.equals(username)) {
		if(dal.getFriendListForUser(username).contains(friendName)) {%> 
			<form name="removeFriend" action ="RemoveFriendServlet" method="post">
				<input type="hidden" name="user2" value="<%=friendName%>">
				<input type="submit" value="Remove friend">
			</form>
		<%} else if (dal.userHasPendingFriendRequestForThisFriend(username, friendName)) {
			%><button> Pending Friend Request </button>
		<%} else {%>
			<form name="addFriend" action ="AddFriendServlet" method="post">
				<input type="hidden" name="user2" value="<%=friendName%>">
				<input type="submit" value="Add friend!">
			</form>
		<%}
	}
	%>

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
				%> <li> Quiz: <a href="quizSummary.jsp?quizName=<%=quizName%>"> <%=quizName%> </a> </li> <% 
			}
		%>
	</ul>
	
	<h2> My Recently Taken Quizzes: </h2>
	<ul>
		<%
			for (int i = 0; i < userRecentlyTakenQuizzes.size(); i++) {
				String quizName = userRecentlyTakenQuizzes.get(i);
				%> <li> Quiz: <a href="quizSummary.jsp?quizName=<%=quizName%>"> <%=quizName%> </a> </li> <% 
			}
		%>
	</ul>
	
	<h2> <%=friendName%>'s Friends: </h2>
	<ul>
		<%
			for (int i = 0; i < friends.size(); i++) {
				String friend = friends.get(i);
				%> <li> <a href="friendProfile.jsp?friendName=<%=friend%>"> <%=friend%> </a> </li> <%
			}
		%>
	</ul>

</body>
</html>






