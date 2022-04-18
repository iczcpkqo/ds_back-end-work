package com.tcd.ds.wada.adoservice.controller;

import com.tcd.ds.wada.adoservice.entity.Athlete;
import com.tcd.ds.wada.adoservice.entity.Availability;
import com.tcd.ds.wada.adoservice.model.BookTestRequest;
import com.tcd.ds.wada.adoservice.model.GetAppointmentsRequest;
import com.tcd.ds.wada.adoservice.model.GetAthleteListRequest;
import com.tcd.ds.wada.adoservice.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdoControllerImpl implements AdoController {

    @Autowired
    AthleteService athleteService;

    //@Cacheable
    public ResponseEntity<List<Athlete>> getListOfAthletes(GetAthleteListRequest getAthleteListRequest){
        return athleteService.getListOfAthletes(getAthleteListRequest);
    }

    //@Cacheable
    public ResponseEntity<List<Availability>> getAllAppointments(GetAppointmentsRequest getAppointmentsRequest) {
        return athleteService.getListOfAppointments(getAppointmentsRequest.getAdoId());
    }

    public ResponseEntity<?> bookTestForAthlete(BookTestRequest bookTestRequest){
        return athleteService.bookTestForAthlete(bookTestRequest);
    }

}
