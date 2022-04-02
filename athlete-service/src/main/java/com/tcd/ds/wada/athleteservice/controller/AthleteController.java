package com.tcd.ds.wada.athleteservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcd.ds.wada.athleteservice.models.AthleteRegistrationRequest;



public interface AthleteController {
	
	String BASE_URL = "/athlete";

	@PostMapping(path = BASE_URL + "/register")
	public ResponseEntity<Object> register(@RequestBody AthleteRegistrationRequest request);

}
