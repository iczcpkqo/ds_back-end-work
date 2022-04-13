package com.tcd.ds.wada.userservice.controller;

import com.tcd.ds.wada.userservice.entity.Location;
import com.tcd.ds.wada.userservice.model.LoginResponse;
import com.tcd.ds.wada.userservice.model.UserLoginRequest;
import com.tcd.ds.wada.userservice.model.UserRegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface UserServiceController {

	String USER_BASE_URL = "/user";

	@PostMapping(path = USER_BASE_URL + "/register")
	public ResponseEntity<Object> register(@RequestBody UserRegistrationRequest request);

	@PostMapping(path = USER_BASE_URL + "/login")
	public @ResponseBody
	ResponseEntity<LoginResponse> login(@RequestBody UserLoginRequest request);

	@GetMapping(path = "/locations")
	public @ResponseBody List<Location> getLocations();

}
