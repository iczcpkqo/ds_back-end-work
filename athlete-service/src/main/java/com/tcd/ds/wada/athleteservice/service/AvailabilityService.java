package com.tcd.ds.wada.athleteservice.service;

import com.tcd.ds.wada.athleteservice.entity.Availability;
import com.tcd.ds.wada.athleteservice.model.AvailabilityRequest;
import com.tcd.ds.wada.athleteservice.repository.AvailabilityRepository;
import com.tcd.ds.wada.athleteservice.service.mapper.AvailabilityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityService {
    private static final Logger logger = LoggerFactory.getLogger(AvailabilityService.class);

    @Autowired
    AvailabilityRepository repository;

    // Get existing future records of availability for athlete
    public ResponseEntity<List<Availability>> get(Integer athleteId) {
        logger.info("Getting Availability for Athlete Id (" + athleteId + ") ...");

        Optional<List<Availability>> athleteAvailabilityList = repository.findByAthleteId(athleteId);

        if (athleteAvailabilityList.isPresent()) {
            logger.info("Found");
            return ResponseEntity.ok(athleteAvailabilityList.get());
        } else {
            logger.info("Not Found");
            return ResponseEntity.notFound().build();
        }
    }

    // Add new record of availability for athlete
    public ResponseEntity<Integer> add(AvailabilityRequest request) {
        logger.info("Adding Availability for athlete ... ");

        // check if availability data already requests at the timestamp
        Availability availability = new AvailabilityMapper().fromAvailabilityRequestToEntity(request);
        availability = repository.save(availability);
        logger.info("Added with Id (" + availability.getAvailabilityId() + ")");

        return new ResponseEntity<>(availability.getAvailabilityId(), HttpStatus.OK);
    }

    // Update existing future record of availability for athlete
    public ResponseEntity<?> update(Integer availabilityId, AvailabilityRequest request) {
        logger.info("Updating Availability for athlete ... ");

        Optional<Availability> availability = repository.findById(availabilityId);
        if (availability.isPresent()) {
            // write update logic
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        } else
            return ResponseEntity.notFound().build();
    }

    // Delete existing future records of availability for athlete (if appointment is not set)
    public ResponseEntity<?> delete(Integer availabilityId) {
        logger.info("Delete Availability for athlete ... ");

        Optional<Availability> availability = repository.findById(availabilityId);
        if (availability.isPresent()) {
            repository.deleteById(availabilityId);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }
}
