<%@ page import="pojo.Movie"%>
<%@ page import="pojo.User"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Available Movies</title>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/movieListStyles.css">
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setHeader("Expires", "0"); // Proxies

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
				<li class="nav-item active"><a class="nav-link"
					href="MovieServlet">Browse Movies</a></li>
				<li class="nav-item"><a class="nav-link"
					href="FetchBookedSeatsServlet">My Seats</a></li>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a>
				</li>
			</ul>
		</div>
	</nav>


	<div class="movie-container">
		<%
		List<Movie> movies = (List<Movie>) request.getAttribute("movies");
		%>
		<h1 class="text-center">Available Movies</h1>
		<div
			class="row row-cols-1 row-cols-md-2 row-cols-lg-3 justify-content-center">

			<%
			for (Movie movie : movies) {
			%>

			<div class="col">
				<div class="card movie-card">
					<img src="img/<%=movie.getId()%>.png"
						class="card-img-top movie-image" alt="<%=movie.getTitle()%>">
					<div class="card-body d-flex flex-column">
						<h5 class="card-title"><%=movie.getTitle()%></h5>
						<p class="card-text text-muted"><%=movie.getGenre()%></p>
						<p class="card-text">
							Rating:
							<%=movie.getRating()%>/10
						</p>
						<p class="card-text">
							Release Year:
							<%=movie.getReleaseYear()%></p>
						<form action="SeatSelectionServlet" method="post" class="mt-auto">
							<input type="hidden" name="movieId" value="<%=movie.getId()%>">
							<button type="submit" class="btn-secondary">Book Seats</button>
						</form>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
	<!-- Footer -->
	<footer>
		<p>© 2024 Movie Hall Booking || Terms & Conditions || Privacy
			Policy</p>
	</footer>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.6/dist/umd/popper.min.js"></script>
	<script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>