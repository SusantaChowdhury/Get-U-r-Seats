package control;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.SeatDAO;
import pojo.Movie;
import pojo.User;

/**
 * The {@code SeatsConfirmationServlet} class handles the confirmation of selected seats 
 * and calculates the total cost for the user during the booking process.
 * <p>
 * This servlet processes POST requests where the selected seats are retrieved from the request,
 * and the total cost is calculated based on those selections using {@link dao.SeatDAO#calculateTotalCost(String[])}.
 * The servlet forwards the request and session attributes to the {@code bookingDetails.jsp} page for displaying
 * the booking details to the user.
 * </p>
 * 
 * <h2>Workflow:</h2>
 * <ol>
 *   <li>The selected seats are retrieved from the HTTP request as a comma-separated string.</li>
 *   <li>The selected seats are split into an array for further processing.</li>
 *   <li>The movie and user objects are retrieved from the HTTP session.</li>
 *   <li>The total cost for the selected seats is calculated using {@link dao.SeatDAO#calculateTotalCost(String[])}.</li>
 *   <li>The selected seats and total cost are set as session and request attributes.</li>
 *   <li>The request is forwarded to {@code bookingDetails.jsp} for displaying the booking details.</li>
 * </ol>
 * 
 * <h2>Dependencies:</h2>
 * <ul>
 *   <li>{@link dao.SeatDAO} - Data Access Object for calculating the total cost of selected seats.</li>
 *   <li>{@link pojo.Movie} - POJO representing a movie, used to fetch movie details from the session.</li>
 *   <li>{@link pojo.User} - POJO representing a user, used to fetch user details from the session.</li>
 * </ul>
 * 
 * <h2>Servlet Mapping:</h2>
 * <p>
 * The servlet is mapped to the URL pattern <strong>/SeatsConfirmationServlet</strong>.
 * </p>
 * 
 * @author Susanta Chowdhury, Rishabh Gon.
 * @version 2.0
 * @since 2024-11-22
 */
@WebServlet("/SeatsConfirmationServlet")
public class SeatsConfirmationServlet extends HttpServlet {
	
	/** Serial version UID for ensuring class compatibility during serialization. */
	private static final long serialVersionUID = 1L;

	/** The {@link pojo.Movie} object representing the movie selected by the user. */
	private Movie movie;
	/** The {@link pojo.User} object representing the user who is confirming the seat selection. */
	private User user;

	/**
     * Default constructor for the servlet.
     * Initializes the {@link pojo.Movie} and {@link pojo.User} objects.
     */
	public SeatsConfirmationServlet() {
		super();
	}

	
	/**
     * Initializes the servlet by creating instances of {@link pojo.Movie} and {@link pojo.User}.
     * This method is called once when the servlet is loaded into memory by the servlet container.
     */
	@Override
	public void init() {
		movie = new Movie();
		user = new User();
	}

	/**
     * Handles HTTP POST requests for confirming selected seats and calculating the total cost.
     * <p>
     * This method retrieves the selected seats from the HTTP request, splits the comma-separated seat values into an array,
     * retrieves the movie and user details from the session, calculates the total cost of the selected seats using {@link dao.SeatDAO#calculateTotalCost(String[])},
     * and forwards the request and session attributes to the {@code bookingDetails.jsp} page.
     * </p>
     * 
     * @param request the {@link HttpServletRequest} object containing the request from the client.
     * @param response the {@link HttpServletResponse} object containing the response to the client.
     * @throws ServletException if an error occurs during servlet processing.
     * @throws IOException if an I/O error occurs during request handling.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String selectedSeats = request.getParameter("selectedSeats");
		String[] seats = selectedSeats.split(", ");
		
		System.out.println(Arrays.toString(seats));
		
		

		HttpSession session = request.getSession();

		movie = (Movie) session.getAttribute("movie");
		movie.getId();

		user = (User) session.getAttribute("user");
		user.getId();
		
		double totalCost = SeatDAO.calculateTotalCost(seats);
		
//		
		
		session.setAttribute("seats", seats);
		request.setAttribute("totalCost", totalCost);
		
		RequestDispatcher rd = request.getRequestDispatcher("bookingDetails.jsp");
		rd.forward(request, response);

	}
}