package com.example.FlightBookingApplication.api.models;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.FlightBookingApplication.api.mapper.FlightMapper;
import com.example.FlightBookingApplication.api.repository.FlightRepository;

public class Flight {
	private String flightNumber;
	private String airline;
	private String from;
	private String to;
	private int departDate;
	private int departTime;
	private int arrivalTime;
	private Map<FareType, List<Seat>> seatsByFareType;

	public Flight(String flightNumber, String airline, String from, String to, int departDate, int departTime,
			int arrivalTime, Map<FareType, List<Seat>> seatsByFareType) {
		this.flightNumber = flightNumber;
		this.airline = airline;
		this.from = from;
		this.to = to;
		this.departDate = departDate;
		this.departTime = departTime;
		this.arrivalTime = arrivalTime;
		this.seatsByFareType = seatsByFareType;
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

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Map<FareType, List<Seat>> getSeatsByFareType() {
		return seatsByFareType;
	}

	public void setSeatsByFareType(Map<FareType, List<Seat>> seatsByFareType) {
		this.seatsByFareType = seatsByFareType;
	}

	public static List<FlightInfo> searchFlight(String from, String to, int departDate, int paxCount) {
		List<Flight> filteredFlights = FlightRepository.getInstance().getAllFlights().stream()
				.filter(flight -> flight.getFrom().equalsIgnoreCase(from))
				.filter(flight -> flight.getTo().equalsIgnoreCase(to))
				.filter(flight -> flight.getDepartDate() == departDate)
				.filter(flight -> isSeatsAvailable(flight, paxCount)).collect(Collectors.toList());

		return FlightMapper.mapFlightsToFlightInfo(filteredFlights);
	}

	public static List<FlightInfo> searchFlightByPreference(String from, String to, int departDate, int paxCount,
			String preferredAirline, String sortBy, final String sortType) {

		Comparator<FlightInfo> comparator;
		switch (sortBy.toLowerCase()) {
		case "departtime":
			comparator = Comparator.comparing(FlightInfo::getDepartTime);
			break;
		case "price":
			comparator = Comparator.comparing(FlightInfo::getPrice);
			break;
		default:
			comparator = Comparator.comparing(FlightInfo::getFlightNumber);
			break;
		}

		if ("desc".equalsIgnoreCase(sortType)) {
			comparator = comparator.reversed();
		}

		List<FlightInfo> filteredFlightInfoList = FlightRepository.getInstance().getAllFlights().stream()
				.filter(flight -> flight.getFrom().equalsIgnoreCase(from))
				.filter(flight -> flight.getTo().equalsIgnoreCase(to))
				.filter(flight -> flight.getDepartDate() == departDate)
				.filter(flight -> isSeatsAvailable(flight, paxCount))
				.filter(flight -> preferredAirline == null || flight.getAirline().equalsIgnoreCase(preferredAirline))
				.map(flight -> FlightMapper.mapFlightsToFlightInfo(Collections.singletonList(flight)))
				.flatMap(List::stream).sorted(comparator).collect(Collectors.toList());

		return filteredFlightInfoList;
	}

	private static boolean isSeatsAvailable(Flight flight, int paxCount) {
		for (List<Seat> seats : flight.getSeatsByFareType().values()) {
			long availableSeats = seats.stream().filter(seat -> !seat.isBooked()).count();

			if (availableSeats >= paxCount) {
				return true;
			}
		}

		return false;
	}

	public List<Seat> getAvailableSeats(FareType fareType) {
		if (seatsByFareType.containsKey(fareType)) {
			List<Seat> bookedSeats = seatsByFareType.get(fareType).stream().filter(Seat::isBooked)
					.collect(Collectors.toList());
			return seatsByFareType.get(fareType).stream().filter(seat -> !bookedSeats.contains(seat))
					.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	public Comparable<?> getSortingValue(String sortBy) {
		switch (sortBy.toLowerCase()) {
		case "departTime":
			return getDepartTime();
		default:
			return getFlightNumber();
		}
	}

}
