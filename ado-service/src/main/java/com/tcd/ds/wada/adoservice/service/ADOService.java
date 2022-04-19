package com.tcd.ds.wada.adoservice.service;

import com.tcd.ds.wada.adoservice.entity.*;
import com.tcd.ds.wada.adoservice.model.BookTestRequest;
import com.tcd.ds.wada.adoservice.model.GetAthleteListRequest;
import com.tcd.ds.wada.adoservice.repository.AdoRepository;
import com.tcd.ds.wada.adoservice.repository.AthleteRepository;
import com.tcd.ds.wada.adoservice.repository.AvailabilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ADOService {

    private static final Logger logger = LoggerFactory.getLogger(ADOService.class);

    @Autowired
    AdoRepository adoRepository;

    @Autowired
    AvailabilityRepository availabilityRepository;

    @Autowired
    AthleteRepository athleteRepository;

    //Returns list of athletes in ado's location
    @Cacheable(value="Athlete")
    public List<Athlete> getListOfAthletes(GetAthleteListRequest getAthleteListRequest) {

        logger.info("Intercepted request to get List of athletes for ado: " + getAthleteListRequest.getAdoId());
        Optional<Ado> ado = adoRepository.findById(getAthleteListRequest.getAdoId());
        Location location = ado.get().getLocation();
        List<Athlete> athleteList = athleteRepository.findByLocation(location);
        if(athleteList.isEmpty()){
            logger.info("No athletes registered under this ado");
            ResponseEntity.noContent().build();
        }
        return athleteList;

    }

    public ResponseEntity<?> bookTestForAthlete(BookTestRequest bookTestRequest) {

        logger.info("Intercepted request to book test for athlete with availability: " + bookTestRequest.getAvailabilityId());

        Optional<Availability> athleteAvailability = availabilityRepository.findById(bookTestRequest.getAvailabilityId());

        if (athleteAvailability.isPresent()) {
            Availability availability = athleteAvailability.get();

            if (availability.getIsAppointment())
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Already booked");

            //can only book within 48 hours
            long currentTime = System.currentTimeMillis();
            if (currentTime < availability.getStartTimeStamp() - Long.parseLong("172800000"))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Booking only allowed within 48 hours of availability");

            //for same availability location and time records, if any of them is true -> not possible
            List<Availability> availabilities =
                    availabilityRepository.findByLocationAndStartTimeStampAndIsAppointment(
                            availability.getLocation(),
                            availability.getStartTimeStamp(),
                            true
                    );

            if (availabilities.isEmpty()) {
                if (deleteAvailabilityFromDB(availability.getAvailabilityId())) {
                    Availability updatedAvailability = new Availability();
                    //updatedAvailability.setAvailabilityId(availability.getAvailabilityId());
                    updatedAvailability.setAthlete(availability.getAthlete());
                    updatedAvailability.setLocation(availability.getLocation());
                    updatedAvailability.setStartTimeStamp(availability.getStartTimeStamp());
                    updatedAvailability.setIsAppointment(true);
                    updatedAvailability = availabilityRepository.save(updatedAvailability);

                    logger.info("Availability updated for athlete");
                    return ResponseEntity.ok().body(updatedAvailability.getAvailabilityId());
                } else
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to update availability");
            } else
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Already booked");
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Availability not found");
    }

//        if(athleteAvailability.get().getIsAppointment()){
//            return ResponseEntity.internalServerError().body("Already booked");
//        }

        //can only book within 48 hours
//        long currentTime = System.currentTimeMillis();
//        if(currentTime < athleteAvailability.get().getStartTimeStamp() - Long.parseLong("172800000")){
//            return ResponseEntity.ok().body("Booking only allowed within 48 hours of availability");
//        }

        //for same availability location and time records, if any of them is true -> not possible
//        List<Availability> availabilities = availabilityRepository.
//                findByLocationAndStartTimeStampAndIsAppointment(athleteAvailability.get().getLocation(),
//                        athleteAvailability.get().getStartTimeStamp(), true);

//        if(availabilities.isEmpty() && !athleteAvailability.get().getIsAppointment()) {
//
//            Availability availability = new Availability();
//            availability.setAvailabilityId(athleteAvailability.get().getAvailabilityId());
//            availability.setAthlete(athleteAvailability.get().getAthlete());
//            availability.setLocation(athleteAvailability.get().getLocation());
//            availability.setStartTimeStamp(athleteAvailability.get().getStartTimeStamp());
//            availability.setIsAppointment(true);
//
//            availabilityRepository.delete(athleteAvailability.get());
//            availabilityRepository.save(availability);
//
//            logger.info("Availability updated for athlete");
//            return ResponseEntity.ok().body("Appointment saved");
//        }
//        else{
//            return ResponseEntity.ok().body("Appointment already exists for another athlete");
//        }

    @Cacheable(value = "Availability")
    public List<Availability> getListOfAppointments(String adoId){

        logger.info("Intercepted request to get list of appointments for ado: " + adoId);

        List<Availability> appointments = null;
        if(adoId != null){
            Location location = adoRepository.findById(adoId).get().getLocation();
            appointments = availabilityRepository.findByAthleteLocationAndIsAppointment(location, true);
            //appointments = appointmentRepository.findByAthleteLocation(location);
        }
        return appointments;
    }

    @CacheEvict(value="Athlete", key="#athleteId")
    boolean deleteAvailabilityFromDB(String availabilityId) {
        Optional<Availability> availability = availabilityRepository.findById(availabilityId);
        if (availability.isPresent()) {
            availabilityRepository.delete(availability.get());
            return true;
        } else
            return false;
    }

}
