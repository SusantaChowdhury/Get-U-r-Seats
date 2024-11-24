<%@ page import="pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/homeStyles.css">
</head>

<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 
	response.setHeader("Expires", "0"); // Proxies 
	User user = (User) session.getAttribute("user"); 
	//	String username = user.getUsername();

	if (user == null) {
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
				<li class="nav-item"><a class="nav-link" href="MovieServlet">Browse Movies</a></li>
				<li class="nav-item"><a class="nav-link"
					href="FetchBookedSeatsServlet">My Seats</a></li>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a>
				</li>
			</ul>
		</div>
	</nav>

	<!-- Hero Section -->
	<header class="hero-home">
		<div class="hero-content-home">
			<h2>
				Hey, <strong> <%=user.getUsername()%></strong>!</h2>
			<h5>Book tickets for the latest movies, select your seats, and
				enjoy a great cinematic experience!</h5>
			<a href="MovieServlet" class="btn-primary">Browse Movies</a>
		</div>
	</header>

	<!-- Featured Movies Section -->
	<section class="featured-movies">
		<h2>Latest Movies</h2>
		<div class="movie-grid" id="movieGrid">
			<!-- Movie cards will be dynamically inserted here via JavaScript -->
		</div>
	</section>

	<!-- Footer -->
	<footer>
		<p>© 2024 Movie Hall Booking || Terms & Conditions || Privacy Policy</p>
	</footer>
	<script src="./js/script.js" defer></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.6/dist/umd/popper.min.js"></script>
	<script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>