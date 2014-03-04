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
		User user = (User)session.getAttribute("user");
		String username = user.getLoginName();
		DAL dal = (DAL)getServletContext().getAttribute("DAL");
		ArrayList<Message> friendRequestMessages = dal.getFriendRequestMessages(username);
		//ArrayList<Message> otherMessages = dal.getUserMessages(user);
	%>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title><%= username %>'s Messages</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<%
	if (user.getIsAdministrator()) {
		%> Go back <a href="administratorHomepage.jsp">home</a><% 
	} else {
		%> Go back <a href="userHomepage.jsp">home</a><% 
	}%>
	
	<%for (Message message : friendRequestMessages) {
		FriendRequestMessage friendRequest = (FriendRequestMessage) message;
		String messageStr = friendRequest.getMessage();
		String requestor = friendRequest.getFromUser();
		String acceptor = friendRequest.getToUser(); %> 
		<p><%=messageStr%></p>
		<form name = "friendAccept" action="AcceptFriendRequestServlet" method="post">
			<input type="hidden" name="requestor" value="<%=requestor%>">
			<input type="hidden" name="acceptor" value="<%=acceptor%>">
			<input type="submit" value="Accept">
		</form>
	<%}%>


	
</body>
</html>