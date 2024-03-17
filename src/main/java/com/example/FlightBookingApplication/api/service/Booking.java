package com.example.FlightBookingApplication.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.example.FlightBookingApplication.api.models.FareType;
import com.example.FlightBookingApplication.api.models.Flight;
import com.example.FlightBookingApplication.api.models.Seat;
import com.example.FlightBookingApplication.api.models.Users;
import com.example.FlightBookingApplication.api.repository.FlightRepository;
import com.example.FlightBookingApplication.api.repository.UserRepository;

public class Booking {
    private String bookingId;
    private String userId;
    private String flightNumber;
    private int departDate;
    private FareType fareType;
    private List<Seat> seats;
    
    private static List<Flight> flights = FlightRepository.getInstance().getAllFlights();

    public Booking(String userId, String flightNumber, int departDate, FareType fareType, List<Seat> seats) {
        this.bookingId = UUID.randomUUID().toString();
        this.userId = userId;
        this.flightNumber = flightNumber;
        this.departDate = departDate;
        this.fareType = fareType;
        this.seats = seats;
    }

    public String getBookingId() {
        return bookingId;
    }

    public static String bookFlight(String userId, String flightNumber, int departDate, FareType fareType, List<Seat> seats) {
        Users user = UserRepository.getInstance().getUserById(userId);
        Flight flight = findFlight(flightNumber, departDate);
        
        if (flight == null) {
            throw new IllegalArgumentException("Invalid flight details.");
        }

        if (user == null) {
            throw new IllegalArgumentException("Invalid user details.");
        }

        double totalCost = calculateTotalCost(seats, fareType);

        if (user.getFunds() < totalCost) {
            throw new IllegalStateException("Insufficient funds to book the flight.");
        }

        // Validate seat availability
        for (Seat seat : seats) {
            if (seat.isBooked()) {
                throw new IllegalStateException("Seat " + seat.getSeatNumber() + " is already booked.");
            }
        }

        // Deduct funds from user's account
        user.setFunds(user.getFunds() - totalCost);

        // Book the seats
        for (Seat seat : seats) {
            seat.bookSeat();
        }

        // Create and return booking ID
        return new Booking(userId, flightNumber, departDate, fareType, seats).getBookingId();
    }

    private static double calculateTotalCost(List<Seat> seats, FareType fareType) {
        double totalCost = 0.0;

        for (Seat seat : seats) {
            totalCost += fareType.getPrice();
        }

        return totalCost;
    }

    private static Flight findFlight(String flightNumber, int departDate) {
        List<Flight> flights = FlightRepository.getInstance().getAllFlights();
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber) && flight.getDepartDate() == departDate) {
                return flight;
            }
        }
        return null;
    }

}
