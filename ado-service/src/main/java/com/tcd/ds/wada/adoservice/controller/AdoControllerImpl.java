package com.tcd.ds.wada.adoservice.controller;

import com.tcd.ds.wada.adoservice.entity.Athlete;
import com.tcd.ds.wada.adoservice.entity.Availability;
import com.tcd.ds.wada.adoservice.model.BookTestRequest;
import com.tcd.ds.wada.adoservice.model.GetAppointmentsRequest;
import com.tcd.ds.wada.adoservice.model.GetAthleteListRequest;
import com.tcd.ds.wada.adoservice.service.ADOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdoControllerImpl implements AdoController {

    @Autowired
    ADOService ADOService;

    //@Cacheable
    public ResponseEntity<List<Athlete>> getListOfAthletes(GetAthleteListRequest getAthleteListRequest){
        return ADOService.getListOfAthletes(getAthleteListRequest);
    }

    //@Cacheable
    public ResponseEntity<List<Availability>> getAllAppointments(GetAppointmentsRequest getAppointmentsRequest) {
        return ADOService.getListOfAppointments(getAppointmentsRequest.getAdoId());
    }

    public ResponseEntity<?> bookTestForAthlete(BookTestRequest bookTestRequest){
        return ADOService.bookTestForAthlete(bookTestRequest);
    }

}
