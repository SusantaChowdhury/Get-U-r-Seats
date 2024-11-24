package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.TicketDAO;
import pojo.Ticket;
import pojo.User;

/**
 * The {@code FetchBookedSeatsServlet} class is a servlet responsible for fetching and displaying the
 * list of booked seats for a specific user.
 * <p>
 * This servlet interacts with the {@link dao.TicketDAO} to retrieve the tickets associated with a logged-in user.
 * It retrieves the user's ID from the session and fetches the corresponding tickets from the database.
 * The list of booked tickets is then forwarded to the {@code bookedSeats.jsp} page for display.
 * </p>
 * 
 * <h2>Workflow:</h2>
 * <ol>
 *   <li>Retrieves the user object from the session.</li>
 *   <li>Extracts the user ID from the user object.</li>
 *   <li>Fetches the list of tickets associated with the user ID using {@link dao.TicketDAO#getTicketsForUser(int)}.</li>
 *   <li>Sets the list of tickets as a request attribute and forwards the request to {@code bookedSeats.jsp} for rendering.</li>
 *   <li>If an error occurs while fetching tickets, an internal server error is returned with an error message.</li>
 * </ol>
 * 
 * <h2>Dependencies:</h2>
 * <ul>
 *   <li>{@link dao.TicketDAO} - Data Access Object for interacting with the ticket database.</li>
 *   <li>{@link pojo.Ticket} - POJO representing a ticket.</li>
 *   <li>{@link pojo.User} - POJO representing a user.</li>
 * </ul>
 * 
 * <h2>Servlet Mapping:</h2>
 * <p>
 * The servlet is mapped to the URL pattern <strong>/FetchBookedSeatsServlet</strong>.
 * </p>
 * 
 * @author Susanta Chowdhury, Rishabh Gon.
 * @version 2.2
 * @since 2024-11-22
 */
@WebServlet("/FetchBookedSeatsServlet")
public class FetchBookedSeatsServlet extends HttpServlet {
	
	/** Serial version UID for ensuring class compatibility during serialization. */
	private static final long serialVersionUID = 1L;

	/** The User object representing the currently logged-in user. */
	private User user;
	private TicketDAO ticketDAO;

	
	/**
     * Default constructor for the servlet.
     * Initializes the {@link dao.TicketDAO} and {@link pojo.User} objects for use during the request processing.
     */
	public FetchBookedSeatsServlet() {
		super();
	}
	
	 /**
     * Initializes the servlet by creating instances of {@link dao.TicketDAO} and {@link pojo.User}.
     * This method is called once when the servlet is loaded into memory by the servlet container.
     */
	@Override
	public void init() {
		ticketDAO =new TicketDAO();
		user = new User();
	}

	/**
     * Handles HTTP GET requests to fetch the booked seats for the currently logged-in user.
     * <p>
     * This method retrieves the user object from the session, extracts the user ID, 
     * and uses the {@link dao.TicketDAO} to fetch the list of tickets associated with that user.
     * The tickets are then forwarded to the {@code bookedSeats.jsp} page for display.
     * </p>
     * 
     * @param request the {@link HttpServletRequest} object containing the request from the client.
     * @param response the {@link HttpServletResponse} object containing the response to the client.
     * @throws ServletException if an error occurs during servlet processing.
     * @throws IOException if an I/O error occurs during request handling.
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Retrieve the session and user object
		HttpSession session = request.getSession();
		
		// Extract the user ID from the user object
		user = (User)session.getAttribute("user");
		int userId = user.getId();

		try {
			// Fetch the tickets associated with the user
			List<Ticket> Tickets = ticketDAO.getTicketsForUser(userId);
			// Set the tickets as a request attribute for the JSP page
			request.setAttribute("Tickets", Tickets);
			// Forward the request to the bookedSeats.jsp page for rendering
			RequestDispatcher dispatcher = request.getRequestDispatcher("bookedSeats.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			 // Handle SQL exceptions and forward an error message
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching seat data.");
		}
	}
}
