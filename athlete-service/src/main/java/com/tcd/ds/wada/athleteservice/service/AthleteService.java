package com.tcd.ds.wada.athleteservice.service;

import com.tcd.ds.wada.athleteservice.entity.Athlete;
import com.tcd.ds.wada.athleteservice.model.AthleteRequest;
import com.tcd.ds.wada.athleteservice.repository.AthleteRepository;
import com.tcd.ds.wada.athleteservice.service.mapper.AthleteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class AthleteService {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    @Autowired
    AthleteRepository repository;

    public ResponseEntity<String> add(AthleteRequest request) {
        logger.info("Adding Athlete ... ");

        Athlete athlete = new AthleteMapper().fromAthleteRequestToEntity(request);
        athlete = repository.save(athlete);

        return new ResponseEntity<>(athlete.getAthleteId(), HttpStatus.OK);
    }

    public ResponseEntity<Athlete> get(String athleteId) {
        logger.info("Getting Athlete ... ");

        Optional<Athlete> athlete = repository.findById(athleteId);

        return athlete
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
