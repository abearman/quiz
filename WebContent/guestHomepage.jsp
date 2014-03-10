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
	ArrayList<String> recentlyCreatedQuizzes = dal.getRecentlyCreatedQuizzes();
	ArrayList<String> popularQuizzes = dal.getPopularQuizzes(); 
	ArrayList<String> announcements = dal.getAllAnnouncements();
	%>  
	
	<title>Welcome Guest</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

	<h2>Welcome Guest</h2>
	
	<p><a href="index.html">Log in</a></p>
	<p><a href="createAccount.html">Create New Account</a></p>
	
	<form name="searchForUser" action="SearchForUserServlet" method="post">
		<input type="text" name="usernameToSearch">
		<input type="submit" value="Search">
	</form>
	
	<h2> Announcements: </h2>
	<ul> 
		<% 
			for (String s : announcements) {
				%> <li> <%=s%></li> <%	
			}
		%> 
	</ul>
	
	<p> See <a href="allQuizzes.jsp"> all existing quizzes </a></p>

	<h2> Recently Created Quizzes: </h2>
	<ul>
		<%
			for (int i = 0; i < recentlyCreatedQuizzes.size(); i++) {
				String quizName = recentlyCreatedQuizzes.get(i);
				String creator = dal.getCreatorName(quizName);
				%> <li> Quiz: <a href="guestQuizSummary.jsp?quizName=<%=quizName%>"><%=quizName%></a>, by <a href="guestFriendProfile.jsp?friendName=<%=creator%>"><%=creator%></a> </li> <% 
			}
		%>
	</ul>
	
	<h2> Popular Quizzes: </h2>
	<ul>
		<%
			for (int i = 0; i < popularQuizzes.size(); i++) {
				String quizName = popularQuizzes.get(i);
				%> <li> Quiz: <a href="guestQuizSummary.jsp?quizName=<%=quizName%>"><%=quizName%></a> </li> <% 
			}
		%>
	</ul>

</body>
</html>






