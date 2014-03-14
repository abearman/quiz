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
	ArrayList<Message> messages = dal.getSevenMostRecentUserMessages(user);
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
	
	<div class="row">
		<div class="col-md-2 col-md-offset-1">
			<h3><a href="friendProfile.jsp?friendName=<%=friendName%>"><%= friendName %></a></h3>
			<button class="btn btn-primary"><a style="color:white; text-decoration:none;" href="sendMessage.jsp?toUser=<%=friendName%>&fromUser=<%=username%>"> Send Message </a></button>
	
		<%
		if (!friendName.equals(username)) {
			if(dal.getFriendListForUser(username).contains(friendName)) {%> 
				<form name="removeFriend" action ="RemoveFriendServlet" method="post" style="padding-top:5px; padding-bottom:5px">
					<input type="hidden" name="user2" value="<%=friendName%>">
					<button type="submit" class="btn btn-primary"> Remove friend </button>
				</form>
			<%} else if (dal.userHasPendingFriendRequestForThisFriend(username, friendName)) {
				%><button class="btn btn-primary"> Pending Friend Request </button>
			<%} else {%>
				<form name="addFriend" action ="AddFriendServlet" method="post" style="padding-top:5px; padding-bottom: 5px">
					<input type="hidden" name="user2" value="<%=friendName%>">
					<button type="submit" class="btn btn-primary"> Add friend!</button>
				</form>
			<%}
		}%>
		</div>
		
		<div class="col-md-6 well">
			<h3> Achievements: </h3>
			<%for (int i = 0; i < achievements.length(); i++) {
				if (achievements.charAt(i) == '1') {%>
					<div style="padding-left:10px">
						<img src="<%=Achievements.achievementsImgs[i]%>" height="30px" width="30px" />
						<%=Achievements.achievements[i]%>	
					</div> <% ;
				}
			}%> 
			
			<h3> <%=friendName%>'s Friends: </h3>
			<ul>
				<%
					for (int i = 0; i < friends.size(); i++) {
						String friend = friends.get(i);
						%> <li> <a href="friendProfile.jsp?friendName=<%=friend%>"> <%=friend%> </a> </li> <%
					}
				%>
			</ul>
			
			<h3> <%=friendName%>'s Recently Created Quizzes: </h3> 
				<%for (int i = 0; i < userRecentlyCreatedQuizzes.size(); i++) {
					String quizName = userRecentlyCreatedQuizzes.get(i);
					%> <div><a href="quizSummary.jsp?quizName=<%=quizName%>"> <%=quizName%> </a> </div> <% 
				}%>
				
			<h3> <%=friendName%>'s Recently Taken Quizzes: </h3>
				<%for (int i = 0; i < userRecentlyTakenQuizzes.size(); i++) {
						String quizName = userRecentlyTakenQuizzes.get(i);
						%> <div><a href="quizSummary.jsp?quizName=<%=quizName%>"> <%=quizName%> </a></div> <% 
				}%>

		</div>
	
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> 
	<script type="text/javascript" src="http://twitter.github.com/bootstrap/assets/js/bootstrap-dropdown.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>






