package com.example.FlightBookingApplication.api.service;

import org.springframework.stereotype.Service;

import com.example.FlightBookingApplication.api.models.Users;
import com.example.FlightBookingApplication.api.repository.UserRepository;

@Service
public class UserService {
	

	public void addUser(String userId, String name, double funds) {
		UserRepository.getInstance().addUser(new Users(userId, name, funds));
	}

}