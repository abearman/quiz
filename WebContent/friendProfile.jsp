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
	String name = request.getParameter("friendName");
	DAL dal = (DAL) request.getServletContext().getAttribute("DAL");
	User user = dal.getUser(name);
	ArrayList<String> userRecentlyCreatedQuizzes = dal.getUserRecentlyCreatedQuizzes(name);
	ArrayList<String> userRecentlyTakenQuizzes = dal.getUserRecentlyTakenQuizzes(name);
	boolean[] achievements = user.getAchievements();
	ArrayList<String> friends = dal.getFriendListForUser(name);
	%>  
	
	<title> <%= name %>'s Profile </title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

	<h2><%= name %>'s Profile</h2>
	

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
	
	<h2> <%=name%>'s Recently Created Quizzes: </h2>
	<ul>
		<%
			for (int i = 0; i < userRecentlyCreatedQuizzes.size(); i++) {
				%> <li> Quiz: <%= userRecentlyCreatedQuizzes.get(i) %></li> <% 
			}
		%>
	</ul>
	
	<h2> <%=name%>'s Recently Taken Quizzes: </h2>
	<ul>
		<%
			for (int i = 0; i < userRecentlyTakenQuizzes.size(); i++) {
				%> <li> Quiz: <%= userRecentlyTakenQuizzes.get(i) %></li> <% 
			}
		%>
	</ul>
	
	<h2> <%=name%>'s Friends: </h2>
	<ul>
		<%
			for (int i = 0; i < friends.size(); i++) {
				String friend = friends.get(i);
				%> <li> <a href="friendProfile.jsp?friendName=<%=friend%>"> <%=friend%> </a> </li> <%
			}
		%>
	</ul>
	
	<%
	if (user.getIsAdministrator()) {
		%> Go back <a href="administratorHomepage.jsp">home</a><% 
	} else {
		%> Go back <a href="userHomepage.jsp">home</a><% 
	}%>

</body>
</html>






