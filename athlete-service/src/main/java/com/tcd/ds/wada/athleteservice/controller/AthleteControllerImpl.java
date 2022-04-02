package com.tcd.ds.wada.athleteservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;
import com.tcd.ds.wada.athleteservice.entity.Athlete;
import com.tcd.ds.wada.athleteservice.models.AthleteRegistrationRequest;
import com.tcd.ds.wada.athleteservice.repository.AthleteRepository;

@RestController
public class AthleteControllerImpl implements AthleteController {
	
	@Autowired
	AthleteRepository repository;

	@Override
	public ResponseEntity<Object> register(AthleteRegistrationRequest request) {
		Faker faker = new Faker();
        List<Athlete> athletes = new ArrayList<>();
//        for (int i = 0; i < 5000; i++) {
//            String name = faker.esports().player();
//            String region = faker.country().name();
//            Athlete athlete = new Athlete();
//    		athlete.setName(name);
//    		athlete.setRegion(region);
//    		athletes.add(athlete);
//        }
		Athlete athlete = new Athlete();
		athlete.setName(request.getName());
		athlete.setRegion(request.getRegion());
		athlete.setAthleteid(getRandomNumberUsingNextInt());
		repository.save(athlete);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	public static int getRandomNumberUsingNextInt() {
	    Random random = new Random();
	    int min = 100;
	    int max = 999;
	    return random.nextInt(max - min) + min;
	}

}
