<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import = "java.util.*, java.text.*, quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Add Questions for 
		<% Quiz quizCreated = (Quiz)request.getSession().getAttribute("quizCreated");
			out.print(quizCreated.getQuizName());
		%>
	</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
	<h5><i>Choose a question type from the drop down menu and fill out the necessary fields. Press "Add Question" to add another
question or press "Done" if you are complete. Press "Add Answers" to add another possible answer to the question.</i></h5>

<script type="text/javascript">

//displays the corresponding question type and its fields.
function displayQuestion()
{
	var selected = document.getElementById("optionsBox").selectedIndex;
	var options = document.getElementById("optionsBox").options;
	if (options[selected].value == "QuestionResponse")
	{
		var display = "<h3>Question Response</h3> ";
		display+= "<input type=\"hidden\" name=\"questionType\" value =1>";
		display+= "<input type=\"hidden\" name=\"numAnswers\" value= 1>";
		display+= "<p>Question:<input type = \"text\" name=\"question\"></p>";
		display+= "<p id=\"answers\">Answers:<input type = \"text\" name=\"answer1\"></p>";
		document.getElementById("displayArea").innerHTML = display;
		var instructions = "<b>An example of a Question Response question is \"Who was the first president of the United States?\"</b>";
		document.getElementById("specialInstructions").innerHTML = instructions;
	}
	if (options[selected].value == "FillInTheBlank")
	{
		var display = "<h3>Fill in the Blank</h3> ";
		display+= "<input type=\"hidden\" name=\"questionType\" value =2>";
		display+= "<input type=\"hidden\" name=\"numAnswers\" value= 1>";
		display+= "<p>Question:<input type = \"text\" name=\"question\"></p>";
		display+= "<p id=\"answers\">Answers:<input type = \"text\" name=\"answer1\"></p>";
		document.getElementById("displayArea").innerHTML = display;
		var instructions = "<b>An example of a Fill in the Blank question is \"______ was the first president of the United States.\"</b>";
		document.getElementById("specialInstructions").innerHTML = instructions;
	}
	if (options[selected].value == "MultipleChoice")
	{
		var display = "<h3>Multiple Choice</h3> ";
		display+= "<input type=\"hidden\" name=\"questionType\" value =3>";
		display+= "<input type=\"hidden\" name=\"numAnswers\" value= 1>";
		display+= "<p>Question:<input type = \"text\" name=\"question\"></p>";
		display+= "<p id=\"answers\">Answers:<input type = \"text\" name=\"answer1\"></p>";
		display+= "<p>Choices:<input type = \"text\" name=\"choices\"></p>";
		document.getElementById("displayArea").innerHTML = display;
		var instructions = "<b>An example of a Multiple Choice question is \"Who was the first president of the United States?\"<br>";
		instructions+= "Choices for the question need to be separated by the \"\\n\" character. For the question above, answer formatting would look like \"Abraham Lincoln\\nGeorge Washington\\nJohn Adams\"</b>";
		document.getElementById("specialInstructions").innerHTML = instructions;
	}
	if (options[selected].value == "PictureResponse")
	{
		var display = "<h3>Picture Response</h3> ";
		display+= "<input type=\"hidden\" name=\"questionType\" value =4>";
		display+= "<input type=\"hidden\" name=\"numAnswers\" value= 1>";
		display+= "<p>Question:<input type = \"text\" name=\"question\"></p>";
		display+= "<p id=\"answers\">Answers:<input type = \"text\" name=\"answer1\"></p>";
		display+= "<p>Image URL:<input type = \"text\" name=\"imageURL\"></p>";
		document.getElementById("displayArea").innerHTML = display;
		var instructions = "<b>An example of a Picture Response question is a picture of George Washinton with the accompanying question \"Who is this President of the United States?\"<br>";
		instructions+= "The question field may be left blank for this type of question and only a picture will be shown. The URL for the image should be the exact URL to find the image at.</b>";
		document.getElementById("specialInstructions").innerHTML = instructions;
	}
}


function addAnswerBox()
{
	document.frm.numAnswers.value = parseInt(document.frm.numAnswers.value)+1;
	var answers = document.getElementById("answers");
	answers.appendChild(document.createElement('div')).innerHTML = "<input type =\"text\" name =\"answer"+document.frm.numAnswers.value+ "\">";
}

</script>



<div>
	<select id = "optionsBox" onchange="displayQuestion()">
		<option value = "QuestionResponse"> Question Response</option>
		<option value = "FillInTheBlank"> Fill in the Blank</option>
		<option value = "MultipleChoice"> Multiple Choice</option>
		<option value = "PictureResponse"> Picture Response</option>
	</select>
</div>

<div>
	<form name ="frm" action ="AddQuestionsServlet" method = "post">
		<div id ="displayArea">
			<h3>Question Response</h3>
			<input type="hidden" name="questionType" value =1>
			<input type="hidden" name="numAnswers" value= 1/>
			<p>Question:<input type = "text" name="question"></p>
			<p id="answers">Answers:<input type = "text" name="answer1" ></p>
		</div>
		
		<input type ="button" value = "Add Answer" onclick ="addAnswerBox()"/>
		<input type = "submit" value = "Add Question"/> 
	</form>
	
	<form action="QuizCreationCompletedServlet" method = "post">
		<input type = "submit" value = "Done"/>
	</form>
	
</div>

<hr noshade size=4>
<p id = "specialInstructions"><b>An example of a Question Response question is "Who was the first president of the United States?"</b></p>
<br>
<p><b>Please separate the possible answers for the question in the text box by the "\n" character. An example would be "apple\norange\nbanana".</b></p>




<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>