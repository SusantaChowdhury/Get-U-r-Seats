package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pojo.Movie;
import pojo.Seat;

/**
 * The {@code SeatDAO} class provides data access operations related to seats and movies for a ticket booking system.
 * <p>
 * It handles interactions with the database to retrieve and manage information about booked seats, 
 * booked movies, and booking operations for users.
 * </p>
 * 
 * <h2>Dependencies:</h2>
 * <ul>
 *   <li>{@link DBConnection} - For establishing connections to the database.</li>
 *   <li>{@link pojo.Movie} - Represents movie details.</li>
 *   <li>{@link pojo.Seat} - Represents seat details.</li>
 * </ul>
 * 
 * <h2>Workflow:</h2>
 * <p>
 * The {@code SeatDAO} class performs operations such as:
 * </p>
 * <ul>
 *   <li>Fetching seats for movies based on user and movie IDs.</li>
 *   <li>Retrieving all booked seats for a movie or a user.</li>
 *   <li>Booking seats for users.</li>
 *   <li>Fetching movies booked by a user.</li>
 *   <li>Calculating the total cost of seat bookings based on seat locations.</li>
 * </ul>
 * 
 * @author Susanta Chowdhury, Rishabh Gon.
 * @version 2.1
 * @since 2024-11-22
 */
public class SeatDAO {

	/**
     * Retrieves the seats booked by a specific user for a given movie.
     * 
     * @param movieId the unique identifier of the movie.
     * @param userId  the unique identifier of the user.
     * @return a {@code List<Seat>} containing seats booked by the user for the movie.
     * @throws SQLException if a database access error occurs.
     * 
     * <h3>Workflow:</h3>
     * <ol>
     *   <li>Connect to the database using {@link dao.DBConnection#getConnection()}.</li>
     *   <li>Execute the query to fetch booked seat details for the user and movie.</li>
     *   <li>Populate a list of {@link pojo.Seat} objects with the retrieved data.</li>
     * </ol>
     */
	public List<Seat> getSeatsForMovie(int movieId, int userId) throws SQLException {
		List<Seat> seats = new ArrayList<>();
		String query = "select * from booked_seats where movie_id = ? and user_id = ?";

		try (Connection conn = DBConnection.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, movieId);
			stmt.setInt(2, userId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String rowLetter = rs.getString("row_letter");
				int seatNumber = rs.getInt("seat_number");
				seats.add(new Seat(rowLetter, seatNumber));
			}
		}
		return seats;
	}

	/**
     * Retrieves all seats booked for a specific movie.
     * 
     * @param movieId the unique identifier of the movie.
     * @return a {@code List<Seat>} containing all booked seats for the movie.
     * @throws SQLException if a database access error occurs.
     * 
     * <h3>Workflow:</h3>
     * <ol>
     *   <li>Connect to the database using {@link dao.DBConnection#getConnection()}.</li>
     *   <li>Execute the query to fetch seat details for the specified movie.</li>
     *   <li>Populate a list of {@link pojo.Seat} objects with the retrieved data.</li>
     * </ol>
     */
	public List<Seat> getBookedSeatsByMovieId(int movieId) throws SQLException {
		String query = "select * from booked_seats where movie_id = ?";
		List<Seat> bookedSeatsForAMovie = new ArrayList<Seat>();

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, movieId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String rowLetter = rs.getString("row_letter");
				int seatNumber = rs.getInt("seat_number");
				System.out.println(rowLetter+seatNumber);
				bookedSeatsForAMovie.add(new Seat(rowLetter, seatNumber));
			}
		}

		return bookedSeatsForAMovie;
	}

	/**
     * Books a seat for a user for a specific movie.
     * 
     * @param userId     the unique identifier of the user.
     * @param movieId    the unique identifier of the movie.
     * @param rowLetter  the row letter of the seat.
     * @param seatNumber the seat number.
     * @return the number of rows affected in the database.
     * @throws SQLException if a database access error occurs.
     * 
     * <h3>Workflow:</h3>
     * <ol>
     *   <li>Connect to the database using {@link DBConnection#getConnection()}.</li>
     *   <li>Execute an {@code INSERT} query to book the seat.</li>
     *   <li>Return the number of rows affected as an integer.</li>
     * </ol>
     */
	public int bookSeat(int userId, int movieId, String rowLetter, int seatNumber) throws SQLException {
		String query = "INSERT INTO booked_seats (user_id, movie_id, row_letter, seat_number)"
				+ "VALUES (?, ?, ?, ?)";

		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, userId);
			stmt.setInt(2, movieId);
			stmt.setString(3, rowLetter);
			stmt.setInt(4, seatNumber);
			return stmt.executeUpdate();
			
		}
	}

	/**
     * Retrieves all seats booked by a specific user.
     * 
     * @param userId the unique identifier of the user.
     * @return a {@code List<Seat>} containing seats booked by the user.
     * @throws SQLException if a database access error occurs.
     * 
     * <h3>Workflow:</h3>
     * <ol>
     *   <li>Connect to the database using {@link dao.DBConnection#getConnection()}.</li>
     *   <li>Execute the query to fetch seat details for the user.</li>
     *   <li>Populate a list of {@link pojo.Seat} objects with the retrieved data.</li>
     * </ol>
     */
	public List<Seat> getBookedSeatsByUserId(int userId) throws SQLException {
		List<Seat> bookedSeats = new ArrayList<>();
		String query = "SELECT row_letter, seat_number FROM booked_seats WHERE user_id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, userId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String rowLetter = rs.getString("row_letter");
				int seatNumber = rs.getInt("seat_number");
				bookedSeats.add(new Seat(rowLetter, seatNumber));
			}
		}
		return bookedSeats;
	}

	/**
     * Retrieves all movies booked by a specific user.
     * 
     * @param userId the unique identifier of the user.
     * @return a {@code List<Movie>} containing movies booked by the user.
     * @throws SQLException if a database access error occurs.
     * 
     * <h3>Workflow:</h3>
     * <ol>
     *   <li>Connect to the database using {@link dao.DBConnection#getConnection()}.</li>
     *   <li>Execute a query to fetch distinct movie details based on user bookings.</li>
     *   <li>Populate a list of {@link pojo.Movie} objects with the retrieved data.</li>
     * </ol>
     */
	public List<Movie> getBookedMoviesByUserId(int userId) throws SQLException {
		List<Movie> bookedMovies = new ArrayList<>();
		String query1 = "select distinct(booked_seats.movie_id),\r\n" + "movies.title,\r\n" + "movies.genre,\r\n"
				+ "movies.duration,\r\n" + "movies.rating,\r\n" + "movies.release_year,\r\n"+ "movies.desc\r\n" + "from booked_seats\r\n"
				+ "inner join movies on booked_seats.movie_id = movies.id where user_id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query1)) {

			stmt.setInt(1, userId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				int id = rs.getInt("movie_id");
				String genre = rs.getString("genre");
				String duration = rs.getString("duration");
				String rating = rs.getString("rating");
				String releaseYear = rs.getString("release_year");
				String desc = rs.getString("desc");
				bookedMovies.add(new Movie(id, title, genre, duration, rating, releaseYear, desc));
			}
		}
		return bookedMovies;
	}

	/**
     * Calculates the total cost for an array of seats.
     * 
     * @param seats an array of seat strings in the format "{@code RowLetterSeatNumber}".
     * @return the total cost of booking the specified seats.
     * 
     * <h3>Cost Calculation:</h3>
     * <ul>
     *   <li>Rows A-C: ₹120 per seat.</li>
     *   <li>Rows D-I: ₹200 per seat.</li>
     *   <li>Rows J-M: ₹250 per seat.</li>
     * </ul>
     */
	public static double calculateTotalCost(String[] seats){
		double totalCost = 0;

		for(String seat : seats) {
			char rowLetter = seat.charAt(0);
			
			if(rowLetter>= 'A' && rowLetter<= 'C') {
				totalCost += 120; 
				
			}
			if(rowLetter>= 'D' && rowLetter<= 'I') {
				totalCost += 200; 
				
			}
			if(rowLetter>= 'J' && rowLetter<= 'M') {
				totalCost += 250; 
				
			}
		}
		return totalCost;
	}
}