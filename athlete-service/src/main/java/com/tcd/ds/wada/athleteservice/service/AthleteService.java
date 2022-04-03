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
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AthleteService {
    private static final Logger logger = LoggerFactory.getLogger(AthleteService.class);

    @Autowired
    AthleteRepository repository;

    public ResponseEntity<String> add(AthleteRequest request) {
        logger.info("Adding Athlete ... ");

        Athlete athlete = new AthleteMapper().fromAthleteRequestToEntity(request);
        athlete = repository.save(athlete);
        logger.info("Added with Id (" + athlete.getAthleteId() + ")");

        return new ResponseEntity<>(athlete.getAthleteId(), HttpStatus.OK);
    }

    public ResponseEntity<Athlete> get(String athleteId) {
        logger.info("Athlete: Getting Athlete");
        Athlete athlete = getAthleteFromDb(athleteId);
        if (athlete != null) {
            return ResponseEntity.ok(athlete);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Athlete getAthleteFromDb(String athleteId) {
        logger.info("Athlete: Getting Athlete Id (" + athleteId + ") from DB");

        Optional<Athlete> athlete = repository.findById(athleteId);

        if (athlete.isPresent()) {
            logger.info("Athlete: Found");
            return athlete.get();
        } else {
            logger.info("Athlete: Not found");
            return null;
        }
    }
}
