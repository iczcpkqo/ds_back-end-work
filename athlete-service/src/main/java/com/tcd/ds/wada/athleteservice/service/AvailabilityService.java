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

import java.util.ArrayList;
import java.util.List;

@Service
public class AvailabilityService {
    private static final Logger logger = LoggerFactory.getLogger(AvailabilityService.class);

    @Autowired
    AthleteRepository athleteRepository;

    @Autowired
    AthleteService athleteService;

    @Autowired
    AvailabilityRepository availabilityRepository;

//    // Get existing future records of availability for athlete
//    public ResponseEntity<List<Availability>> get(String athleteId) {
//        logger.info("Getting Availability for Athlete Id (" + athleteId + ") ...");
//
//        Optional<List<Availability>> athleteAvailabilityList = repository.findByAthleteId(athleteId);
//
//        if (athleteAvailabilityList.isPresent()) {
//            logger.info("Found");
//            return ResponseEntity.ok(athleteAvailabilityList.get());
//        } else {
//            logger.info("Not Found");
//            return ResponseEntity.notFound().build();
//        }
//    }

    // Add new record of availability for athlete
    public ResponseEntity<Object> add(String athleteId, AvailabilityRequest request) {
        logger.info("Availability: Adding for Athlete ... ");

        Athlete athlete = athleteService.getAthleteFromDb(athleteId);

        if (athlete != null) {
            Availability newAvailability = new AvailabilityMapper().fromAvailabilityRequestToAddEntity(request);

            if (!checkAvailabilityAdd(athlete.getAvailabilities(), newAvailability))
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Availability already exists for athlete");

            newAvailability = availabilityRepository.save(newAvailability);
            athlete.getAvailabilities().add(newAvailability);
            athleteRepository.save(athlete);

            newAvailability.setAthleteId(athleteId);
            availabilityRepository.save(newAvailability);

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
            // Get new availability obj
            Availability updateAvailability = new AvailabilityMapper().fromAvailabilityRequestToUpdateEntity(request);

            // Remove current availability obj
            List<Availability> newAvailabilityList = deleteAvailability(athlete.getAvailabilities(), updateAvailability);

            // Check updated availability obj can be added or not
            if (!checkAvailabilityAdd(newAvailabilityList, updateAvailability))
                return ResponseEntity.status(HttpStatus.CONFLICT).body("New availability already exists for athlete");

            // Add new availability obj to list and add list to athlete and save
            newAvailabilityList.add(updateAvailability);
            athlete.setAvailabilities(newAvailabilityList);

            athleteRepository.save(athlete);
            logger.info("Availability: Updated");

            return ResponseEntity.ok().body("Updated");
        } else {
            logger.info("Availability: Athlete not found");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Athlete not found");
        }
    }

    // Delete existing future records of availability for athlete (if appointment is not set)
    public ResponseEntity<String> delete(String athleteId, AvailabilityRequest request) {
        logger.info("Availability: Delete for Athlete ... ");

        // Get athlete obj from db
        Athlete athlete = athleteService.getAthleteFromDb(athleteId);

        if (athlete != null) {
            // Get new availability obj
            Availability deleteAvailability = new AvailabilityMapper().fromAvailabilityRequestToUpdateEntity(request);

            // Remove current availability obj
            List<Availability> newAvailabilityList = deleteAvailability(athlete.getAvailabilities(), deleteAvailability);

            athlete.setAvailabilities(newAvailabilityList);

            athleteRepository.save(athlete);
            logger.info("Availability: Deleted");

            return ResponseEntity.ok().body("Deleted");
        } else {
            logger.info("Availability: Athlete not found");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Athlete not found");
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

    public List<Availability> deleteAvailability(List<Availability> currentAvailabilities, Availability removeAvailability) {
        if (currentAvailabilities.isEmpty())
            return currentAvailabilities;

        List<Availability> newAvailabilities = new ArrayList<>();

        for (Availability eachAvailability: currentAvailabilities) {
            if (eachAvailability.getAvailabilityId() != removeAvailability.getAvailabilityId()) {
                newAvailabilities.add(eachAvailability);
            }
        }
        return newAvailabilities;
    }
}
