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
	String userName = (String)session.getAttribute("loginName");
	
	DAL dal = (DAL) request.getServletContext().getAttribute("DAL");
	ArrayList<String> userRecentlyCreatedQuizzes = dal.getUserRecentlyCreatedQuizzes(friendName);
	ArrayList<String> userRecentlyTakenQuizzes = dal.getUserRecentlyTakenQuizzes(friendName);
	String achievements = dal.getUserAchievements(friendName);
	ArrayList<String> friends = dal.getFriendListForUser(friendName);
	%>  
	
	<title> <%=friendName%>'s Profile </title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

	<h2><%= friendName %>'s Profile</h2>
	
		<a href="sendMessage.jsp?toUser=<%=friendName%>&fromUser=<%=userName%>"><button> Send Message </button></a>
	
	<%
	if (!friendName.equals(userName)) {
		if(dal.getFriendListForUser(userName).contains(friendName)) {%> 
			<form name="removeFriend" action ="RemoveFriendServlet" method="post">
				<input type="hidden" name="user2" value="<%=friendName%>">
				<input type="submit" value="Remove friend">
			</form>
		<%} else if (dal.userHasPendingFriendRequestForThisFriend(userName, friendName)) {
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
	
	<%
	if (dal.isUserAdmin(userName)) {
		%> Go back <a href="administratorHomepage.jsp">home</a><% 
	} else {
		%> Go back <a href="userHomepage.jsp">home</a><% 
	}%>

</body>
</html>






