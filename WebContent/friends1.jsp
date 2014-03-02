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
		String username = user.getLoginName();
	%>
	<title><%= username %>'s Friends</title>
	
</head>
<body>

	<%
	if (user.getIsAdministrator()) {
		%> Go back <a href="administratorHomepage.jsp">home</a><% 
	} else {
		%> Go back <a href="userHomepage.jsp">home</a><% 
	}%>

</body>
</html>