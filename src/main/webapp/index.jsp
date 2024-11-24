<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Get U'r Seats</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/homeStyles.css">
</head>

<body>
	<!-- Navigation Bar -->
	<nav class="navbar navbar-expand-lg navbar-dark">
		<a class="navbar-brand" href="#">Get U'r Seats</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
				<li class="nav-item"><a class="nav-link" href="register.jsp">Register</a></li>
			</ul>
		</div>
	</nav>

	<!-- Hero Section -->
	<header class="hero">
		<%
		String result = (String) request.getAttribute("result");
		if (result != null) {
			if ("success".equals(result)) {
				out.print("<div class='message'><p>Registration Successfull! Log in to Book your Seats.</p></div>");
			}
		}
		%>
		<div class="hero-content">
			<h2>Get your Seats Booked Now!</h2>
			<h5>Book tickets for the latest movies, select your seats, and
				enjoy a great cinematic experience.</h5>

			<!-- <a href="movies.jsp" class="btn-primary">Browse Movies</a> -->
		</div>
	</header>

	<!-- Featured Movies Section -->
	<section class="featured-movies">
		<h2>Now Showing</h2>
		<div class="movie-grid" id="movieGrid">
			<!-- Movie cards will be dynamically inserted here via JavaScript -->
		</div>
	</section>

	<!-- Footer -->
	<footer>
		<p>© 2024 Get U'r Seats || Terms & Conditions || Privacy Policy</p>
	</footer>
	<script src="./js/script.js" defer></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>