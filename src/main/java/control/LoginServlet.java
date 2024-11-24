package control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import dao.UserDAO;
import pojo.User;

/**
 * The {@code LoginServlet} class handles user login requests for the application.
 * <p>
 * This servlet interacts with the {@link dao.UserDAO} to verify user credentials by comparing 
 * the provided password with the stored hashed password. If the authentication is successful, 
 * the user is redirected to the home page, and their session is created.
 * If the authentication fails, an error message is displayed on the login page.
 * </p>
 * 
 * <h2>Workflow:</h2>
 * <ol>
 *   <li>Receives the login credentials (email address and password) from the login form.</li>
 *   <li>Retrieves the stored hashed password for the given email address using {@link dao.UserDAO#getPasswordByEmail(String)}.</li>
 *   <li>Verifies the password using {@link org.mindrot.jbcrypt.BCrypt#checkpw(String, String)}.</li>
 *   <li>If the password is correct, creates a session for the user and redirects to {@code home.jsp}.</li>
 *   <li>If the password is incorrect or the user does not exist, forwards the request to {@code login.jsp} with an appropriate error message.</li>
 *   <li>Handles any exceptions that occur during the process and forwards to {@code login.jsp} with a failure message.</li>
 * </ol>
 * 
 * <h2>Dependencies:</h2>
 * <ul>
 *   <li>{@link dao.UserDAO} - Data Access Object for interacting with the user database.</li>
 *   <li>{@link pojo.User} - The User POJO representing the user data.</li>
 *   <li>{@link org.mindrot.jbcrypt.BCrypt} - Library for hashing and verifying passwords.</li>
 * </ul>
 * 
 * <h2>Servlet Mapping:</h2>
 * <p>
 * The servlet is mapped to the URL pattern <strong>/LoginServlet</strong>.
 * </p>
 * 
 * @author Susanta Chowdhury, Rishabh Gon.
 * @version 2.1
 * @since 2024-11-22
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	/** Serial version UID for ensuring class compatibility during serialization. */
	private static final long serialVersionUID = 1L;
	
	/** The user object used to store user details once authenticated. */
	private User user;

	/**
     * Default constructor for the servlet.
     * Initializes the {@link pojo.User} object to be used during the login process.
     */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * Initializes the servlet and sets up the user object.
     * This method is called once when the servlet is loaded into memory by the servlet container.
     */
	@Override
	public void init() {
		user = new User();
	}

	 /**
     * Handles HTTP POST requests for user login.
     * <p>
     * This method processes the login form by retrieving the email address and password from the request,
     * checking the credentials using {@link dao.UserDAO} and {@link BCrypt}, and performing the appropriate action:
     * </p>
     * <ul>
     *   <li>If the login is successful, it redirects to the home page and creates a session for the user.</li>
     *   <li>If the login fails due to an incorrect password or a non-existing user, it forwards the request to the login page with an error message.</li>
     *   <li>If an exception occurs, it forwards the request to the login page with a failure message.</li>
     * </ul>
     * 
     * @param request the {@link HttpServletRequest} object containing the request from the client.
     * @param response the {@link HttpServletResponse} object containing the response to the client.
     * @throws ServletException if an error occurs during servlet processing.
     * @throws IOException if an I/O error occurs during request handling.
     */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		RequestDispatcher rd;

		try {
			// Retrieve the stored hashed password for the given email address
			String storedHashedPass = UserDAO.getPasswordByEmail(email);
			if (storedHashedPass != null) {
				// Verify the hashed password with the provided password
				if (BCrypt.checkpw(password, storedHashedPass)) {
					user = UserDAO.getUserByEmail(email);
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					response.sendRedirect("home.jsp");
				} else {
					request.setAttribute("result", "incorrect password");
					rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				}
			} else {
				request.setAttribute("result", "user not found");
				rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			// Handle exceptions and forward to the login page with an error message
			request.setAttribute("result", "failure");
			System.err.println("Login failed due to: " + e.getMessage());
			rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}

	}

}
