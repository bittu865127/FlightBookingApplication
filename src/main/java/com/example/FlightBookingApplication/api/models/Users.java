package com.example.FlightBookingApplication.api.models;

public class Users{
    private String userId;
    private String name;
    private double funds;
 
    public Users(String userId, String name, double funds) {
        this.userId = userId;
        this.name = name;
        this.funds = funds;
    }
 
	public String getUserId() {
		return userId;
	}
 
	public void setUserId(String userId) {
		this.userId = userId;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public double getFunds() {
		return funds;
	}
 
	public void setFunds(double funds) {
		this.funds = funds;
	}
 
}