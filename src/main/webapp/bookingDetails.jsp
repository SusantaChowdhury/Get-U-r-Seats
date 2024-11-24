<%@ page import="pojo.Movie"%>
<%@ page import="pojo.User"%>
<%@ page import="pojo.Seat"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking Details</title>
<link rel="stylesheet" href="css/bookingDetails.css">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20,400,0,0&icon_names=currency_rupee" />
</head>
<body>
	<%
	User user = (User) session.getAttribute("user");
	String username = user.getUsername();

	if (username == null) {
		response.sendRedirect("index.jsp");
	}
	%>
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

	<!-- Booking Details -->
	<div class="container">
		<h2 class="text-center">Booking Details!</h2>
		<%
		String[] seats = (String[]) session.getAttribute("seats");
		Movie movie = (Movie) session.getAttribute("movie");
		double totalCost = (double) request.getAttribute("totalCost");
		%>


		<div class="movie-card">
			<img src="img/<%=movie.getId()%>.png" alt="<%=movie.getTitle()%>"
				class="movie-poster">
			<div class="movie-details">
				<h4><%=movie.getTitle()%></h4>
				<p class="text-muted">
					Genre:
					<%=movie.getGenre()%></p>
				<p>
					Rating:
					<%=movie.getRating()%>/10
				</p>
				<p>
					Release Year:
					<%=movie.getReleaseYear()%></p>

			</div>
			<div class="booking-details">
				<p>
					Seats:
					<%
				for (String seat : seats) {
					out.print(seat + " ");
				}
				%>

				</p>
				<p>
					Price: <span class="material-symbols-outlined">
						currency_rupee </span><%=totalCost%>
				</p>
			</div>
			<form action="ConfirmBookingServlet" method="post">

				<button type="submit" class="btn-primary">
					Pay <span class="material-symbols-outlined"> currency_rupee
					</span><%=totalCost%></button>
			</form>
		</div>

	</div>


	<!-- Footer -->
	<footer>
		<p>© 2024 Get U'r Seats || Terms & Conditions || Privacy Policy</p>
	</footer>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.6/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>