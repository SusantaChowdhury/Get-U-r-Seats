package control;

import java.io.IOException;
import java.sql.SQLException;

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
 * The {@code ConfirmBookingServlet} class handles the final confirmation of seat bookings for a movie.
 * <p>
 * This servlet processes POST requests to confirm the booking of seats for a particular user and movie. 
 * It retrieves the selected seats from the session, iterates through each seat, and books them by invoking 
 * the {@link dao.SeatDAO#bookSeat(int, int, String, int)} method. If all seats are successfully booked, it forwards the 
 * request to a confirmation page. Otherwise, it sets a failure result attribute and handles errors appropriately.
 * </p>
 * 
 * <h2>Workflow:</h2>
 * <ol>
 *   <li>Retrieve the selected seats from the HTTP session.</li>
 *   <li>Retrieve the movie and user details from the session.</li>
 *   <li>Iterate through the selected seats and attempt to book each one using {@link dao.SeatDAO#bookSeat(int, int, String, int)}.</li>
 *   <li>If all seats are booked successfully, forward the request to {@code bookingConfirmed.jsp} for confirmation.</li>
 *   <li>If any seat booking fails, set the result to "failure" and handle errors.</li>
 * </ol>
 * 
 * <h2>Dependencies:</h2>
 * <ul>
 *   <li>{@link dao.SeatDAO} - Data Access Object responsible for interacting with the database to book seats.</li>
 *   <li>{@link pojo.Movie} - POJO representing a movie, used to fetch movie details such as movie ID.</li>
 *   <li>{@link pojo.User} - POJO representing the user who is booking the seats, used to fetch user ID.</li>
 * </ul>
 * 
 * <h2>Servlet Mapping:</h2>
 * <p>
 * The servlet is mapped to the URL pattern <strong>/ConfirmBookingServlet</strong>.
 * </p>
 * 
 * @author Susanta Chowdhury, Rishabh Gon.
 * @version 2.0
 * @since 2024-11-22
 */
@WebServlet("/ConfirmBookingServlet")
public class ConfirmBookingServlet extends HttpServlet {
	
	/** Serial version UID for ensuring class compatibility during serialization. */
	private static final long serialVersionUID = 1L;

	/** The {@link dao.SeatDAO} object used for booking seats in the database. */
	private SeatDAO seatDAO;
	/** The {@link pojo.Movie} object representing the selected movie for booking. */
	private Movie movie;
	/** The {@link pojo.User} object representing the user who is confirming the seat booking. */
	private User user;
	
	/**
     * Default constructor for the servlet. 
     * Initializes the {@link dao.SeatDAO}, {@link pojo.Movie}, and {@link pojo.User} objects for use in the servlet.
     */
	public ConfirmBookingServlet() {
		super();
	}

	 /**
     * Initializes the servlet by creating instances of {@link dao.SeatDAO}, {@link pojo.Movie}, and {@link pojo.User}.
     * This method is called once when the servlet is loaded into memory by the servlet container.
     */
	@Override
	public void init() {
		seatDAO = new SeatDAO();
		movie = new Movie();
		user = new User();
	}
	
	/**
     * Handles HTTP POST requests for confirming seat bookings.
     * <p>
     * This method retrieves the selected seats from the session, iterates over each seat, and books it
     * by calling {@link dao.SeatDAO#bookSeat(int, int, String, int)}. If all seats are booked successfully, 
     * the request is forwarded to {@code bookingConfirmed.jsp}. If any seat booking fails, the result is set 
     * to "failure" and errors are handled appropriately.
     * </p>
     * 
     * @param request the {@link HttpServletRequest} object containing the request from the client.
     * @param response the {@link HttpServletResponse} object containing the response to the client.
     * @throws ServletException if an error occurs during servlet processing.
     * @throws IOException if an I/O error occurs during request handling.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Retrieve the selected seats from the HTTP session
		HttpSession session = request.getSession();
		String[] seats = (String[]) session.getAttribute("seats");

		// Retrieve the movie and user details from the session
		movie = (Movie) session.getAttribute("movie");
		int movieId = movie.getId();

		user = (User) session.getAttribute("user");
		int userId = user.getId();

		int count = 0;
//		
		// Iterate over each seat to book it
		for (String seat : seats) {

			String rowLetter = seat.substring(0, 1);
			int seatNumber = Integer.parseInt(seat.substring(1));
//			System.out.println("userId : " + userId);
//			System.out.println("movieId : " + movieId);
//			System.out.println("rowLetter : " + rowLetter);
//			System.out.println("seatNumber : " + seatNumber);
			try {
				// Attempt to book the seat in the database
				if (seatDAO.bookSeat(userId, movieId, rowLetter, seatNumber) > 0) {
					count++; // Increment count if booking was successful
				}

			} catch (SQLException e) {
				System.out.println("Error while booking...");
				e.printStackTrace();
			}
		}
		
		// Check if all seats were successfully booked
		if (count == seats.length) {
			// Forward to the booking confirmation page if all seats were booked
			request.setAttribute("result", "success");
			RequestDispatcher rd = request.getRequestDispatcher("bookingConfirmed.jsp");
			rd.forward(request, response);
		} else {
			// Set failure result if some seats couldn't be booked
			request.setAttribute("result", "failure");
		}
	}

}
