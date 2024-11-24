package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pojo.Movie;
import pojo.Seat;
import pojo.Ticket;


/**
 * The {@code TicketDAO} class provides functionality for managing and retrieving ticket-related information.
 * <p>
 * This class interacts with the {@link dao.SeatDAO} to gather data about movies and seats booked by a user.
 * It creates a comprehensive list of tickets, including booked movies and associated seats, for a given user.
 * </p>
 * 
 * <h2>Dependencies:</h2>
 * <ul>
 *   <li>{@link dao.SeatDAO} - Used to fetch seat and movie booking details.</li>
 *   <li>{@link pojo.Movie} - Represents a movie entity.</li>
 *   <li>{@link pojo.Seat} - Represents a seat entity.</li>
 *   <li>{@link pojo.Ticket} - Represents a ticket containing movie and seat details.</li>
 * </ul>
 * 
 * <h2>Workflow:</h2>
 * <p>
 * The {@code TicketDAO} class retrieves the user's tickets by:
 * </p>
 * <ol>
 *   <li>Fetching a list of movies booked by the user via {@link dao.SeatDAO#getBookedMoviesByUserId(int)}.</li>
 *   <li>Retrieving the seats booked by the user for each movie using {@link dao.SeatDAO#getSeatsForMovie(int, int)}.</li>
 *   <li>Creating {@link pojo.Ticket} objects for each movie, including its associated seats.</li>
 *   <li>Compiling the tickets into a list and returning it.</li>
 * </ol>
 * 
 * @author Susanta Chowdhury, Rishabh Gon.
 * @version 2.0
 * @since 2024-11-22
 */
public class TicketDAO {

	public List<Ticket> getTicketsForUser(int userId) throws SQLException {

		SeatDAO seatDAO = new SeatDAO();
		Ticket ticket = new Ticket();

		List<Ticket> tickets = new ArrayList<>();
		List<Movie> bookedMovies = seatDAO.getBookedMoviesByUserId(userId);
		List<Seat> bookedSeatsOfMovie = new ArrayList<>();

		for (Movie bookedMovie : bookedMovies) {
			int bookedMovieId = bookedMovie.getId();
			bookedSeatsOfMovie = seatDAO.getSeatsForMovie(bookedMovieId, userId);
			ticket = new Ticket(bookedMovie, bookedSeatsOfMovie);
			tickets.add(ticket);
		}
		return tickets;
	}
}
