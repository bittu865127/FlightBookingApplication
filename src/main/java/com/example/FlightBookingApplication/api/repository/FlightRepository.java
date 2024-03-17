package com.example.FlightBookingApplication.api.repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.FlightBookingApplication.api.models.FareType;
import com.example.FlightBookingApplication.api.models.Flight;
import com.example.FlightBookingApplication.api.models.Seat;


public class FlightRepository {


   private static final FlightRepository instance = new FlightRepository();
    private final List<Flight> flightList;


   private FlightRepository() {
        flightList = new ArrayList<>();


        Map<FareType, List<Seat>> seatsByFareType1 = new HashMap<>();
		seatsByFareType1.put(new FareType("F1", 2000), createSeats("1b", "2c", "4e"));
		flightList.add(new Flight("123", "6E", "DEL", "BLR", 2, 10, 11,seatsByFareType1));
		
		Map<FareType, List<Seat>> seatsByFareType2 = new HashMap<>();
		seatsByFareType2.put(new FareType("F2", 4000), createSeats("4e"));
		flightList.add(new Flight("156", "6E", "DEL", "BLR", 2, 14, 15,seatsByFareType2));
 
		Map<FareType, List<Seat>> seatsByFareType3 = new HashMap<>();
		seatsByFareType3.put(new FareType("F3", 1000), createSeats("29a","40e","1e","4e"));
		flightList.add(new Flight("234", "6E", "DEL", "HYD", 2, 15, 16, seatsByFareType3));
		
		Map<FareType, List<Seat>> seatsByFareType4 = new HashMap<>();
		seatsByFareType4.put(new FareType("F5", 10000), createSeats("7c","7d","12c","5f","8e","7e","4d","3e","4a","10a"));
		flightList.add(new Flight("456", "6E", "AMD", "CCU", 2, 18, 22, seatsByFareType4));
		
		Map<FareType, List<Seat>> seatsByFareType5 = new HashMap<>();
		seatsByFareType5.put(new FareType("F11", 2500), createSeats("12c","5f","4d","3e","4a","10a"));
		flightList.add(new Flight("987", "SJ", "DEL", "BLR", 2, 11, 16, seatsByFareType5));
		
		Map<FareType, List<Seat>> seatsByFareType6 = new HashMap<>();
		seatsByFareType6.put(new FareType("F1", 2300), createSeats("8e","7e","4d","6a","15a"));
		flightList.add(new Flight("1001", "SJ", "DEL", "HYD", 2, 9, 12, seatsByFareType6));
		
		Map<FareType, List<Seat>> seatsByFareType7 = new HashMap<>();
		seatsByFareType7.put(new FareType("F2", 2100), createSeats("45e","30a","1e","4e","7c","7d","12c"));
		flightList.add(new Flight("890", "SJ", "DEL", "AMD", 2, 12, 18, seatsByFareType7));
		
		Map<FareType, List<Seat>> seatsByFareType8 = new HashMap<>();
		seatsByFareType8.put(new FareType("F1d", 2900), createSeats("1e","4e","7c","7d","12c","5f","8e","7e","4d","3e","4a","10a"));
		flightList.add(new Flight("999", "SJ", "DEL", "HYD", 2, 8, 12, seatsByFareType8));
		
		Map<FareType, List<Seat>> seatsByFareType9 = new HashMap<>();
		seatsByFareType9.put(new FareType("F1x", 2000), createSeats("5a","10b","34e","20c","20a","8b","8a","9b"));
		flightList.add(new Flight("432", "UK", "DEL", "BLR", 2, 16, 18, seatsByFareType9));
		
		Map<FareType, List<Seat>> seatsByFareType10 = new HashMap<>();
		seatsByFareType10.put(new FareType("F1d", 4300), createSeats("12a","13b"));
		flightList.add(new Flight("444", "UK", "BLR", "DEL", 2, 11, 13, seatsByFareType10));
		
		Map<FareType, List<Seat>> seatsByFareType11 = new HashMap<>();
		seatsByFareType11.put(new FareType("F1", 2800), createSeats("5a","10b","34e","20c","3e","4a","10a"));
		flightList.add(new Flight("456", "UK", "DEL", "BLR", 2, 19, 21, seatsByFareType11));
		
		Map<FareType, List<Seat>> seatsByFareType12 = new HashMap<>();
		seatsByFareType12.put(new FareType("F1", 3600), createSeats("41a","44b","44c","12d"));
		flightList.add(new Flight("654", "AI", "DEL", "BLR", 2, 21, 23, seatsByFareType12));
		
		Map<FareType, List<Seat>> seatsByFareType13 = new HashMap<>();
		seatsByFareType13.put(new FareType("F1", 6700), createSeats("20c","20a","8b","8a","9b","10c","21a","18b","18a","19b"));
		flightList.add(new Flight("236", "AI", "DEL", "BLR", 2, 17, 19, seatsByFareType13));
		
		Map<FareType, List<Seat>> seatsByFareType14 = new HashMap<>();
		seatsByFareType14.put(new FareType("F1", 1500), createSeats("1a","1b","1c","1d"));
		flightList.add(new Flight("980", "QP", "DEL", "BLR", 2, 13, 14, seatsByFareType14));
		
		Map<FareType, List<Seat>> seatsByFareType15 = new HashMap<>();
		seatsByFareType15.put(new FareType("F11", 2100), createSeats("3b","12c"));
		flightList.add(new Flight("123", "6E", "DEL", "BLR", 2, 10, 11, seatsByFareType15));
    }


   public static FlightRepository getInstance() {
        return instance;
    }


   public Flight getFlightByFlightNumber(final String flightNumber) {
        return flightList.stream()
                .filter(flight -> flight.getFlightNumber().equals(flightNumber))
                .findFirst()
                .orElse(null);
    }


   public void addFlight(Flight flight) {
        flightList.add(flight);
    }


   public List<Flight> getAllFlights() {
        return new ArrayList<>(flightList);
    }


   private List<Seat> createSeats(String... seatNumbers) {
        List<Seat> seats = new ArrayList<>();
        for (String seatNumber : seatNumbers) {
            seats.add(new Seat(seatNumber));
        }
        return seats;
    }
}