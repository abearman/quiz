<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Quizzler</title>
	
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

</head>

	<%
	String username = "";
	String password = "";
	Cookie[] cookies = request.getCookies();
	if (cookies.length > 1) {
		username = cookies[1].getName();
		password = cookies[1].getValue();
	}
	%>

<body style="background-color:#f5f5f5">

	<div class="form-signin container" style="width:400px; padding-left:30px">
			<form action="LoginServlet" method="post">
				<h2>Welcome to <span style="font-family:'Fugaz One', cursive;">Quizzler!</span></h2>
				<h3> Please log in </h3>
				<div><input name="username" type="text" class="input-block-level" placeholder="User Name" value=<%=username%>></div>
				<div><input name="password" type="password" class="input-block-level" placeholder="Password" value=<%=password%>></div>
				<label class="checkbox">
					<input name="checkbox" type="checkbox" value="remember-me" checked> Remember me
				</label>
				<button class="btn btn-large btn-primary" type="submit"> Sign in </button>
			</form>
			
			<form style="padding-top:10px" action="LoginAsGuestServlet" method="post">
				<button class="btn btn-large btn-primary" type="submit"> Log in as guest </button>
			</form>
			
		<div style="padding-top:5px"><a href="createAccount.html">Create New Account</a></div>
		
	</div>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>


