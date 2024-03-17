package com.example.FlightBookingApplication.api.repository;

import java.util.HashMap;
import java.util.Map;

import com.example.FlightBookingApplication.api.models.Users;

public class UserRepository {
    private static final UserRepository instance = new UserRepository();
    private final Map<String, Users> userMap;

    private UserRepository() {
        userMap = new HashMap();
    }

    public static UserRepository getInstance() {
        return instance;
    }

    public Users getUserById(String userId) {
        return userMap.get(userId);
    }

    public void addUser(Users user) {
        userMap.put(user.getUserId(), user);
    }

    // Other methods as needed...

    public Map<String, Users> getAllUsers() {
        return new HashMap(userMap);
    }
}
