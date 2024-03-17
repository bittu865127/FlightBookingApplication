package com.example.FlightBookingApplication.api.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.FlightBookingApplication.api.models.FareType;
import com.example.FlightBookingApplication.api.models.Flight;
import com.example.FlightBookingApplication.api.models.FlightInfo;
import com.example.FlightBookingApplication.api.models.Seat;

public class FlightMapper {

    public static List<FlightInfo> mapFlightsToFlightInfo(List<Flight> flights) {
        List<FlightInfo> flightInfoList = new ArrayList<>();

        for (Flight flight : flights) {
            for (Map.Entry<FareType, List<Seat>> entry : flight.getSeatsByFareType().entrySet()) {
                FareType fareType = entry.getKey();
                List<Seat> seats = entry.getValue();

                FlightInfo flightInfo = new FlightInfo();
                flightInfo.setFlightNumber(flight.getFlightNumber());
                flightInfo.setAirline(flight.getAirline());
                flightInfo.setFrom(flight.getFrom());
                flightInfo.setTo(flight.getTo());
                flightInfo.setDepartDate(flight.getDepartDate());
                flightInfo.setDepartTime(flight.getDepartTime());
                flightInfo.setPrice(fareType.getPrice());
                flightInfo.setFaretype(fareType.getName());
                flightInfo.setSeats(seats);

                flightInfoList.add(flightInfo.clone());
            }
        }

        return flightInfoList;
    }
}