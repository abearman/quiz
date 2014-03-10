<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #000000;
      }

      .form-signin {
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
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
    <link href="../assets/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href='http://fonts.googleapis.com/css?family=Fugaz+One' rel='stylesheet' type='text/css'>
	
	<%
	String username = (String)request.getParameter("username");
	%>
	
	<title>Create Account</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body style="background-color:#f5f5f5">

	<div class="container">
		<form style="width:400px" class="form-signin" action="AccountCreationServlet" method="post">
			<h2 class="form-signin-heading"> Sorry, the name <%= username %> is already in use. </h2>
			<h3>Please enter another name and password.</h3>
			<div><input name="username" type="text" class="input-block-level" placeholder="User Name"></div>
			<div><input name="password" type="password" class="input-block-level" placeholder="Password"></div>
			<button class="btn btn-large btn-primary" type="submit"> Submit </button>
			<div><a href="guestHomepage.jsp">Log in as guest</a></div>
			<div><a href="index.html">Log in</a></div>
		</form>
	</div>

</body>
</html>



