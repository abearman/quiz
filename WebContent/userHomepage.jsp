<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<%
	DAL dal = (DAL) request.getServletContext().getAttribute("DAL");
	User user = (User)session.getAttribute("user");
	String username = user.getLoginName();

	ArrayList<String> recentlyCreatedQuizzes = dal.getRecentlyCreatedQuizzes(); 
	ArrayList<String> popularQuizzes = dal.getPopularQuizzes();
	ArrayList<String> userRecentlyCreatedQuizzes = dal.getUserRecentlyCreatedQuizzes(user.getLoginName());
	ArrayList<String> userRecentlyTakenQuizzes = dal.getUserRecentlyTakenQuizzes(user.getLoginName());
	
	String achievements = dal.getUserAchievements(username);
	ArrayList<String> announcements = dal.getAllAnnouncements();
	boolean hasNewMessages = dal.userHasNewMessages(username);
	
	ArrayList<NewsfeedObject> newsfeed = dal.getNewsfeed(username);
	%>  

	<title>Welcome <%= username %> </title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href='http://fonts.googleapis.com/css?family=Fugaz+One' rel='stylesheet' type='text/css'>
</head>

<body>

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
	
		<form class="navbar-form navbar-left" role="search" name="searchForUser" action="SearchForUserServlet" method="post">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search for friends" name="usernameToSearch">
				<button type="submit" class="btn btn-default">Submit</button>
			</div>	
		</form>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
	          <ul class="dropdown-menu">
	            <li><a href="#">Action</a></li>
	            <li><a href="#">Another action</a></li>
	            <li><a href="#">Something else here</a></li>
	            <li class="divider"></li>
	            <li><a href="#">Separated link</a></li>
	            <li class="divider"></li>
	            <li><a href="#">One more separated link</a></li>
	          </ul>
	        </li>
	      </ul>
	      
	      <ul class="nav navbar-nav navbar-right">
	        <li class="active"><a href="friends.jsp" style="margin:0; padding:0"><img src="friends-icon.jpg" height="50px" height="50px"/></a></li>
	        <li class="active"><a href="messages.jsp" style="margin:0; margin-left:10px; padding:0"><img src="messages.jpg" height="50px" height="50px"/></a></li>
	        
	        <%if (hasNewMessages) {
	        	%> <li class="active"><a href="messages.jsp" style="margin:0; margin-left:10px; padding:0"><img src="exclamation-red.jpg" height="50px" height="50px"/></a></li> <% 
	        } else {%>
	        	<li class="active"><a href="messages.jsp" style="margin:0; margin-left:10px; padding:0"><img src="exclamation.jpg" height="50px" height="50px"/></a></li>
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


	<div class="row">
		<div class="col-md-2">
			<!-- User stuff on left -->
			<h3><a href="friendProfile.jsp?friendName=<%=username%>"><%= username %></a></h3>
			<a href="createQuizPage.jsp"><button class="btn btn-default btn-primary"> Create quiz </button></a> 
			
			<h3> Achievements: </h3>
			<%for (int i = 0; i < achievements.length(); i++) {
				if (achievements.charAt(i) == '1') {%>
					<div style="padding-left:10px">
						<img src="<%=Achievements.achievementsImgs[i]%>" height="30px" width="30px" />
						<%=Achievements.achievements[i]%>	
					</div> <% ;
				}
			}%> 
			
			<h3> My Recently Created Quizzes: </h3> 
				<%for (int i = 0; i < userRecentlyCreatedQuizzes.size(); i++) {
					String quizName = userRecentlyCreatedQuizzes.get(i);
					%> <div><a href="quizSummary.jsp?quizName=<%=quizName%>"> <%=quizName%> </a> </div> <% 
				}%>
				
			<h3> My Recently Taken Quizzes: </h3>
				<%for (int i = 0; i < userRecentlyTakenQuizzes.size(); i++) {
						String quizName = userRecentlyTakenQuizzes.get(i);
						%> <div><a href="quizSummary.jsp?quizName=<%=quizName%>"> <%=quizName%> </a></div> <% 
				}%>

		</div>
	
		<div class="col-md-7" style="border-right-style:solid; border-right-color:#d3d3d3; border-left-style:solid; border-left-color:#d3d3d3">
			<!-- Newsfeed in middle -->

			<h2 style="margin-left:15px">Newsfeed</h2>
			
			<form class="navbar-form" role="search" name="updateStatus" action="" method="post">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Update status" name="status" size=80>
					<button type="submit" class="btn btn-default">Submit</button>
				</div>	
			</form>
			
			<div class="row" style="margin-left:15px; padding-bottom:5px">
				<a data-toggle="modal" href="#fullHistoryModal" class="btn btn-primary">See my quiz history</a>
				<a data-toggle="modal" href="#allQuizzesModal" class="btn btn-primary">See all existing quizzes</a>
				<a data-toggle="modal" href="#recentlyCreatedQuizzesModal" class="btn btn-primary">Recently Created Quizzes</a>
				<a data-toggle="modal" href="#popularQuizzesModal" class="btn btn-primary">Popular Quizzes</a>
			</div>
			
			<div style="height:800px;overflow:scroll;padding:5px;">
				<%for (int i = 0; i < newsfeed.size(); i++) {
					NewsfeedObject nfo = newsfeed.get(i);
					String name = nfo.getLoginName();
					String action = nfo.getAction();
					if (nfo.hasQuiz()) { 
						String quizName = nfo.getQuizName(); %>
						<div style="border-bottom-style:solid; border-bottom-width:1px; border-bottom-color:#d3d3d3; padding:5px"> <a href="friendProfile.jsp?friendName=<%=name%>"><%=name%></a> <%=action%> <a href="quizSummary.jsp?quizName=<%=quizName%>"><%=quizName%></a> </div>
					<%} else {%>
						<div style="border-bottom-style:solid; border-bottom-width:1px; border-bottom-color:#d3d3d3; padding:5px"> <a href="friendProfile.jsp?friendName=<%=name%>"><%=name%></a> <%=action%> </div>
					<%}%>
				<%}%>
			</div>
		</div>
	
		<div class="col-md-3">
			<!-- User stuff on right -->
			<h3> Announcements </h3>
			<div class="well">
				<% for (String announcement : announcements) {
					%> <div style="border-bottom-style:solid; border-bottom-width:1px; border-bottom-color:#d3d3d3; padding-top:5px; padding-bottom:5px"> <%=announcement%></div> <%	
				}%> 
			</div>		
			
		</div>	
	</div>
		
	  <!-- Recently Created Quizzes Modal -->
	  <div class="modal fade" id="recentlyCreatedQuizzesModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title">Recently Created Quizzes</h4>
	        </div>
	        <div class="modal-body">
	          <%for (int i = 0; i < recentlyCreatedQuizzes.size(); i++) {
					String quizName = recentlyCreatedQuizzes.get(i);
					String creator = dal.getCreatorName(quizName);
					%> <div> Quiz: <a href="quizSummary.jsp?quizName=<%=quizName%>"><%=quizName%></a>, by <a href="friendProfile.jsp?friendName=<%=creator%>"><%=creator%></a> </div> <% 
			   }%>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        </div>
	      </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	  </div><!-- /.modal -->
	  
	   <!-- Popular Quizzes Modal -->
	  <div class="modal fade" id="popularQuizzesModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title">Popular Quizzes</h4>
	        </div>
	        <div class="modal-body">
	          <%for (int i = 0; i < popularQuizzes.size(); i++) {
				String quizName = popularQuizzes.get(i);
				%> <div> <a href="quizSummary.jsp?quizName=<%=quizName%>"><%=quizName%></a> </div> <% 
			  }%>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        </div>
	      </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	  </div><!-- /.modal -->
	
		<!-- All Quizzes Modal -->
	  <div class="modal fade" id="allQuizzesModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title">All Quizzes</h4>
	        </div>
	        <div class="modal-body">
	          <% ArrayList<Quiz> allQuizzes = dal.getAllQuizzes();
	          for (Quiz quiz : allQuizzes) {
				String quizName = quiz.getQuizName();
				String creator = quiz.getCreatorName();
				%> <div><a href="quizSummary.jsp?quizName=<%=quizName%>"><%=quizName%></a>, by <a href="friendProfile.jsp?friendName=<%=creator%>"><%=creator%></a> </div> <%
			}%>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        </div>
	      </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	  </div><!-- /.modal -->
	
		<!-- Full History Modal -->
	  <div class="modal fade" id="fullHistoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title">My Quiz History</h4>
	        </div>
	        <div class="modal-body">
				<ul>
					<% 
					ArrayList<HistoryObject> allHistories = dal.getHistoryListForUser(username);
					for (HistoryObject hist : allHistories) {
						String quizName = hist.getQuizName();
						%> <li> 
								<p>Quiz: <a href="quizSummary.jsp?quizName=<%=quizName%>"><%=quizName%></a></p>
								<p>Score: <%= hist.getNumQuestionsCorrect() %></p> 
								<p>Time Elapsed: <%= hist.getElapsedTime() %></p>
								<p>Date: <%= hist.getDate() %></p>
						
						 </li> <%
					} 
					%>
				</ul>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        </div>
	      </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	  </div><!-- /.modal -->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> 
	<script src="/twitter-bootstrap/twitter-bootstrap-v2>/js/bootstrap-modal.js"></script> 
	<script src="js/bootstrap.min.js"></script>
</body>
</html>






