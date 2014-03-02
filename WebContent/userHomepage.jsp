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
	//String username = (String) request.getParameter("username");
	User user = (User)session.getAttribute("user");
	String username = user.getLoginName();
	Webpage webpage = (Webpage)getServletContext().getAttribute("webpage");
	
	//DAL dal = (DAL) request.getServletContext().getAttribute("DAL");
	//ArrayList<String> announcements = dal.getAllAnnouncements();
	//String achievements = dal.getUserAchievements(username);
	
	boolean[] achievements = user.getAchievements();
	ArrayList<String> announcements = webpage.getAnnouncements();
	boolean hasNewMessages = user.getNewMessages();
	
	%>  
	
	<title>Welcome <%= username %></title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

	<h2>Welcome <%= username %></h2>
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
	
	<i>${hasNewMessages ? "You have new messages!" : "No new messages."}</i>
	<p>See my <a href="messages.jsp"> messages </a></p>
	<p> See my <a href="friends.jsp"> friends </a></p>

</body>
</html>






