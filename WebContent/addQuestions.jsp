<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Questions</title>
</head>

<body>
<h5><i>Choose a question type from the drop down menu and fill out the necessary fields. Press "Add Question" to add another
question or press "Done" if you are complete.</i></h5>

<div>
<select>
	<option value = "QuestionResponse"> Question Response</option>
	<option value = "FillInTheBlank"> Fill in the Blank</option>
	<option value = "MultipleChoice"> Multiple Choice</option>
	<option value = "PictureResponse"> Picture Response</option>
</select>
</div>

<div>
<form action ="AddQuestionServlet" method = "post">
<input type = "submit" value = "Add Question"/> 
</form>
<form action="QuizCompleted?" method = "post">
<input type = "submit" value = "Done"/>
</form>
</div>

</body>
</html>