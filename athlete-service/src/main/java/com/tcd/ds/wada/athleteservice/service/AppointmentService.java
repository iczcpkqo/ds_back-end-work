package com.tcd.ds.wada.athleteservice.service;

import com.tcd.ds.wada.athleteservice.entity.Availability;
import com.tcd.ds.wada.athleteservice.repository.AvailabilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class AppointmentService {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    @Autowired
    AvailabilityRepository availabilityRepository;

    public ResponseEntity<List<Availability>> get(String athleteId) {
        logger.info("Getting Appointments for athlete ... ");

        Optional<List<Availability>> availabilityIsAnAppointmentList = availabilityRepository.findByAthleteIdAndIsAppointment(athleteId, true);

        return availabilityIsAnAppointmentList
                .map(availabilities -> new ResponseEntity<>(availabilities, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
