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

import java.util.List;
import java.util.Optional;

public class AvailabilityService {
    private static final Logger logger = LoggerFactory.getLogger(AvailabilityService.class);

    @Autowired
    AvailabilityRepository repository;

    // Get existing future records of availability for athlete
    public ResponseEntity<List<Availability>> get(String athleteId) {
        logger.info("Getting Availability for athlete ... ");

        Optional<List<Availability>> athleteAvailabilityList = repository.findByAthleteId(athleteId);

        return athleteAvailabilityList
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add new record of availability for athlete
    public ResponseEntity<String> add(AvailabilityRequest request) {
        logger.info("Adding Availability for athlete ... ");

        // check if availability data already requests at the timestamp
        Availability availability = new AvailabilityMapper().fromAvailabilityRequestToEntity(request);
        availability = repository.save(availability);

        return new ResponseEntity<>(availability.getAvailabilityId(), HttpStatus.OK);
    }

    // Update existing future record of availability for athlete
    public ResponseEntity<?> update(String availabilityId, AvailabilityRequest request) {
        logger.info("Updating Availability for athlete ... ");

        Optional<Availability> availability = repository.findById(availabilityId);
        if (availability.isPresent()) {
            // write update logic
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        } else
            return ResponseEntity.notFound().build();
    }

    // Delete existing future records of availability for athlete (if appointment is not set)
    public ResponseEntity<?> delete(String availabilityId) {
        logger.info("Delete Availability for athlete ... ");

        Optional<Availability> availability = repository.findById(availabilityId);
        if (availability.isPresent()) {
            if (availability.get().getIsAppointment()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else {
                repository.deleteById(availabilityId);
                return ResponseEntity.ok().build();
            }
        } else
            return ResponseEntity.notFound().build();
    }
}
