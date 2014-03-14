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

<script>
function checkRadioButtons()
{
	var isRandomButtons = document.getElementsByName("isRandom");
	for(var i=0; i<isRandomButtons.length; i++)
	{
		if(isRandomButtons[i].checked)
			{
				break;
			}
		if(i+1 == isRandomButtons.length)
			{
				alert("Please select if the quiz had a random question order.");
				return;
			}
	}
	
	var isMultiplePageButtons = document.getElementsByName("isMultiplePage");
	for(var i=0; i<isMultiplePageButtons.length; i++)
	{
		if(isMultiplePageButtons[i].checked)
			{
				break;
			}
		if(i+1 == isMultiplePageButtons.length)
			{
				alert("Please select if the quiz will be displayed over multipe pages.");
				return;
			}
	}
	
	var isImmediateCorrectionButtons = document.getElementsByName("isImmediateCorrection");
	for(var i=0; i<isImmediateCorrectionButtons.length; i++)
	{
		if(isImmediateCorrectionButtons[i].checked)
			{
				break;
			}
		if(i+1 == isImmediateCorrectionButtons.length)
			{
				alert("Please select if the quiz will have immediate corrections for the user.");
				return;
			}
	}
	document.creationForm.submit();
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
	
	<div class="form-quiz" style="width:400px">

		<h3>Create a Quiz</h3>
	
		<form name= "creationForm" action="QuizCreationServlet" method = "post">
			<p>Quiz Name: <input type = "text" name = "quizName" class="input-block-level" placeholder="Name"/></p>
			<p>Quiz Description: <input type = "text" name = "quizDescription" class="input-block-level" placeholder="Description"/></p>
			<p>Random Question Order: <br>
			Yes: <input type = "radio" name = "isRandom" value = "true"/><br>
			No: <input type = "radio" name = "isRandom" value = "false"/></p>
			<p>Multiple Page: <br>
			Yes: <input type = "radio" name = "isMultiplePage" value = "true"/><br>
			No: <input type = "radio" name = "isMultiplePage" value = "false"/></p>
			<p>Immediate Correction: <br>
			Yes: <input type = "radio" name = "isImmediateCorrection" value = "true"/><br>
			No: <input type = "radio" name = "isImmediateCorrection" value = "false"/></p>
			<input type = "button" value = "Done" onclick="checkRadioButtons()"/>
		</form>
	
	</div>
	
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>