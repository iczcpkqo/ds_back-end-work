package com.tcd.ds.wada.userservice.controller;

import com.tcd.ds.wada.userservice.model.UserLoginRequest;
import com.tcd.ds.wada.userservice.model.UserRegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public interface UserServiceController {

	String BASE_URL = "/user";

	@PostMapping(path = BASE_URL + "/register")
	public ResponseEntity<Object> register(@RequestBody UserRegistrationRequest request);

	@PostMapping(path = BASE_URL + "/login")
	public @ResponseBody ResponseEntity<Object> login(@RequestBody UserLoginRequest request);

}
