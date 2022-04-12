package com.tcd.ds.wada.athleteservice.service;

import com.tcd.ds.wada.athleteservice.entity.Athlete;
import com.tcd.ds.wada.athleteservice.entity.Availability;
import com.tcd.ds.wada.athleteservice.model.AvailabilityRequest;
import com.tcd.ds.wada.athleteservice.repository.AthleteRepository;
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
    AthleteRepository athleteRepository;

    @Autowired
    AthleteService athleteService;

    @Autowired
    AvailabilityRepository availabilityRepository;

    // Add new record of availability for athlete
    public ResponseEntity<Object> add(String athleteId, AvailabilityRequest request) {
        logger.info("Availability: Adding for Athlete ... ");

        Athlete athlete = athleteService.getAthleteFromDb(athleteId);

        if (athlete != null) {
            Availability newAvailability = new AvailabilityMapper().fromAvailabilityRequestToNewEntity(request);
            newAvailability.setAthlete(athlete);

            if (!addNewAvailabilityToDB(newAvailability)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Availability already exists for athlete");
            }

            logger.info("Availability: Added");
            return ResponseEntity.ok().body("Added");
        } else {
            logger.info("Availability: Athlete not found");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Athlete not found");
        }
    }

    // Update existing future record of availability for athlete
    public ResponseEntity<String> update(String athleteId, AvailabilityRequest request) {
        logger.info("Availability: Updating for Athlete ... ");

        // Get athlete obj from db
        Athlete athlete = athleteService.getAthleteFromDb(athleteId);

        if (athlete != null) {
            // Remove current availability obj
            deleteAvailabilityFromDB(request.getAvailabilityId());

            // Create new updated availability obj
            Availability newAvailability = new AvailabilityMapper().fromAvailabilityRequestToNewEntity(request);
            newAvailability.setAthlete(athlete);

            // Add new object to db
            if (!addNewAvailabilityToDB(newAvailability)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Availability already exists for athlete");
            }

            logger.info("Availability: Updated");
            return ResponseEntity.ok().body("Updated");
        } else {
            logger.info("Availability: Athlete not found");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Athlete not found");
        }
    }

    boolean deleteAvailabilityFromDB(String availabilityId) {
        Optional<Availability> availability = availabilityRepository.findById(availabilityId);
        if (availability.isPresent()) {
            availabilityRepository.delete(availability.get());
            return true;
        } else
            return false;
    }

    boolean addNewAvailabilityToDB(Availability availability) {
        if (checkAvailabilityAdd(availabilityRepository.findByAthlete(availability.getAthlete()), availability)) {
            availabilityRepository.save(availability);
            return true;
        }
        return false;
    }

    // Delete existing future records of availability for athlete (if appointment is not set)
    public ResponseEntity<String> delete(String availabilityId) {
        logger.info("Availability: Delete for Athlete ... ");

        if (deleteAvailabilityFromDB(availabilityId)) {
            logger.info("Availability: Deleted");
            return ResponseEntity.ok().body("Deleted");
        } else {
            logger.info("Availability: Not found");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Availability not found");
        }
    }

    public boolean checkAvailabilityAdd(List<Availability> presentAvailabilities, Availability newAvailability) {
        if (presentAvailabilities.isEmpty())
            return true;

        for (Availability eachAvailability: presentAvailabilities) {
            if (Math.abs(newAvailability.getStartTimeStamp() - eachAvailability.getStartTimeStamp()) < 2000) {
                logger.info("Availability: Availability already exists for athlete");
                return false;
            }
        }
        return true;
    }
}
