package control;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import dao.UserDAO;

/**
 * The {@code RegisterServlet} class handles user registration by collecting and
 * processing user data submitted through an HTTP POST request.
 * <p>
 * The servlet interacts with the {@link dao.UserDAO} to store user information
 * securely in the database. Passwords are hashed using the
 * {@link org.mindrot.jbcrypt.BCrypt} library to ensure data security.
 * </p>
 * 
 * <h2>Workflow:</h2>
 * <ol>
 * <li>Receives registration details such as user name, email, password, and
 * confirm password.</li>
 * <li>Validates the email address against accepted domains such as gmail.com,
 * yahoo.com, rediffmail.com, etc.</li>
 * <li>Validates the passwords for equality.</li>
 * <li>Hashes the password using {@link BCrypt} and stores the user data in the
 * database.</li>
 * <li>Provides appropriate feedback to the user depending on the result of the
 * registration process.</li>
 * </ol>
 * 
 * <h2>Dependencies:</h2>
 * <ul>
 * <li>{@link dao.UserDAO} - Data Access Object for database operations.</li>
 * <li>{@link org.mindrot.jbcrypt.BCrypt} - Library for hashing passwords
 * securely.</li>
 * </ul>
 * 
 * <h2>Servlet Mapping:</h2>
 * <p>
 * The servlet is mapped to the URL pattern <strong>/RegisterServlet</strong>.
 * </p>
 * 
 * @author Susanta Chowdhury, Rishabh Gon.
 * @version 2.1
 * @since 2024-11-22
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	/** Serial version UID for ensuring class compatibility during serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor for the servlet. Initializes the servlet instance and
	 * provides a hook for servlet container-specific operations.
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handles HTTP POST requests for user registration.
	 * <p>
	 * Collects user information from the registration form, validates input, and
	 * stores the data in the database using {@link dao.UserDAO}.
	 * </p>
	 * 
	 * <h3>Process:</h3>
	 * <ol>
	 * <li>Retrieves form data including username, email, password, and confirm
	 * password.</li>
	 * <li>Validates that the password and confirm password fields match.</li>
	 * <li>Hashes the password securely using
	 * {@link BCrypt#hashpw(String, String)}.</li>
	 * <li>Calls {@link dao.UserDAO#registerUser(String, String, String) } to store
	 * the data in the database.</li>
	 * <li>Forwards the user to appropriate pages based on the result of the
	 * operation:
	 * <ul>
	 * <li><strong>Invalid email:</strong> Redirects to the registration page with a
	 * generic error message.</li>
	 * <li><strong>Invalid domain:</strong> Redirects to the registration page with
	 * a generic error message.</li>
	 * <li><strong>Success:</strong> Redirects to the index page.</li>
	 * <li><strong>User Exists:</strong> Redirects to the registration page with an
	 * error message.</li>
	 * <li><strong>Failure:</strong> Redirects to the registration page with a
	 * generic error message.</li>
	 * </ul>
	 * </li>
	 * </ol>
	 * 
	 * @param request  the {@link HttpServletRequest} object containing the request
	 *                 from the client.
	 * @param response the {@link HttpServletResponse} object containing the
	 *                 response to the client.
	 * @throws ServletException if an error occurs during servlet processing.
	 * @throws IOException      if an I/O error occurs during request handling.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		System.out.println("username : " + username);
		System.out.println("email : " + email);
		System.out.println("password : " + password);
		System.out.println("confirm password : " + confirmPassword);

		RequestDispatcher rd;

		/**
		 * The array of accepted email domains.
		 */
		String[] allowedDomains = { "gmail.com", "yahoo.com", "rediffmail.com" };

		// Email validation using regex pattern
		Pattern emailPattern = Pattern
				.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher emailMatcher = emailPattern.matcher(email);

		// Validate email format
		if (!emailMatcher.matches()) {
			request.setAttribute("result", "invalid email");
			rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
			return; // Stop further processing if email is invalid
		}

		/**
		 * To extract and store the domain from the user's email address.
		 */
		String domain = email.substring(email.indexOf('@') + 1);

		/**
		 * Variable to store the boolean value if the email is valid.
		 */
		boolean isValidDomain = false;
		for (String allowedDomain : allowedDomains) {
			if (domain.equalsIgnoreCase(allowedDomain)) {
				isValidDomain = true;
				break;
			}
		}

		if (!isValidDomain) {
			request.setAttribute("result", "invalid domain");
			rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
			return; // Stop further processing if domain is invalid
		}

		if (password != null && password.equals(confirmPassword)) {

			try {

				String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
				System.out.println("Hashed Password :" + hashedPassword);

				String result = UserDAO.registerUser(username, hashedPassword, email);
				System.out.println("result(in RegisterServlet) :" + result);

				if ("success".equals(result)) {
					request.setAttribute("result", "success");
					rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				} else if ("user exists".equals(result)) {
					request.setAttribute("result", "user exists");
					rd = request.getRequestDispatcher("register.jsp");
					rd.forward(request, response);

				} else if ("failure".equals(result)) {
					request.setAttribute("result", "failure");
					rd = request.getRequestDispatcher("register.jsp");
					rd.forward(request, response);
				}

			} catch (ClassNotFoundException ex) {
				request.setAttribute("result", "failure");
				System.out.println("Registration failed due to : " + ex.getMessage());
				rd = request.getRequestDispatcher("register.jsp");
				rd.forward(request, response);

			}
		} else {
			request.setAttribute("result", "passwords mismatch");
			rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
		}

	}

}
