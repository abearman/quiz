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
	String username = (String) request.getParameter("username");
	DAL dal = (DAL) request.getServletContext().getAttribute("DAL");
	ArrayList<String> announcements = dal.getAllAnnouncements();
	String achievements = dal.getUserAchievements(username);
	%> 
	
	<title>Welcome <%= username %></title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

	<h2>Welcome <%= username %></h2>

</body>
</html>