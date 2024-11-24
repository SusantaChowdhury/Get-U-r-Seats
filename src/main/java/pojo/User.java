package pojo;

/**
 * This is the Plain Old Java Object(POJO) class representing a User with the attributes as: user id, user name, email address and password.
 * 
 * @author Susanta Chowdhury, Rishabh Gon.
 * @version 1.0
 */
public class User {

	private int id;
	private String username;
	private String email;
	private String password;
	
	public User() {
		
	}
	
	/**
	 * This method fetches the user id.
	 * @return the user id as an integer.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * This method initializes the user id with the parameter value.
	 * @param id an integer.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * This method fetches the user name.
	 * @return the user name as a String.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * This method initializes the user name with the parameter value.
	 * @param username a String.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * This method fetches the user email address.
	 * @return the email address as a String.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * This method initializes the user email address with the parameter value.
	 * @param email a String.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * This method fetches the user password.
	 * @return the password as a String.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * This method initializes the user password with the parameter value.
	 * @param password a String.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
