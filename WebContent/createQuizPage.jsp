<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Create Quiz</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

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
	
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>