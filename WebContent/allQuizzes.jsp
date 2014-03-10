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
		String username = null;
		if (user != null) username = user.getLoginName();
		ArrayList<Quiz> allQuizzes = dal.getAllQuizzes();
	%>
	<title><%= username %>'s Friends</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<%
	if (username != null) {
		if (user.getIsAdministrator()) {
			%> Go back <a href="administratorHomepage.jsp">home</a><% 
		} else {
			%> Go back <a href="userHomepage.jsp">home</a><% 
		}
	} else {
		%> Go back <a href="guestHomepage.jsp">home</a><% 
	}%>
	
	<ul>
		<% 
		for (Quiz quiz : allQuizzes) {
			String quizName = quiz.getQuizName();
			String creator = quiz.getCreatorName();
			if (username != null) {
				%> <li> Quiz: <a href="quizSummary.jsp?quizName=<%=quizName%>"><%=quizName%></a>, by <a href="friendProfile.jsp?friendName=<%=creator%>"><%=creator%></a> </li> <%
			} else {
				%> <li> Quiz: <a href="guestQuizSummary.jsp?quizName=<%=quizName%>"><%=quizName%></a>, by <a href="guestFriendProfile.jsp?friendName=<%=creator%>"><%=creator%></a> </li> 
			<%}
		}%>
	</ul>

</body>
</html>


