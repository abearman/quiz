<%@ page import="java.util.*, quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Question Response</title>
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

	<%
		User user = (User)request.getSession().getAttribute("user");
		DAL dal = (DAL)request.getServletContext().getAttribute("DAL");
		String username = (String)session.getAttribute("loginName");
		Quiz quiz = (Quiz)request.getSession().getAttribute("quiz");
		boolean hasNewMessages = dal.userHasNewMessages(username);
		
		ArrayList<Question> questions = quiz.getQuestions();
		ArrayList<String> usersAnswers = quiz.getAnswers();
		
		long elapsedTime = (Long)request.getSession().getAttribute("elapsedTime");
		double elapsedTimeInSeconds = ((Long)elapsedTime).doubleValue()/1000;
		int numQuestionsCorrect = (Integer)request.getSession().getAttribute("numQuestionsCorrect");
		int totalNumQuestions = questions.size();
		ArrayList<Message> messages = dal.getSevenMostRecentUserMessages(user);
	%>

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
	      <div><a class="navbar-brand" href="/" style="color:white; font-family:'Fugaz One', cursive; font-size:40px">Quizzler</a></div>
	    </div>
	
		<form class="navbar-form navbar-left" role="search" name="searchForUser" action="SearchForUserServlet" method="post">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search for friends" name="usernameToSearch">
				<button type="submit" class="btn btn-default">Submit</button>
				
			</div>	
		</form>
		
	      <ul class="nav navbar-nav navbar-right">
	        <li class="active"><a href="friends.jsp" style="margin:0; padding:0"><img src="friends-icon.jpg" height="50px" height="50px"/></a></li>
	        <li class="active"><a href="messages.jsp" style="margin:0; margin-left:10px; padding:0"><img src="messages.jpg" height="50px" height="50px"/></a></li>
	        
	        <%if (hasNewMessages) {
	        	%> 
				<li class="navbar-form">
				<div class="dropdown">
				  <button class="btn dropdown-toggle btn-danger" type="button" id="dropdownMenu1" data-toggle="dropdown"> Notifications <span class="caret"></span> </button>
				  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
				  	
				  	<% for (int i = 0; i < messages.size(); i++) {
				  		%><li role="presentation">
				  			<a role="menuitem" tabindex="-1" href="messages.jsp">
				  				<%
				  				Message message = messages.get(i);
				  				String type = message.getMessageType();
				  				String fromUser = message.getFromUser();%>
				  				<%=type%> from <%=fromUser%> 
				  			</a>
				  		</li>
				  	<%}%>
				    <li role="presentation" class="divider"></li>
				    <li role="presentation"><a role="menuitem" tabindex="-1" href="messages.jsp">See All</a></li>
				  </ul>
				</div>
			</li>
				<% 
	        } else {%>
	        	<li class="navbar-form">
				<div class="dropdown">
				  <button class="btn dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown"> Notifications <span class="caret"></span> </button>
				  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
				  	
				  	<% for (int i = 0; i < messages.size(); i++) {
				  		%><li role="presentation">
				  			<a role="menuitem" tabindex="-1" href="messages.jsp">
				  				<%
				  				Message message = messages.get(i);
				  				String type = message.getMessageType();
				  				String fromUser = message.getFromUser();%>
				  				<%=type%> from <%=fromUser%> 
				  			</a>
				  		</li>
				  	<%}%>
				    <li role="presentation" class="divider"></li>
				    <li role="presentation"><a role="menuitem" tabindex="-1" href="messages.jsp">See All</a></li>
				  </ul>
				</div>
			</li>
			<%}%>
			
			<%if (user.getIsAdministrator()) {
				%> <li class="active"><a href="administratorHomepage.jsp">Home</a></li><%
			} else {
				%> <li class="active"><a href="userHomepage.jsp">Home</a></li>
			<%}%>
			
	        <li class="active"><a href="friendProfile.jsp?friendName=<%=username%>"><%=username%></a></li>
	        <li style="padding-right:10px">
	        	<form class="navbar-form navbar-right" name="logOut" action="LogOutServlet" method="post">
					<button type="submit" class="btn btn-default">Log Out</button>
				</form>
	        </li>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
	
	<div class="form-quiz" style="width:600px">

		<h3><%= quiz.getQuizName() %> by <%= quiz.getCreatorName() %></h3>
		<h4>Score: <%= numQuestionsCorrect %>/<%= totalNumQuestions %></h4>
		<h4>Time Taken: <%= elapsedTimeInSeconds %> seconds</h4>

		<table class="table table-striped">
			<tr>
				<th>Your Answer</th>
				<th>Correct Answer</th>
			</tr>
		<%
			for (int i = 0; i < questions.size(); i++){
				String usersAnswer = usersAnswers.get(i);
				if(questions.get(i).getQuestionType()==Question.MultiAnswer_MultipleChoice)//if question is multiAnswerMultipleChoice
				{
					ArrayList<String> realAnswers = Question.createArray(usersAnswer);
					if(questions.get(i).isCorrect(realAnswers))//if answers is right
					{
						out.println("<tr>");
						out.println("<td>");
						String userOutput = "";
						for(int j =0; j < realAnswers.size(); j++)//adds up users answers
						{
							if(j+1==realAnswers.size())
							{
								userOutput+=realAnswers.get(j);
							}
							else
							{
								userOutput+=realAnswers.get(j)+", ";
							}
						}
						out.println(userOutput);
						out.println("</td>");
						out.println("<td>");
						out.println(userOutput);
						out.println("</td>");
						out.println("</tr>");
					}
					else //if answer is wrong
					{
						out.println("<tr>");
						out.println("<td>");
						String userOutput = "";
						for(int j =0; j < realAnswers.size(); j++)//adds up users answers
						{
							if(j+1==realAnswers.size())
							{
								userOutput+=realAnswers.get(j);
							}
							else
							{
								userOutput+=realAnswers.get(j)+", ";
							}
						}
						out.println(userOutput);
						out.println("</td>");
						out.println("<td>");
						ArrayList<String> actualAnswers = questions.get(i).getAnswer();
						String quizOutput = "";
						for(int j =0; j < actualAnswers.size(); j++)//adds up users answers
						{
							if(j+1==actualAnswers.size())
							{
								quizOutput+=actualAnswers.get(j);
							}
							else
							{
								quizOutput+=actualAnswers.get(j)+", ";
							}
						}
						out.println(quizOutput);
						out.println("</td>");
						out.println("</tr>");
					}
				}
				else //
				{
					String correctAnswer = questions.get(i).getAnswer().get(0);
					if (questions.get(i).answerIsCorrect(usersAnswer)){
						correctAnswer = usersAnswer;
					}
					out.println("<tr>");
					out.println("<td>");
					out.println(usersAnswer);
					out.println("</td>");
					out.println("<td>");
					out.println(correctAnswer);
					out.println("</td>");
					out.println("</tr>");
				}
			}
		%>
		</table>

		<div>
		<form name ="frm" action ="SendMessageServlet" method = "post">
			<input type="hidden" name="messageType" value ="ChallengeMessage">
			<input type="hidden" name="quiz" value=<%=quiz.getQuizName()%>>
			<input type="hidden" name="fromUser" value=<%=user.getLoginName()%>>
			<h5>Challenge a friend to take this quiz!<input type = "text" name="toUser"></h5>	
			<input type = "submit" class="btn btn-primary" value = "Challenge"/> 
		</form>
		</div>

		<p>Go back <a href="quizSummary.jsp?quizName=<%=quiz.getQuizName()%>"><%=quiz.getQuizName()%></a></p>

		<%
		if (user.getIsAdministrator()) {
			%> Go back <a href="administratorHomepage.jsp">home</a><% 
		} else {
			%> Go back <a href="userHomepage.jsp">home</a><% 
		}%>
	
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> 
	<script type="text/javascript" src="http://twitter.github.com/bootstrap/assets/js/bootstrap-dropdown.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>