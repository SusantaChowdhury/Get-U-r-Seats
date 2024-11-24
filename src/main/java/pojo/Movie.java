package pojo;

/**
 * This is the Plain Old Java Object(POJO) class representing a {@link pojo.Movie} object with the attributes as: movie id, title, genre, duration, rating and release year.
 * 
 * @author Susanta Chowdhury, Rishabh Gon.
 * @version 1.0
 */
public class Movie {
	private int id;
    private String title;
    private String genre;
    private String duration;
    private String rating;
    private String releaseYear;
    private String desc;
    
    /**
     * The Parameterized Constructor of the class used to initialize a {@link pojo.Movie} object with the parameters.
     * 
     * @param id the movie id as an integer value.
     * @param title the movie title as a String.
     * @param genre the movie genre as a String.
     * @param duration the movie duration as a String.
     * @param rating the movie rating as a String.
     * @param releaseYear the release year of the movie as a String.
     */
    public Movie(int id, String title, String genre, String duration, String rating, String releaseYear, String desc) {
    	this.duration = duration;
    	this.genre = genre;
    	this.id = id;
    	this.rating = rating;
    	this.releaseYear = releaseYear;
    	this.title = title;
    	this.desc = desc;
    }
    
    /**
     * Default Constructor.
     */
	public Movie() {
	}

	/**
	 * This method fetches the movie id.
	 * @return the movie id as an integer.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * This method initializes the movie id with the parameter value.
	 * @param id an integer.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * This method fetches the movie title.
	 * @return the movie title as a String.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * This method initializes the movie title with the parameter value.
	 * @param title a String.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * This method fetches the movie genre.
	 * @return the movie genre as a String.
	 */
	public String getGenre() {
		return genre;
	}
	
	/**
	 * This method initializes the movie genre with the parameter value.
	 * @param genre a String.
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	/**
	 * This method fetches the movie duration.
	 * @return the movie duration as a String.
	 */
	public String getDuration() {
		return duration;
	}
	
	/**
	 * This method initializes the movie duration with the parameter value.
	 * @param duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	/**
	 * This method fetches the movie rating.
	 * @return the movie rating as a String.
	 */
	public String getRating() {
		return rating;
	}
	
	/**
	 * This method initializes the movie rating with the parameter value.
	 * @param rating a St.ring
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	/**
	 * This method fetches the release year of the movie.
	 * @return the release year of the movie.
	 */
	public String getReleaseYear() {
		return releaseYear;
	}
	
	/**
	 * This method initializes the release year of the movie with the parameter value.
	 * @param releaseYear a String.
	 */
	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}
	   
	/**
	 * This method fetches the movie description.
	 * @return the movie description as a String.
	 */
	public String getDesc() {
		return desc;
	}
	
	/**
	 * This method initializes the movie description with the parameter value.
	 * @param desc a String.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
