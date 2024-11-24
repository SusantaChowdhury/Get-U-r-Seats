package pojo;

import java.util.List;

public class Ticket {

	private Movie movie;
	private List<Seat> seats;
	
	public Ticket() {
		
	}
	
	public Ticket(Movie movie, List<Seat> seats) {
		super();
		this.movie = movie;
		this.seats = seats;
	}

	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public List<Seat> getSeats() {
		return seats;
	}
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	
	
}
