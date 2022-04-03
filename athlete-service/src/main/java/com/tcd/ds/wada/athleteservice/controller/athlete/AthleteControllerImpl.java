package com.tcd.ds.wada.athleteservice.controller.athlete;

import com.tcd.ds.wada.athleteservice.entity.Athlete;
import com.tcd.ds.wada.athleteservice.model.AthleteRequest;
import com.tcd.ds.wada.athleteservice.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AthleteControllerImpl implements AthleteController {

    @Autowired
    AthleteService service;

    @Override
    public ResponseEntity<Integer> add(AthleteRequest request) {
        return service.add(request);
    }

    @Override
    public ResponseEntity<Athlete> get(Integer athleteId) {
        return service.get(athleteId);
    }
}