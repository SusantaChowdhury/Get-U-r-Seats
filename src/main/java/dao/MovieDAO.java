package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pojo.Movie;

/**
 * The {@code MovieDAO} class provides methods to interact with the {@code movies} table 
 * in the database. It includes functionality to retrieve all movies or a specific movie 
 * by its unique ID.
 * 
 * <h2>Dependencies</h2>
 * <ul>
 *   <li>{@link java.sql.Connection}</li>
 *   <li>{@link java.sql.PreparedStatement}</li>
 *   <li>{@link java.sql.ResultSet}</li>
 *   <li>{@link java.sql.SQLException}</li>
 *   <li>{@link java.sql.Statement}</li>
 *   <li>{@link pojo.Movie}</li>
 *   <li>{@link dao.DBConnection}</li>
 * </ul>
 * 
 * <h2>Workflow</h2>
 * <ol>
 *   <li>Establish a connection to the database using {@link dao.DBConnection#getConnection()}.</li>
 *   <li>Execute SQL queries to retrieve or filter movie data.</li>
 *   <li>Map the database results to {@link pojo.Movie} objects.</li>
 *   <li>Return the results as a {@code List} of {@link pojo.Movie} objects or a single {@link pojo.Movie} object.</li>
 * </ol>
 * 
 * <h2>Usage</h2>
 * <pre>
 * // Example: Fetch all movies
 * MovieDAO movieDAO = new MovieDAO();
 * List<Movie> movies = movieDAO.getAllMovies();
 * 
 * // Example: Fetch a specific movie by ID
 * Movie movie = MovieDAO.getMovieById(1);
 * </pre>
 * 
 * @see pojo.Movie
 * @see dao.DBConnection
 * 
 * @author Susanta Chowdhury, Rishabh Gon.
 * @version 2.0
 * @since 2024-11-22
 */
public class MovieDAO {
	
	/**
     * Retrieves all movies from the {@code movies} table.
     * 
     * <h3>SQL Query</h3>
     * <pre>
     * SELECT * FROM movies;
     * </pre>
     * 
     * @return a {@link java.util.List} of {@link pojo.Movie} objects containing details of all movies.
     * If no movies are found, an empty list is returned.
     * 
     * @throws SQLException if a database access error occurs.
     */
	public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
        		Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM movies")) {

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setGenre(rs.getString("genre"));
                movie.setDuration(rs.getString("duration"));
                movie.setRating(rs.getString("rating"));
                movie.setReleaseYear(rs.getString("release_year"));
                movie.setDesc(rs.getString("desc"));
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

	 /**
     * Retrieves a specific movie by its ID from the {@code movies} table.
     * 
     * <h3>SQL Query</h3>
     * <pre>
     * SELECT * FROM movies WHERE id = ?;
     * </pre>
     * 
     * @param id the unique identifier of the movie to retrieve.
     * 
     * @return a {@link pojo.Movie} object containing the details of the movie if found, or {@code null} if no movie matches the given ID.
     * 
     * @throws SQLException if a database access error occurs.
     */
    public static Movie getMovieById(int id) {
        Movie movie = null;
        String query = "SELECT * FROM movies WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setGenre(rs.getString("genre"));
                movie.setDuration(rs.getString("duration"));
                movie.setRating(rs.getString("rating"));
                movie.setReleaseYear(rs.getString("release_year"));
                movie.setDesc(rs.getString("desc"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }
}
