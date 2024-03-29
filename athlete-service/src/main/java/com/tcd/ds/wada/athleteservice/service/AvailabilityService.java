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

    public ResponseEntity<Availability> get(String availabilityId) {
        logger.info("Availability: Getting Availability");
        Availability availability = getAvailabilityFromDb(availabilityId);
        if (availability != null) {
            //AvailabilityResponse response = new AvailabilityMapper().fromEntityToResponse(availability);
            return ResponseEntity.ok(availability);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //@Cacheable(value = "Availability")
    public ResponseEntity<List<Availability>> get() {
        logger.info("Availability: Getting All Availabilities");
        List<Availability> availabilityList = (List<Availability>) availabilityRepository.findAll();
        //List<AvailabilityResponse> responseList = new AvailabilityMapper().fromEntityListToResponseList(availabilityList);
        return ResponseEntity.ok(availabilityList);
    }

    public ResponseEntity<List<Availability>> getForAthlete(String athleteId) {
        logger.info("Availability: Getting all Availabilities for athlete id " + athleteId);

        Athlete athlete = athleteService.getAthleteFromDb(athleteId);
        if (athlete != null) {
            List<Availability> availabilityList = getAvailabilitiesOfAthlete(athlete);
            //List<AvailabilityResponse> responseList = new AvailabilityMapper().fromEntityListToResponseList(availabilityList);
            return ResponseEntity.ok(availabilityList);
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
            if (System.currentTimeMillis() > request.getStartTimeStamp())
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Availability time should be in future and in milliseconds");

            Availability newAvailability = new AvailabilityMapper().fromRequestToNewEntity(request);
            newAvailability.setAthlete(athlete);

            String newAvailabilityId = addNewAvailabilityToDB(newAvailability);
            if (newAvailabilityId == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Availability already exists for athlete");
            }

            logger.info("Availability: Added");
            return ResponseEntity.ok().body(newAvailabilityId);
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
        Optional<Availability> availability = availabilityRepository.findById(request.getAvailabilityId());

        if (athlete != null && availability.isPresent()) {

            Availability currentAvailability = availability.get();

            Long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis > request.getStartTimeStamp())
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Availability time should be in future and in milliseconds");

            if (currentAvailability.getStartTimeStamp() - currentTimeMillis < 172800000)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Availability within 48 hours. Cannot be edited.");

            // Remove current availability obj
            deleteAvailabilityFromDB(request.getAvailabilityId());

            // Create new updated availability obj
            Availability newAvailability = new AvailabilityMapper().fromRequestToNewEntity(request);
            newAvailability.setAthlete(athlete);

            // Add new object to db
            String updatedAvailabilityId = addNewAvailabilityToDB(newAvailability);
            if (updatedAvailabilityId == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Availability already exists for athlete");
            }

            logger.info("Availability: Updated");
            return ResponseEntity.ok().body(updatedAvailabilityId);
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
            logger.info("Availability: Deletion not possible");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Availability deletion not possible");
        }
    }

    public boolean checkAvailabilityAdd(List<Availability> presentAvailabilities, Availability newAvailability) {
        if (presentAvailabilities.isEmpty())
            return true;

        for (Availability eachAvailability: presentAvailabilities) {
            if (Math.abs(newAvailability.getStartTimeStamp() - eachAvailability.getStartTimeStamp()) < 3600000) {
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

    String addNewAvailabilityToDB(Availability availability) {
        if (checkAvailabilityAdd(getAvailabilitiesOfAthlete(availability.getAthlete()), availability)) {
            Availability newAvailability = availabilityRepository.save(availability);
            return newAvailability.getAvailabilityId();
        }
        return null;
    }

    boolean deleteAvailabilityFromDB(String availabilityId) {
        Optional<Availability> availability = availabilityRepository.findById(availabilityId);
        if (availability.isPresent()) {

            Availability currentAvailability = availability.get();
            if (currentAvailability.getStartTimeStamp() - System.currentTimeMillis() < 172800000)
                return false;

            availabilityRepository.delete(availability.get());
            return true;
        } else
            return false;
    }
}
