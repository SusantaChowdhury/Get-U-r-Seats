package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The {@code LogoutServlet} class is a servlet responsible for handling user logout functionality.
 * <p>
 * This servlet is invoked when a user wants to log out of the application. It handles the {@code GET} requests by 
 * removing the user and movie attributes from the session, invalidating the session, and then redirecting the user 
 * to the homepage (index.jsp).
 * </p>
 * 
 * <h2>Workflow:</h2>
 * <ol>
 *   <li>Retrieve the current session using {@link HttpServletRequest#getSession()}.</li>
 *   <li>Remove the "user" and "movie" attributes from the session to clear any user-related data.</li>
 *   <li>Invalidate the session to ensure that all session-related data is discarded.</li>
 *   <li>Redirect the user to the home page ({@code index.jsp}) using {@link HttpServletResponse#sendRedirect(String)}.</li>
 * </ol>
 * 
 * <h2>Dependencies:</h2>
 * <ul>
 *   <li>{@link javax.servlet.http.HttpSession} - Provides the session management capabilities for storing and removing session attributes.</li>
 *   <li>{@link javax.servlet.http.HttpServletRequest} - Used to obtain the session and request details.</li>
 *   <li>{@link javax.servlet.http.HttpServletResponse} - Used to send the redirection response after logout.</li>
 * </ul>
 * 
 * <h2>Servlet Mapping:</h2>
 * <p>
 * The servlet is mapped to the URL pattern <strong>/LogoutServlet</strong>.
 * </p>
 * 
 * @author Susanta Chowdhury, Rishabh Gon.
 * @version 2.0
 * @since 2024-11-22
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	
	/** Serial version UID for ensuring class compatibility during serialization. */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor for the servlet. 
     * This constructor is invoked when the servlet is instantiated by the servlet container.
     */
	public LogoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * Handles HTTP {@code GET} requests for logging out the user.
     * <p>
     * This method removes the "user" and "movie" attributes from the session, invalidates the session, and then
     * redirects the user to the homepage ({@code index.jsp}). This is typically used when a user decides to log out.
     * </p>
     * 
     * @param request the {@link HttpServletRequest} object containing the request from the client.
     * @param response the {@link HttpServletResponse} object containing the response to the client.
     * @throws ServletException if an error occurs during servlet processing.
     * @throws IOException if an I/O error occurs during response handling.
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.removeAttribute("movie");
		session.invalidate();
		response.sendRedirect("index.jsp");
	}

}
