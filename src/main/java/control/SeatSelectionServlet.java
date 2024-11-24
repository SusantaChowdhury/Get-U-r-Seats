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
import dao.MovieDAO;
import dao.SeatDAO;
import pojo.Movie;
import pojo.Seat;

/**
 * The {@code SeatSelectionServlet} class handles the selection of seats for a particular movie.
 * <p>
 * This servlet processes POST requests where it retrieves the movie ID from the request,
 * fetches the corresponding movie details using {@link dao.MovieDAO}, and then fetches the list of booked seats
 * associated with that movie using {@link dao.SeatDAO}.
 * It forwards the results to the {@code seatMap.jsp} page for rendering the seat map.
 * </p>
 * 
 * <h2>Workflow:</h2>
 * <ol>
 *   <li>The movie ID is retrieved from the HTTP request parameters.</li>
 *   <li>The movie details are fetched using {@link dao.MovieDAO#getMovieById(int)}.</li>
 *   <li>The booked seats for the movie are fetched using {@link dao.SeatDAO#getBookedSeatsByMovieId(int)}.</li>
 *   <li>The movie and booked seats are set as request and session attributes.</li>
 *   <li>The request is forwarded to {@code seatMap.jsp} to display the seat map to the user.</li>
 *   <li>If any SQL exception occurs while fetching data, an internal server error is returned with a relevant message.</li>
 * </ol>
 * 
 * <h2>Dependencies:</h2>
 * <ul>
 *   <li>{@link dao.MovieDAO} - Data Access Object for retrieving movie details from the database.</li>
 *   <li>{@link dao.SeatDAO} - Data Access Object for retrieving booked seat details from the database.</li>
 *   <li>{@link pojo.Movie} - POJO representing a movie.</li>
 *   <li>{@link pojo.Seat} - POJO representing a seat.</li>
 * </ul>
 * 
 * <h2>Servlet Mapping:</h2>
 * <p>
 * The servlet is mapped to the URL pattern <strong>/SeatSelectionServlet</strong>.
 * </p>
 * 
 * @author Susanta Chowdhury, Rishabh Gon.
 * @version 2.2
 * @since 2024-11-22
 */
@WebServlet("/SeatSelectionServlet")
public class SeatSelectionServlet extends HttpServlet {
	
	/** Serial version UID for ensuring class compatibility during serialization. */
	private static final long serialVersionUID = 1L;

	/** The SeatDAO object used to interact with the seat database. */
	private SeatDAO seatDAO;
	
	/**
     * Default constructor for the servlet.
     * Initializes the {@link dao.SeatDAO} object for use during the request processing.
     */
	public SeatSelectionServlet() {
		super();
	}

	/**
     * Initializes the servlet by creating an instance of {@link dao.SeatDAO}.
     * This method is called once when the servlet is loaded into memory by the servlet container.
     */
	@Override
	public void init() {
		seatDAO = new SeatDAO();
	}

	/**
     * Handles HTTP POST requests for selecting seats for a specific movie.
     * <p>
     * This method retrieves the movie ID from the HTTP request, fetches the corresponding movie details,
     * retrieves the booked seats for the selected movie, and forwards the request and response to the
     * {@code seatMap.jsp} page for rendering.
     * </p>
     * 
     * @param request the {@link HttpServletRequest} object containing the request from the client.
     * @param response the {@link HttpServletResponse} object containing the response to the client.
     * @throws ServletException if an error occurs during servlet processing.
     * @throws IOException if an I/O error occurs during request handling.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int movieId = Integer.parseInt(request.getParameter("movieId"));

		Movie movie = MovieDAO.getMovieById(movieId);
		System.out.println(movie.getTitle());

		try {
			List<Seat> bookedSeats = seatDAO.getBookedSeatsByMovieId(movieId);
			HttpSession session = request.getSession();
			session.setAttribute("movie", movie);
			request.setAttribute("bookedSeats", bookedSeats);
			RequestDispatcher rd = request.getRequestDispatcher("seatMap.jsp");
			rd.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching seat data.");
		}
	}

}
