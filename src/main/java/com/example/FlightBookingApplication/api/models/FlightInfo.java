package com.example.FlightBookingApplication.api.models;

import java.util.List;
import java.util.stream.Collectors;

public class FlightInfo implements Cloneable {
	
	private String flightNumber;
    private String airline;
    private String from;
    private String to;
    private int departDate;
    private int departTime;
    private double price;
    private String faretype;
    private List<Seat> seats;

    public FlightInfo() {
        // Default constructor
    }

    public FlightInfo(String flightNumber, String airline, String from, String to,
                      int departDate, int departTime, double price, String faretype, List<Seat> seats) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.from = from;
        this.to = to;
        this.departDate = departDate;
        this.departTime = departTime;
        this.price = price;
        this.faretype = faretype;
        this.seats = seats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getDepartDate() {
        return departDate;
    }

    public void setDepartDate(int departDate) {
        this.departDate = departDate;
    }

    public int getDepartTime() {
        return departTime;
    }

    public void setDepartTime(int departTime) {
        this.departTime = departTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFaretype() {
        return faretype;
    }

    public void setFaretype(String faretype) {
        this.faretype = faretype;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public FlightInfo clone() {
        try {
            FlightInfo cloned = (FlightInfo) super.clone();
            if (seats != null) {
                cloned.seats = seats.stream().map(Seat::clone).collect(Collectors.toList());
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
