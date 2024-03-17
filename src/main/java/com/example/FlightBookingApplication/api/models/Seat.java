package com.example.FlightBookingApplication.api.models;

public class Seat implements Cloneable{
    private String seatNumber;
    private boolean isBooked;
    
    public Seat() {
		super();
	}

	public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
        this.isBooked = false;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookSeat() {
        if (isBooked) {
            throw new IllegalStateException("Seat " + seatNumber + " is already booked.");
        }
        isBooked = true;
    }
    
    @Override
    public Seat clone() {
        try {
            return (Seat) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
