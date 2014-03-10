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
	String loginName = (String)session.getAttribute("loginName");
	DAL dal = (DAL)getServletContext().getAttribute("DAL");
	String name = request.getParameter("usernameToSearch");
	%>  
	
	<title> Profile Not Found </title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

	<p> Sorry, we couldn't find <b><%=name%>'s</b> profile :( </p>	

	<%
	if (loginName != null) {
		if (dal.isUserAdmin(loginName)) {
			%> Go back <a href="administratorHomepage.jsp">home</a><% 
		} else {
			%> Go back <a href="userHomepage.jsp">home</a><% 
		}
	} else {
		%> Go back <a href="guestHomepage.jsp">home</a><% 
	}%>
	
	

</body>
</html>






