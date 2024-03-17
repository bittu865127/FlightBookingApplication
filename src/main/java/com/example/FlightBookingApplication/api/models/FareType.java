package com.example.FlightBookingApplication.api.models;

public class FareType {
	
    private String name;
    private double price;
    
    public FareType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FareType(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

