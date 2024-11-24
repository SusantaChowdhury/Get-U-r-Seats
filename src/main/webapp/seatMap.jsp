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
<title>Seat Selection</title>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/seatMapStyles.css">
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
			<li class="nav-item"><a class="nav-link"
					href="home.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="MovieServlet">Browse
						Movies</a></li>
				<li class="nav-item"><a class="nav-link"
					href="FetchBookedSeatsServlet">My Seats</a></li>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a>
				</li>
			</ul>
		</div>
	</nav>

	<div class="container">


		<%
		Movie movie = (Movie) session.getAttribute("movie");
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
				<p>
					<%=movie.getDesc()%>
				</p>
			</div>
		</div>

		<!-- Screen Section -->
		<div class="screen">
			<p>Screen this side</p>
		</div>


		<!-- Silver Section -->
		<div class="seat-section" id="silver-section">
			<h3>Silver (Rs 120)</h3>
			<div class="seat-grid">
				<%
				String[] rows = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"};
				List<Seat> bookedSeats = (List<Seat>) request.getAttribute("bookedSeats");
				String startRow = "A";
				String endRow = "C";
				int totalSeatsPerRow = 20;
				boolean withinRange = false;

				for (String row : rows) {
					if (row.equals(startRow))
						withinRange = true;
					if (withinRange) {
						for (int seatNumber = 1; seatNumber <= totalSeatsPerRow; seatNumber++) {
					String seatClass = "seat";
					if (bookedSeats != null) {
						for (Seat bookedSeat : bookedSeats) {
							if (bookedSeat.getRowLetter().equals(row) && bookedSeat.getSeatNumber() == seatNumber) {
								seatClass = "seat booked";
								break;
							}
						}
					}
					out.write("<button type='submit' class='" + seatClass + "' value='" + row + seatNumber + "'>" + row
							+ seatNumber + "</button>");
						}
					}
					if (row.equals(endRow))
						break;
				}
				%>
			</div>
		</div>

		<!-- Gold Section -->
		<div class="seat-section" id="gold-section">
			<h3>Gold (Rs 200)</h3>
			<div class="seat-grid">
				<%
				startRow = "D";
				endRow = "I";
				totalSeatsPerRow = 20;
				withinRange = false;

				for (String row : rows) {
					if (row.equals(startRow))
						withinRange = true;
					if (withinRange) {
						for (int seatNumber = 1; seatNumber <= totalSeatsPerRow; seatNumber++) {
					String seatClass = "seat";
					if (bookedSeats != null) {
						for (Seat bookedSeat : bookedSeats) {
							if (bookedSeat.getRowLetter().equals(row) && bookedSeat.getSeatNumber() == seatNumber) {
								seatClass = "seat booked";
								break;
							}
						}
					}
					out.write("<button type='submit' class='" + seatClass + "' value='" + row + seatNumber + "'>" + row
							+ seatNumber + "</button>");
						}
					}
					if (row.equals(endRow))
						break;
				}
				%>
			</div>
		</div>

		<!-- Platinum Section -->
		<div class="seat-section" id="platinum-section">
			<h3>Platinum (Rs 250)</h3>
			<div class="seat-grid">
				<%
				startRow = "J";
				endRow = "M";
				totalSeatsPerRow = 20;
				withinRange = false;

				for (String row : rows) {
					if (row.equals(startRow))
						withinRange = true;
					if (withinRange) {
						for (int seatNumber = 1; seatNumber <= totalSeatsPerRow; seatNumber++) {
					String seatClass = "seat";
					if (bookedSeats != null) {
						for (Seat bookedSeat : bookedSeats) {
							if (bookedSeat.getRowLetter().equals(row) && bookedSeat.getSeatNumber() == seatNumber) {
								seatClass = "seat booked";
								break;
							}
						}
					}
					out.write("<button type='submit' class='" + seatClass + "' value='" + row + seatNumber + "'>" + row
							+ seatNumber + "</button>");
						}
					}
					if (row.equals(endRow))
						break;
				}
				%>
			</div>
		</div>

		<!-- Legend -->
		<div class="legend">
			<div class="legend-item">
				<div class="seat"></div>
				Available
			</div>
			<div class="legend-item">
				<div class="seat selected"></div>
				Selected
			</div>
			<div class="legend-item">
				<div class="seat booked"></div>
				Booked
			</div>
		</div>

		<!-- Selected Seats Display -->
		<div class="selected-seats">
			<h3>Selected Seats:</h3>
			<div id="selectedSeatsList"></div>
		</div>

		<!-- Action Buttons -->
		<div class="actions">
			<form action="SeatsConfirmationServlet" method="post">
				<input type="hidden" id="hiddenInput" name="selectedSeats" value="">
				<button class="button" id="confirmSelection">Confirm
					Booking</button>
			</form>
		</div>

	</div>

	<footer>
		<p>© 2024 Get U'r Seats || Terms & Conditions || Privacy Policy</p>
	</footer>



	<script type="text/javascript">
	document.addEventListener("DOMContentLoaded", function () {
	    const selectedSeatsList = document.getElementById("selectedSeatsList");
	    const seats = document.querySelectorAll(".seat:not(.booked)");
	    const selectedSeats = new Set();
	    const confirmButton = document.getElementById("confirmSelection");

	    selectedSeatsList.textContent = "No seats selected.";
	    // Update the Selected Seats area
	    function updateSelectedSeats() {
	        if (selectedSeats.size === 0) {
	            
	            document.getElementById("hiddenInput").setAttribute("value", "");
	        } else {
	            selectedSeatsList.textContent = Array.from(selectedSeats).join(", ");
	            document.getElementById("hiddenInput").setAttribute("value", selectedSeatsList.innerText);
	        }
	    }

	    // Add click event listeners to toggle seat selection
	    seats.forEach((seat) => {
	        seat.addEventListener("click", function () {
	            const seatId = this.value;
				console.log(seatId);
	            if (selectedSeats.has(seatId)) {
	                selectedSeats.delete(seatId);
	                this.classList.remove("selected");
	            } else {
	                selectedSeats.add(seatId);
	                this.classList.add("selected");
	            }

	            updateSelectedSeats();
	        });
	    });
	    
	    function sendSelectedSeats(){
	    	if(selectedSeatsList.textContent=="No seats selected."){
	    		alert("Please select at least one seat to Confirm Booking.")
	    	}else{
	    		 updateSelectedSeats();
	    	}
	    }
	        
	    
	    // Add event listener to the Confirm Booking button
	    confirmButton.addEventListener("click", sendSelectedSeats);
	    
	    
	 

	    // Initialize the display
	    updateSelectedSeats();	
	});

	</script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.6/dist/umd/popper.min.js"></script>
	<script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
