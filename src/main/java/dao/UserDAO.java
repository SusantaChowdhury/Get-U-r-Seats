package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pojo.User;

/**
 * The {@code UserDAO} class provides methods to interact with the database for user-related operations.
 * <p>
 * It supports functionalities such as registering a new user, retrieving user information by email, 
 * checking if a user already exists, and retrieving stored passwords. This class relies on a 
 * database connection provided by the {@code DBConnection} utility class.
 * </p>
 * 
 * <h2>Dependencies:</h2>
 * <ul>
 *   <li>{@link java.sql.Connection} - To establish a database connection.</li>
 *   <li>{@link java.sql.PreparedStatement} - To execute parameterized SQL queries.</li>
 *   <li>{@link java.sql.ResultSet} - To process the results of SQL queries.</li>
 *   <li>{@link DBConnection} - Provides the database connection utility.</li>
 *   <li>{@link pojo.User} - Represents the user entity for the application.</li>
 * </ul>
 * 
 * <h2>Workflow:</h2>
 * <p>
 * Each method in this class performs a specific task:
 * </p>
 * <ul>
 *   <li>{@link #registerUser(String, String, String)} - Registers a new user if they do not already exist.</li>
 *   <li>{@link #getUserByEmail(String)} - Retrieves user information based on their email address.</li>
 *   <li>{@link #userExits(String)} - Checks if a user exists in the database.</li>
 *   <li>{@link #getPasswordByEmail(String)} - Retrieves the hashed password for a user by email.</li>
 * </ul>
 * 
 * @author Susanta Chowdhury, Rishabh Gon.
 * @version 2.2
 * @since 2024-11-22
 */

public class UserDAO {


	/**
     * Registers a new user in the database.
     * <p>
     * This method checks if a user already exists using the email. If the user does not exist, 
     * it inserts the new user's details into the database.
     * </p>
     * 
     * @param username the username of the new user.
     * @param hashedPassword the hashed password of the new user.
     * @param email the email address of the new user.
     * @return a {@code String} indicating the result of the operation: 
     *         {@code "success"} for successful registration, {@code "failure"} if registration fails, 
     *         or {@code "user exists"} if the email is already in use.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
	public static String registerUser(String username, String hashedPassword, String email)
			throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		String result = "failure";

		boolean userExists = userExits(email);

		System.out.println("Entered registerUser() method...");

		if (userExists) {
			result = "user exists";
		} else {
			conn = DBConnection.getConnection();
			try {
				String insertQuery = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
				ps = conn.prepareStatement(insertQuery);
				ps.setString(1, username);
				ps.setString(2, email);
				ps.setString(3, hashedPassword);

				if (ps.executeUpdate() > 0) {
					result = "success";
				} else {
					result = "failure";
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return result;
	}


	/**
     * Retrieves user details from the database using the email address.
     * <p>
     * This method queries the database for a user with the specified email and maps the results 
     * to a {@link pojo.User} object.
     * </p>
     * 
     * @param email the email address of the user.
     * @return a {@link pojo.User} object containing the user's details, or an empty user object if no match is found.
     */
	public static User getUserByEmail(String email) {
		User user = new User();
		String query = "SELECT * FROM users WHERE email =?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
     * Checks if a user exists in the database based on their email address.
     * <p>
     * This method performs a query to determine whether a record with the specified email exists.
     * </p>
     * 
     * @param email the email address to check.
     * @return {@code true} if the user exists, {@code false} otherwise.
     */
	public static boolean userExits(String email) {

		String query = "select * from users where email = ?";
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	/**
     * Retrieves the stored hashed password for a user based on their email address.
     * <p>
     * This method is typically used during the login process to validate user credentials.
     * </p>
     * 
     * @param email the email address of the user.
     * @return a {@code String} containing the hashed password, or {@code null} if no match is found.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
	public static String getPasswordByEmail(String email) throws ClassNotFoundException {
		String storedPassword = null;

		try (Connection conn = DBConnection.getConnection()) {

			String query = "SELECT password FROM users WHERE email = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				storedPassword = rs.getString("password");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return storedPassword;
	}
}
