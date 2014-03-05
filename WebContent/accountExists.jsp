<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<%
	String username = (String)request.getParameter("username");
	%>
	
	<title>Create Account</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<h2>Sorry, the name <%= username %> is already in use.</h2>
	<h3>Please enter another name and password.</h3>

	<form action="AccountCreationServlet" method="post">
	<p>User Name: <input type="text" name="username" /><br>
	Password: <input type="text" name="password" />
	<input type = "submit" /></p>
	</form>

</body>
</html>