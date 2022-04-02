package com.tcd.ds.wada.adoservice.controller;

import com.tcd.ds.wada.adoservice.entity.Ado;
import com.tcd.ds.wada.adoservice.model.BookTestRequest;
import com.tcd.ds.wada.adoservice.model.GetAthleteListRequest;
import com.tcd.ds.wada.adoservice.service.AthleteService;
import com.tcd.ds.wada.athleteservice.entity.Athlete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public class AdoControllerImpl implements AdoController {

    @Autowired
    AthleteService athleteService;

    public List<Athlete> getListOfAthletes(GetAthleteListRequest getAthleteListRequest){
        return athleteService.getListOfAthletes(getAthleteListRequest);
    }

    public void bookTestForAthlete(BookTestRequest bookTestRequest){
        athleteService.bookTestForAthlete(bookTestRequest);
    }
}
