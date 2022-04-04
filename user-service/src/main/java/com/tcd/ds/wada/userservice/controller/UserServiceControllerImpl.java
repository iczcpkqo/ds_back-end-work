package com.tcd.ds.wada.userservice.controller;

import com.tcd.ds.wada.userservice.entity.Location;
import com.tcd.ds.wada.userservice.model.LoginResponse;
import com.tcd.ds.wada.userservice.model.UserLoginRequest;
import com.tcd.ds.wada.userservice.model.UserRegistrationRequest;
import com.tcd.ds.wada.userservice.repository.LocationRepository;
import com.tcd.ds.wada.userservice.service.UserLoginService;
import com.tcd.ds.wada.userservice.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserServiceControllerImpl implements UserServiceController {

	@Autowired
	UserLoginService userLoginService;

	@Autowired
	UserRegistrationService userRegistrationService;

	@Autowired
	LocationRepository locationRepository;

	@Override
	public ResponseEntity<LoginResponse> login(UserLoginRequest request) {
		return userLoginService.login(request);
	}

	@Override
	public List<Location> getLocations() {
		return (List<Location>) locationRepository.findAll();
	}

	@Override
	public ResponseEntity<Object> register(UserRegistrationRequest request) {
		return userRegistrationService.register(request);
	}


}
