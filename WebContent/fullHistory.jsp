<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

<head>
	<%
		User user = (User)session.getAttribute("user");
		DAL dal = (DAL)getServletContext().getAttribute("DAL");
		String username = user.getLoginName();
		ArrayList<HistoryObject> allHistories = dal.getHistoryListForUser(username);
	%>
	<title><%= username %>'s Quiz History</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<%
	if (user.getIsAdministrator()) {
		%> Go back <a href="administratorHomepage.jsp">home</a><% 
	} else {
		%> Go back <a href="userHomepage.jsp">home</a><% 
	}%>
	
	<h2> Your Quiz History: </h2>
	
	<ul>
		<% 
		for (HistoryObject hist : allHistories) {
			%> <li> 
					<p>Quiz Name: <%= hist.getQuizName() %></p>
					<p>Score: <%= hist.getNumQuestionsCorrect() %></p> 
					<p>Time Elapsed: <%= hist.getElapsedTime() %></p>
					<p>Date: <%= hist.getDate() %></p>
			
			 </li> <%
		} 
		%>
	</ul>

</body>
</html>