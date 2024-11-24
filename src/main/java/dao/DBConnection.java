package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The {@code DBConnection} class provides a utility method to establish a
 * connection to the database. This class is designed to manage database
 * connection parameters and handles the initialization of the JDBC driver.
 * 
 * <h2>Dependencies</h2>
 * <ul>
 * <li>{@link java.sql.Connection}</li>
 * <li>{@link java.sql.DriverManager}</li>
 * <li>{@link java.lang.Class}</li>
 * <li>MySQL JDBC Driver ({@code com.mysql.cj.jdbc.Driver})</li>
 * </ul>
 * 
 * <h2>Database Configuration</h2>
 * <ul>
 * <li>Database Name: {@code movie_hall_db}</li>
 * <li>Database URL: {@code jdbc:mysql://localhost:3306/movie_hall_db}</li>
 * <li>Username: {@code root}</li>
 * <li>Password: {@code 1234}</li>
 * </ul>
 * 
 * <h2>Workflow</h2>
 * <ol>
 * <li>Load the MySQL JDBC driver using {@code Class.forName()}.</li>
 * <li>Establish a connection using
 * {@link DriverManager#getConnection(String, String, String)}.</li>
 * <li>Return a {@link Connection} object for executing SQL queries.</li>
 * <li>Handle any exceptions that may occur during connection setup.</li>
 * </ol>
 * 
 * <h2>Usage</h2>
 * 
 * <pre>
 * // Example: Get a database connection
 * Connection connection = DBConnection.getConnection();
 * if (connection != null) {
 * 	System.out.println("Connection established.");
 * } else {
 * 	System.out.println("Failed to establish connection.");
 * }
 * </pre>
 * 
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * 
 * @author Susanta Chowdhury, Rishabh Gon.
 * @version 1.0
 * @since 2024-11-22
 */
public class DBConnection {

	/**
	 * The name of the database to which the connection is established.
	 */
	static final String dbName = "movie_hall_db";

	/**
	 * The URL of the database. It includes the protocol, host, port, and database
	 * name. Example: {@code jdbc:mysql://localhost:3306/movie_hall_db}
	 */
	static final String dbURL = "jdbc:mysql://localhost:3306/" + dbName; // "jdbc:oracle:thin:@localhost:1521:orcl";

	/**
	 * The username for the database connection.
	 */
	static final String dbUsername = "root";

	/**
	 * The password for the database connection.
	 */
	static final String dbPassword = "1234";

	/**
	 * Establishes and returns a connection to the database.
	 * 
	 * <h3>Workflow</h3>
	 * <ol>
	 * <li>Loads the MySQL JDBC driver using {@code Class.forName()}.</li>
	 * <li>Attempts to establish a connection using
	 * {@link DriverManager#getConnection(String, String, String)}.</li>
	 * <li>Returns a valid {@link Connection} object if successful.</li>
	 * <li>Logs a success message on successful connection.</li>
	 * <li>Handles exceptions and logs error messages if the connection fails.</li>
	 * </ol>
	 * 
	 * <h3>Dependencies</h3>
	 * <ul>
	 * <li>Requires the MySQL JDBC driver to be available in the classpath.</li>
	 * </ul>
	 * 
	 * <h3>Examples</h3>
	 * 
	 * <pre>
	 * Connection conn = DBConnection.getConnection();
	 * if (conn != null) {
	 * 	System.out.println("Database connection successful.");
	 * } else {
	 * 	System.out.println("Database connection failed.");
	 * }
	 * </pre>
	 * 
	 * @return a {@link Connection} object if the connection is successfully
	 *         established, or {@code null} if the connection fails.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 * @throws java.sql.SQLException  if a database access error occurs.
	 */
	public static Connection getConnection() {
		Connection conn = null;

		try {

			// Load the JDBC driver (MySQL in this case)
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establish the database connection
			conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			System.out.println("JDBC Connection established successfully.");

		} catch (Exception ex) {
			System.out.println("Connection failed...");
			ex.printStackTrace();
		}
		return conn;
	}
}
