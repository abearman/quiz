<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
 <%@ page import = "java.util.*, java.text.*, quiz.*" %>
<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href='http://fonts.googleapis.com/css?family=Fugaz+One' rel='stylesheet' type='text/css'>
	
	<style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #000000;
      }
      
      .send-message {
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
    </style>
	
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	
	<%
	String toUser = request.getParameter("toUser");
	String fromUser = request.getParameter("fromUser");
	DAL dal = (DAL) request.getServletContext().getAttribute("DAL");
	%>  
	
	<title>Send message to <%=toUser%></title>
	
</head>

<script type="text/javascript">
function checkMessage()
{
	if(document.frm.message.value == "")
	{
		alert("Please enter a message.");
	}
	else if(document.frm.message.value.length > 5000)
	{
		alert("Message needs to be less than 5000 characters.");
	}
	else
	{
		document.frm.submit();
	}
}
</script>

<body style="background-color:#f5f5f5">

	<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <div><a class="navbar-brand" style="color:white; font-family:'Fugaz One', cursive; font-size:40px">Quizzler</a></div>
	    </div>
	  </div><!-- /.container-fluid -->
	</nav>
	
	<div class="send-message" style="width:400px">

		<h3>Send message to <%=toUser%></h3>
	
		<div>
			<form name ="frm" action ="SendMessageServlet" method = "post">
				<div id ="messageArea">
					<h3>Note Message</h3>
					<input type="hidden" name="messageType" value ="NoteMessage">
					<input type="hidden" name="fromUser" value=<%=fromUser%>>
					<input type="hidden" name="toUser" value=<%=toUser%>>
					<input type ="text" name="message" class="input-block-level" placeholder="Message"> 
					<input type="button" class="btn btn-primary" value="Send Message" onclick="checkMessage()">
				</div>		
			</form>
		</div>
	</div>


	

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>
