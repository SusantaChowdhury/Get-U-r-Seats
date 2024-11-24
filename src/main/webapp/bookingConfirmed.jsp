<%@ page import="pojo.Movie"%>
<%@ page import="pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking Confirmation</title>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/bookingConfirmationStyles.css">
</head>
<body>
	<!-- Navigation Bar -->
	<nav class="navbar navbar-expand-lg navbar-dark">
		<a class="navbar-brand" href="home.jsp">Get U'r Seats</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="home.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="MovieServlet">Browse
						Movies</a></li>
				<li class="nav-item"><a class="nav-link"
					href="FetchBookedSeatsServlet">My Seats</a></li>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a>
				</li>
			</ul>
		</div>
	</nav>
	<h3 class="text-center">Booking Successful!</h3>
	<div class="container">
		<%
		Movie movie = (Movie) session.getAttribute("movie");
		User user = (User) session.getAttribute("user");
		String[] seats = (String[]) session.getAttribute("seats");
		%>
		<div class="movie-details">
			<div class="poster">
				<img src="img/<%=movie.getId()%>.png" class="movie-poster"
					alt="<%=movie.getTitle()%>">
			</div>
			<div class="details">
				<h1>
					<%=movie.getTitle()%>
				</h1>
				<p>
					Rating:
					<%=movie.getRating()%>/10
				</p>
				<p>
					Duration:
					<%=movie.getDuration()%>
				</p>
				<p>
					Genre:
					<%=movie.getGenre()%>
				</p>
				<div class="booking details">
					<h5>Booked Details:</h5>
					<h6>
						Name:
						<%=user.getUsername()%></h6>
					<h6>
						<h6>
							Email:
							<%=user.getEmail()%></h6>
						<h6>
							Your Seats:
							<%
						for (String seat : seats) {
									out.print(seat + " ");
								}
						%>
						</h6>
				</div>
			</div>
		
		
			
		</div>
	</div>

	<h3 style="color: #e2e5be" class="text-center">Thank You!</h3>

	<footer>
		<p>© 2024 Get U'r Seats || Terms & Conditions || Privacy Policy</p>
	</footer>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.6/dist/umd/popper.min.js"></script>
	<script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>