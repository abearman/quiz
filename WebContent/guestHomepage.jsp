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

	ArrayList<String> recentlyCreatedQuizzes = dal.getRecentlyCreatedQuizzes(); 
	ArrayList<String> popularQuizzes = dal.getPopularQuizzes();
	ArrayList<String> announcements = dal.getAllAnnouncements();
	
	%>  

	<title>Welcome Guest </title>
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
		
	      <ul class="nav navbar-nav navbar-right">
	      	<li class="active"><a href="guestHomepage.jsp">Home</a></li>
	      </ul>
	  </div><!-- /.container-fluid -->
	</nav>


	<div>

		<div>
			<a data-toggle="modal" href="#allQuizzesModal" class="btn btn-primary">See all existing quizzes</a>
			<a data-toggle="modal" href="#recentlyCreatedQuizzesModal" class="btn btn-primary">Recently Created Quizzes</a>
			<a data-toggle="modal" href="#popularQuizzesModal" class="btn btn-primary">Popular Quizzes</a>
		</div>
		
		<h3> Announcements </h3>
		<div class="well">
			<% for (String announcement : announcements) {
				%> <div style="border-bottom-style:solid; border-bottom-width:1px; border-bottom-color:#d3d3d3; padding-top:5px; padding-bottom:5px"> <%=announcement%></div> <%	
			}%> 
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
					%> <div> Quiz: <a href="guestQuizSummary.jsp?quizName=<%=quizName%>"><%=quizName%></a>, by <a href="guestFriendProfile.jsp?friendName=<%=creator%>"><%=creator%></a> </div> <% 
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
				%> <div> <a href="guestQuizSummary.jsp?quizName=<%=quizName%>"><%=quizName%></a> </div> <% 
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
				%> <div><a href="guestQuizSummary.jsp?quizName=<%=quizName%>"><%=quizName%></a>, by <a href="guestFriendProfile.jsp?friendName=<%=creator%>"><%=creator%></a> </div> <%
			}%>
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






