package com.example.FlightBookingApplication.api.controller;

import org.springframework.web.bind.annotation.*;

import com.example.FlightBookingApplication.api.models.BookingRequest;
import com.example.FlightBookingApplication.api.models.Flight;
import com.example.FlightBookingApplication.api.models.FlightInfo;
import com.example.FlightBookingApplication.api.service.Booking;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @GetMapping("/search")
    public List<FlightInfo> searchFlights(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam int departDate,
            @RequestParam int paxCount) {
        return Flight.searchFlight(from, to, departDate, paxCount);
    }

    @PostMapping("/book")
    public String bookFlight(@RequestBody BookingRequest bookingRequest) {
        return Booking.bookFlight(
                bookingRequest.getUserId(),
                bookingRequest.getFlightNumber(),
                bookingRequest.getDepartDate(),
                bookingRequest.getFareType(), 
                bookingRequest.getSeats()
        );
    }
    
    @GetMapping("/searchByPreference")
    public List<FlightInfo> searchFlights(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam int departDate,
            @RequestParam int paxCount,
            @RequestParam(required = false) String preferredAirline,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortType
    ) {
    	return Flight.searchFlightByPreference(from, to, departDate, paxCount, preferredAirline, sortBy, sortType);
   }


}
