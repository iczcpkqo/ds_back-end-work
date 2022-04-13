package com.tcd.ds.wada.userservice.service.mapper;

import com.tcd.ds.wada.userservice.entity.User;
import com.tcd.ds.wada.userservice.model.UserRegistrationRequest;

public class UserMapper {
    public User fromRegistrationRequestToEntity(UserRegistrationRequest request) {
    	User user = new User();
    	user.setPassword(request.getPassword());
    	user.setUserEmail(request.getEmail());
    	user.setName(request.getName());
		user.setIsAthlete(request.getIsAthlete());
		user.setLocation(request.getLocation());
    	return user;
    }
}
