package com.tcd.ds.wada.userservice.service;

import com.tcd.ds.wada.userservice.entity.User;
import com.tcd.ds.wada.userservice.model.UserRegistrationRequest;

public class UserMapper {
    public User fromRegistrationRequestToEntity(UserRegistrationRequest request) {
    	User user = new User();
    	user.setPassword(request.getPassword());
    	user.setUserEmail(request.getEmail());
    	user.setUserName(request.getUsername());
		user.setIsAthlete(request.getIsAthlete());
    	return user;
    }
}
