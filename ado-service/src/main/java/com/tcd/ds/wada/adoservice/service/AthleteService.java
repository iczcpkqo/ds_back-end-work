package com.tcd.ds.wada.adoservice.service;

import com.tcd.ds.wada.adoservice.model.BookTestRequest;
import com.tcd.ds.wada.adoservice.model.GetAthleteListRequest;
import com.tcd.ds.wada.adoservice.repository.AdoRepository;
import com.tcd.ds.wada.athleteservice.entity.Athlete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {

    @Autowired
    AdoRepository adoRepository;

    public List<Athlete> getListOfAthletes(GetAthleteListRequest getAthleteListRequest) {

        List<Athlete> athletes = null;
        if(getAthleteListRequest != null) {
            athletes = adoRepository.findByLocation(getAthleteListRequest.getAdoLocationId());
        }
        return athletes;
    }

    public void bookTestForAthlete(BookTestRequest bookTestRequest) {

    }
}
