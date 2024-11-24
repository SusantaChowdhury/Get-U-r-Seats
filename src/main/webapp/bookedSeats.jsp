<%@ page import="pojo.Ticket"%>
<%@ page import="pojo.Seat"%>
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
<link rel="stylesheet" href="css/bookedSeats.css">
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
				<li class="nav-item"><a class="nav-link" href="MovieServlet">Browse
						Movies</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="FetchBookedSeatsServlet">My Seats</a></li>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a>
				</li>
			</ul>
		</div>
	</nav>
	<h1 class="text-center">My Seats</h1>

	<div class="movie-container">
		<%
		List<Ticket> tickets = (List<Ticket>) request.getAttribute("Tickets");
		%>

		<div
			class="row row-cols-1 row-cols-md-2 row-cols-lg-3 justify-content-center">

			<%
			for (Ticket ticket : tickets) {
			%>

			<div class="col">
				<div class="card movie-card">
					<img src="img/<%=ticket.getMovie().getId()%>.png"
						class="card-img-top movie-image"
						alt="<%=ticket.getMovie().getTitle()%>">
					<div class="card-body d-flex flex-column">
						<h5 class="card-title"><%=ticket.getMovie().getTitle()%></h5>
						<p class="card-text text-muted"><%=ticket.getMovie().getGenre()%></p>
						<p class="card-text">
							Rating:
							<%=ticket.getMovie().getRating()%>/10
						</p>
						<p class="card-text">
							Genre:
							<%=ticket.getMovie().getGenre()%>
						</p>
						<p class="card-text">
							Release Year:
							<%=ticket.getMovie().getReleaseYear()%>
						</p>
						<p>
							Seats:
							<%
						for (Seat seat : ticket.getSeats()) {
							out.print(seat.getRowLetter() + seat.getSeatNumber() + " ");
						}
						%>
						</p>


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