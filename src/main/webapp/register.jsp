<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration</title>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/registerStyles.css" rel="stylesheet">
</head>

<body class="register">

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
				<li class="nav-item "><a class="nav-link" href="login.jsp">Login</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="register.jsp">Register</a></li>
			</ul>
		</div>
	</nav>

	<!-- Login Form -->
	<div class="container">
		<div class="card-header text-center">
			<h4>Register</h4>
		</div>
		<div class="card-body">
			<form action="RegisterServlet" method="post">
				<div class="form-group">
					<input type="text" class="form-control" id="username"
						name="username" placeholder="Username" required> <i
						class='bx bxs-user'></i>
				</div>
				<div class="form-group">
					<input type="email" class="form-control" id="email" name="email"
						placeholder="Email" required> <i class='bx bxs-envelope'></i>
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="Password" required> <i
						class='bx bxs-lock'></i>
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="password"
						name="confirmPassword" placeholder="Confirm Password" required> <i
						class='bx bxs-lock'></i>
				</div>
				<%
				String result = (String) request.getAttribute("result");
				System.out.println(result);
				if (result != null) {
					if (result.equals("user exists")) {
						out.print("<div class='registrationError'><p>User already registered! Try Logging in...</p></div>");
					} else if (result.equals("passwords mismatch")) {
						out.print("<div class='registrationError'><p>Passwords mis-matched! Please try again...</p></div>");
					} else if (result.equals("failure")) {
						out.print("<div class='registrationError'><p>Registration failed! Please try again...</p></div>");
					}else if (result.equals("invalid domain")) {
						out.print("<div class='registrationError'><p>Invalid domain! Please enter a valid domain...</p></div>");
					}else if (result.equals("invalid email")) {
						out.print("<div class='registrationError'><p>Invalid email! Please enter a valid email address...</p></div>");
					}
				}
				%>

				<button type="submit" class="btn mt-3">Register</button>
			</form>
		</div>
		<div class="card-footer text-center">
			<small>Already have an account? <a href="login.jsp">Login
					here</a></small>
		</div>

	</div>


	<!-- Footer -->
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