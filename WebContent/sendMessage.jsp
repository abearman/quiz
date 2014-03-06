<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
 <%@ page import = "java.util.*, java.text.*, quiz.*" %>
<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	
	<%
	String toUser = request.getParameter("toUser");
	String fromUser = request.getParameter("fromUser");
	DAL dal = (DAL) request.getServletContext().getAttribute("DAL");
	%>  
	
	<title>Send message to <%=toUser%></title>
	
</head>

<body>

	<h3>Send message to <%=toUser%></h3>
	

<script type="text/javascript">

//displays the corresponding message type and its fields.
function displayMessage()
{
	var selected = document.getElementById("optionsBox").selectedIndex;
	var options = document.getElementById("optionsBox").options;
	System.out.println("hellooo");
	if (options[selected].value.equals(Message.NOTE_MESSAGE)) {
		var display = "<h3>Note Message</h3> ";
		display+= "<input type=\"hidden\" name=\"messageType\" value =\"<%=Message.NOTE_MESSAGE%>\"/>";
		display+= "<p>Message:<input type = \"text\" name=\"message\"></p>";
		document.getElementById("messageArea").innerHTML = display;

	} else if (options[selected].value.equals(Message.CHALLENGE_MESSAGE)) {
		var display = "<h3>Challenge Message</h3> ";
		display+= "<input type=\"hidden\" name=\"messageType\" value =\"<%=Message.CHALLENGE_MESSAGE%>\"/>";
		display+= "<p>Quiz:<input type = \"text\" name=\"quiz\"></p>";
		document.getElementById("messageArea").innerHTML = display;
	}
}

</script>



<div>
	<select id ="optionsBox" onchange="displayMessage()">
		<option value = "<%=Message.NOTE_MESSAGE%>"> Note Message</option>
		<option value = "<%=Message.CHALLENGE_MESSAGE%>"> Challenge Message</option>
	</select>
</div>

<div>
	<form name ="frm" action ="SendMessageServlet" method = "post">
		<div id ="messageArea">
			<h3>Note Message</h3>
			<input type="hidden" name="messageType" value ="<%=Message.NOTE_MESSAGE%>">
			<p>Message:<input type = "text" name="message"></p>
		</div>		
		<input type = "submit" value = "Send Message"/> 
	</form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>
