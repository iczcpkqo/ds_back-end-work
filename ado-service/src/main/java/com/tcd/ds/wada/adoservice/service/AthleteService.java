package com.tcd.ds.wada.adoservice.service;

import com.tcd.ds.wada.adoservice.model.BookTestRequest;
import com.tcd.ds.wada.adoservice.model.EditTestRequest;
import com.tcd.ds.wada.adoservice.model.GetAthleteListRequest;
import com.tcd.ds.wada.adoservice.repository.AdoRepository;
import com.tcd.ds.wada.athleteservice.entity.Athlete;
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

    public ResponseEntity<List<Athlete>> getListOfAthletes(GetAthleteListRequest getAthleteListRequest) {

        logger.info("Intercepted request to get List of athletes for locationId: " + getAthleteListRequest.getAdoLocationId());
        List<Athlete> athletes;
        athletes = adoRepository.findByLocation(getAthleteListRequest.getAdoLocationId());
        if(!athletes.isEmpty()) {
            return ResponseEntity.ok(athletes);
        }
        else{
            logger.error("No athletes found for locationId: " + getAthleteListRequest.getAdoLocationId());
            return ResponseEntity.notFound().build();
        }

    }

    public void bookTestForAthlete(BookTestRequest bookTestRequest) {
        logger.info("Intercepted request to book test for athleteId: " + bookTestRequest.getAthleteId());

    }

    public void editTest(EditTestRequest editTestRequest) {
    }
}
