package com.tcd.ds.wada.adoservice.service;

import com.tcd.ds.wada.adoservice.entity.Appointments;
import com.tcd.ds.wada.adoservice.model.BookTestRequest;
import com.tcd.ds.wada.adoservice.model.GetAthleteListRequest;
import com.tcd.ds.wada.adoservice.repository.AppointmentRepository;
import com.tcd.ds.wada.userservice.entity.Athlete;
import com.tcd.ds.wada.userservice.repository.AdoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteService {

    private static final Logger logger = LoggerFactory.getLogger(AthleteService.class);

    @Autowired
    AdoRepository adoRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    public ResponseEntity<List<Athlete>> getListOfAthletes(GetAthleteListRequest getAthleteListRequest) {

        logger.info("Intercepted request to get List of athletes for country: " + getAthleteListRequest.getCountry());
        List<Athlete> athletes;
        athletes = adoRepository.findByLocation(getAthleteListRequest.getCountry());
        if(!athletes.isEmpty()) {
            return ResponseEntity.ok(athletes);
        }
        else{
            logger.error("No athletes found for country: " + getAthleteListRequest.getCountry());
            return ResponseEntity.notFound().build();
        }

    }

    public void bookTestForAthlete(BookTestRequest bookTestRequest) {
        if(bookTestRequest != null){
            Appointments appointments = new Appointments();
            appointments.setAthlete(bookTestRequest.getAthlete());
            appointments.setAppointmentDetails(bookTestRequest.getAvailability());
            appointments.setBookedBy(bookTestRequest.getAdoId());
            appointmentRepository.save(appointments);
        }
        logger.info("Intercepted request to book test for athleteId: " + bookTestRequest.getAthlete().getAthleteId());
    }

}
