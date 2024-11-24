package control;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.MovieDAO;
import pojo.Movie;

/**
 * The {@code MovieServlet} class handles HTTP requests related to movie data.
 * <p>
 * This servlet interacts with the {@link dao.MovieDAO} to retrieve a list of movies from the database 
 * and forwards the data to a JSP page for display.
 * </p>
 * 
 * <h2>Workflow:</h2>
 * <ol>
 *   <li>Initializes the {@link dao.MovieDAO} to interact with the database.</li>
 *   <li>Handles HTTP GET requests by calling {@link dao.MovieDAO#getAllMovies()} to retrieve a list of movies.</li>
 *   <li>Sets the movie list as a request attribute.</li>
 *   <li>Forwards the request to the {@code movieList.jsp} page for rendering the movie list.</li>
 * </ol>
 * 
 * <h2>Dependencies:</h2>
 * <ul>
 *   <li>{@link dao.MovieDAO} - Data Access Object to fetch movie data from the database.</li>
 *   <li>{@link pojo.Movie} - The Movie POJO representing the movie data.</li>
 * </ul>
 * 
 * <h2>Servlet Mapping:</h2>
 * <p>
 * The servlet is mapped to the URL pattern <strong>/MovieServlet</strong>.
 * </p>
 * 
 * @author Susanta Chowdhury, Rishabh Gon.
 * @version 2.1
 * @since 2024-11-22
 */
@WebServlet("/MovieServlet")
public class MovieServlet extends HttpServlet {
	
	/** Serial version UID for ensuring class compatibility during serialization. */
	private static final long serialVersionUID = 1L;

	/** Data Access Object for interacting with the movie database. */
	private MovieDAO movieDAO;
	
	 /**
     * Default constructor for the servlet.
     * Initializes the {@link dao.MovieDAO} to enable interactions with the movie database.
     */
	public MovieServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	 /**
     * Initializes the servlet and creates a {@link dao.MovieDAO} instance.
     * This method is called once when the servlet is loaded into memory by the servlet container.
     */
	@Override
	public void init() {
		movieDAO = new MovieDAO();
	}

	/**
     * Handles HTTP GET requests to retrieve a list of movies.
     * <p>
     * This method fetches all movies from the database using {@link dao.MovieDAO#getAllMovies()} and
     * forwards the movie list to the {@code movieList.jsp} page.
     * </p>
     * 
     * <h3>Process:</h3>
     * <ol>
     *   <li>Calls {@link dao.MovieDAO#getAllMovies()} to fetch the list of movies from the database.</li>
     *   <li>Sets the movie list as a request attribute.</li>
     *   <li>Forwards the request to {@code movieList.jsp} for rendering the movie list.</li>
     * </ol>
     * 
     * @param request  the {@link HttpServletRequest} object containing the request from the client.
     * @param response the {@link HttpServletResponse} object containing the response to the client.
     * @throws ServletException if the request processing encounters a servlet-related error.
     * @throws IOException      if an I/O error occurs during request handling.
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Movie> movies = movieDAO.getAllMovies();
		request.setAttribute("movies", movies);
		RequestDispatcher dispatcher = request.getRequestDispatcher("movieList.jsp");
		dispatcher.forward(request, response);
	}

}
