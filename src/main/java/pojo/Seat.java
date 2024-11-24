package pojo;

public class Seat {
	 private String rowLetter;
	 private int seatNumber;
//	 private Date booking_date;
//	 private Time booking_time;
	    
	    public Seat(String rowLetter, int seatNumber) {
	        this.rowLetter = rowLetter;
	        this.seatNumber = seatNumber;
	        
	    }

		public String getRowLetter() {
			return rowLetter;
		}

		public void setRowLetter(String rowLetter) {
			this.rowLetter = rowLetter;
		}

		public int getSeatNumber() {
			return seatNumber;
		}

		public void setSeatNumber(int seatNumber) {
			this.seatNumber = seatNumber;
		}

//		public Date getBooking_date() {
//			return booking_date;
//		}
//
//		public void setBooking_date(Date booking_date) {
//			this.booking_date = booking_date;
//		}
//
//		public Time getBooking_time() {
//			return booking_time;
//		}
//
//		public void setBooking_time(Time booking_time) {
//			this.booking_time = booking_time;
//		}

}
