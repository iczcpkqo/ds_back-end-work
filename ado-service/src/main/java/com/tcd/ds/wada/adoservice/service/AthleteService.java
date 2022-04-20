package com.tcd.ds.wada.adoservice.service;

import com.tcd.ds.wada.adoservice.model.BookTestRequest;
import com.tcd.ds.wada.adoservice.model.GetAthleteListRequest;
import com.tcd.ds.wada.userservice.entity.Ado;
import com.tcd.ds.wada.userservice.entity.Athlete;
import com.tcd.ds.wada.userservice.entity.Availability;
import com.tcd.ds.wada.userservice.entity.Location;
import com.tcd.ds.wada.userservice.repository.AdoRepository;
import com.tcd.ds.wada.userservice.repository.AthleteRepository;
import com.tcd.ds.wada.userservice.repository.AvailabilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {

    private static final Logger logger = LoggerFactory.getLogger(AthleteService.class);

    @Autowired
    AdoRepository adoRepository;

    @Autowired
    AvailabilityRepository availabilityRepository;

    //Returns list of athletes in ado's location
    public ResponseEntity<List<Athlete>> getListOfAthletes(GetAthleteListRequest getAthleteListRequest) {

        logger.info("Intercepted request to get List of athletes for ado: " + getAthleteListRequest.getAdoId());
        List<Athlete> athletes;
        Optional<Ado> ado = adoRepository.findById(getAthleteListRequest.getAdoId());
        athletes = ado.get().getAthletes();
        if(athletes.isEmpty()){
            logger.info("No athletes registered under this ado");
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(athletes);

    }

    @CachePut(value = "Availability", key = "#availabilityId")
    public void bookTestForAthlete(BookTestRequest bookTestRequest) {

        logger.info("Intercepted request to book test for athlete with availability: " + bookTestRequest.getAvailabilityId());

        Optional<Availability> availability = availabilityRepository.findById(bookTestRequest.getAvailabilityId());
        availability.get().setIsAppointment(true);
        availabilityRepository.save(availability.get());

        logger.info("Availability updated for athlete");
    }

    public ResponseEntity<List<Availability>> getListOfAppointments(Integer adoId){

        logger.info("Intercepted request to get list of appointments for ado: " + adoId);

        List<Availability> appointments = null;
        if(adoId != null){
            Location location = adoRepository.findById(adoId).get().getLocation();
            appointments = availabilityRepository.findByLocation(location);
        }
        return ResponseEntity.ok(appointments);
    }

}