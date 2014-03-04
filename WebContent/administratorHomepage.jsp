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
	DAL dal = (DAL) request.getServletContext().getAttribute("DAL");
	User user = (User)session.getAttribute("user");
	String username = user.getLoginName();
	
	ArrayList<String> recentlyCreatedQuizzes = dal.getRecentlyCreatedQuizzes(); 
	ArrayList<String> popularQuizzes = dal.getPopularQuizzes();
	ArrayList<String> userRecentlyCreatedQuizzes = dal.getUserRecentlyCreatedQuizzes(user.getLoginName());
	ArrayList<String> userRecentlyTakenQuizzes = dal.getUserRecentlyTakenQuizzes(user.getLoginName());
	
	boolean[] achievements = user.getAchievements();
	ArrayList<String> announcements = dal.getAllAnnouncements();
	boolean hasNewMessages = dal.userHasNewMessages(username);
	
	ArrayList<String> friends = dal.getFriendListForUser(username);
	//ArrayList<FriendRecentActivity> friendRecentActivities = dal.getFriendsRecentActivity(friends);
	
	%>  
	
	<title>Welcome <%= username %> </title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

	<h2>Welcome, admin <%= username %></h2>
	
	<form name="logOut" action="LogOutServlet" method="post">
		<input type="submit" value="Log Out"> 
	</form>
	
	<a href="createQuizPage.jsp"><button> Create quiz </button></a> 
	
	<h2> Announcements: </h2>
	<ul> 
		<% 
			for (String s : announcements) {
				%> <li> <%=s%></li> <%	
			}
		%> 
	</ul>

	<h2> Achievements: </h2>
	<ul>
		<% 
			for (int i = 0; i < achievements.length; i++) {
				if (achievements[i]) {
					%> <li> <%= Achievements.achievements[i] %></li> <% ;	
				}
			}
			%> 
	</ul>
	
	<%if (hasNewMessages) {
		%> <i> You have new messages! </i><%
	} else {%>
		<i> No new messages. </i>
	<%}%>
	
	<p>See my <a href="messages.jsp"> messages </a></p>
	<p> See my <a href="friends.jsp"> friends </a></p>

	<h2> Recently Created Quizzes: </h2>
	<ul>
		<%
			for (int i = 0; i < recentlyCreatedQuizzes.size(); i++) {
				String quizName = recentlyCreatedQuizzes.get(i);
				String creator = dal.getCreatorName(quizName);
				%> <li> Quiz: <%=quizName%>, by <a href="friendProfile.jsp?friendName=<%=creator%>"><%=creator%></a> </li> <% 
			}
		%>
	</ul>
	
	<h2> Popular Quizzes: </h2>
	<ul>
		<%
			for (int i = 0; i < popularQuizzes.size(); i++) {
				%> <li> Quiz: <%= popularQuizzes.get(i) %></li> <% 
			}
		%>
	</ul>
	
	<h2> My Recently Created Quizzes: </h2> 
	<ul>
		<%
			for (int i = 0; i < userRecentlyCreatedQuizzes.size(); i++) {
				%> <li> Quiz: <%= userRecentlyCreatedQuizzes.get(i) %></li> <% 
			}
		%>
	</ul>
	
	<h2> My Recently Taken Quizzes: </h2>
	<ul>
		<%
			for (int i = 0; i < userRecentlyTakenQuizzes.size(); i++) {
				%> <li> Quiz: <%= userRecentlyTakenQuizzes.get(i) %></li> <% 
			}
		%>
	</ul>
	
	<!-- > Ability for administrator to create announcements -->
	<form name="createAnnouncement" action="CreateAnnouncementServlet" method="post">
		Create Announcement: <input type="text" name="announcement">
		<input type="submit" value="Submit">
	</form>
	
	<!-- > Ability for administrator to remove accounts -->
	<form name="removeUserAccount" action="RemoveUserAccountServlet" method="post">
		Remove User: <input type="text" name="user">
		<input type="submit" value="Submit">
	</form>
	
	<!-- > Ability for administrator to remove quizzes -->
	<form name="removeQuiz" action="RemoveQuizServlet" method="post">
		Remove Quiz: <input type="text" name="quiz">
		<input type="submit" value="Submit">
	</form>

	<!-- > Ability for administrator to promote user to administrator -->
	<form name="promoteUserToAdmin" action="PromoteUserToAdminServlet" method="post">
		Promote User to Administrator: <input type="text" name="user">
		<input type="submit" value="Submit">
	</form>
	
	<!-- > Ability for administrator to promote clear history for particular quiz -->
	<form name="clearHistoryForQuiz" action="ClearHistoryForQuizServlet" method="post">
		Clear History For Quiz: <input type="text" name="quiz">
		<input type="submit" value="Submit">
	</form>
	
	<!-- Ability for administrator to display site statistics -->
	<h2> Site Statistics </h2>
	<ul>
		<li> Number of users: <%=dal.getNumberOfUsers() %></li>
		<li> Number of quizzes created: <%=dal.getNumberOfQuizzesCreated() %></li>
		<li> Number of quizzes taken: <%=dal.getNumberOfQuizzesTaken() %></li>
	</ul>

</body>
</html>






