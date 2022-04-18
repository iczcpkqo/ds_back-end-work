package com.tcd.ds.wada.athleteservice.service;

import com.tcd.ds.wada.athleteservice.entity.Athlete;
import com.tcd.ds.wada.athleteservice.entity.Availability;
import com.tcd.ds.wada.athleteservice.model.AvailabilityRequest;
import com.tcd.ds.wada.athleteservice.model.AvailabilityResponse;
import com.tcd.ds.wada.athleteservice.repository.AvailabilityRepository;
import com.tcd.ds.wada.athleteservice.service.mapper.AvailabilityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityService {
    private static final Logger logger = LoggerFactory.getLogger(AvailabilityService.class);

    @Autowired
    AthleteService athleteService;

    @Autowired
    AvailabilityRepository availabilityRepository;

    public ResponseEntity<AvailabilityResponse> get(String availabilityId) {
        logger.info("Availability: Getting Availability");
        Availability availability = getAvailabilityFromDb(availabilityId);
        if (availability != null) {
            AvailabilityResponse response = new AvailabilityMapper().fromEntityToResponse(availability);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //@Cacheable(value = "Availability")
    public ResponseEntity<List<AvailabilityResponse>> get() {
        logger.info("Availability: Getting All Availabilities");
        List<Availability> availabilityList = (List<Availability>) availabilityRepository.findAll();
        List<AvailabilityResponse> responseList = new AvailabilityMapper().fromEntityListToResponseList(availabilityList);
        return ResponseEntity.ok(responseList);
    }

    public ResponseEntity<List<AvailabilityResponse>> getForAthlete(String athleteId) {
        logger.info("Availability: Getting all Availabilities for athlete id " + athleteId);

        Athlete athlete = athleteService.getAthleteFromDb(athleteId);
        if (athlete != null) {
            List<Availability> availabilityList = getAvailabilitiesOfAthlete(athlete);
            List<AvailabilityResponse> responseList = new AvailabilityMapper().fromEntityListToResponseList(availabilityList);
            return ResponseEntity.ok(responseList);
        } else {
            logger.info("Availability: Athlete not found");
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    // Add new record of availability for athlete
    //@CachePut(value = "Availability", key = "#request.availabilityId")
    public ResponseEntity<Object> add(String athleteId, AvailabilityRequest request) {
        logger.info("Availability: Adding for Athlete ... ");

        Athlete athlete = athleteService.getAthleteFromDb(athleteId);

        if (athlete != null) {
            Availability newAvailability = new AvailabilityMapper().fromRequestToNewEntity(request);
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
    //@CachePut(value = "Availability", key = "#request.availabilityId")
    public ResponseEntity<String> update(String athleteId, AvailabilityRequest request) {
        logger.info("Availability: Updating for Athlete ... ");

        // Get athlete obj from db
        Athlete athlete = athleteService.getAthleteFromDb(athleteId);

        if (athlete != null) {
            // Remove current availability obj
            deleteAvailabilityFromDB(request.getAvailabilityId());

            // Create new updated availability obj
            Availability newAvailability = new AvailabilityMapper().fromRequestToNewEntity(request);
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

    //@Cacheable(value = "Availability", key = "#availabilityId")
    public Availability getAvailabilityFromDb(String availabilityId) {
        logger.info("Availability: Getting Availability Id (" + availabilityId + ") from DB");

        Optional<Availability> availability = availabilityRepository.findById(availabilityId);

        if (availability.isPresent()) {
            logger.info("Availability: Found");
            return availability.get();
        } else {
            logger.info("Availability: Not found");
            return null;
        }
    }

    List<Availability> getAvailabilitiesOfAthlete(Athlete athlete) {
        return availabilityRepository.findByAthlete(athlete);
    }

    boolean addNewAvailabilityToDB(Availability availability) {
        if (checkAvailabilityAdd(getAvailabilitiesOfAthlete(availability.getAthlete()), availability)) {
            availabilityRepository.save(availability);
            return true;
        }
        return false;
    }

    boolean deleteAvailabilityFromDB(String availabilityId) {
        Optional<Availability> availability = availabilityRepository.findById(availabilityId);
        if (availability.isPresent()) {
            availabilityRepository.delete(availability.get());
            return true;
        } else
            return false;
    }
}
