<%@ page import="java.util.*, quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Create Quiz</title>
	<style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #000000;
      }
      
      .form-quiz {
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
	
	<link href="../assets/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href='http://fonts.googleapis.com/css?family=Fugaz+One' rel='stylesheet' type='text/css'>
	
</head>

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
	
	<div class="form-quiz" style="width:400px">

		<h3>Create a Quiz</h3>
	
		<form action="QuizCreationServlet" method = "post">
			<p>Quiz Name: <input type = "text" name = "quizName"/></p>
			<p>Quiz Description: <input type = "text" name = "quizDescription"/></p>
			<p>Random Question Order: <br>
			Yes: <input type = "radio" name = "isRandom" value = "true"/><br>
			No: <input type = "radio" name = "isRandom" value = "false"/></p>
			<p>Multiple Page: <br>
			Yes: <input type = "radio" name = "isMultiplePage" value = "true"/><br>
			No: <input type = "radio" name = "isMultiplePage" value = "false"/></p>
			<p>Immediate Correction: <br>
			Yes: <input type = "radio" name = "isImmediateCorrection" value = "true"/><br>
			No: <input type = "radio" name = "isImmediateCorrection" value = "false"/></p>
			<input type = "submit" value = "Done"/>
		</form>
	
	</div>
	
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>