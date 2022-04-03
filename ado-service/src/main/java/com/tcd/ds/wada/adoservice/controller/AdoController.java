package com.tcd.ds.wada.adoservice.controller;

import com.tcd.ds.wada.adoservice.model.BookTestRequest;
import com.tcd.ds.wada.adoservice.model.EditTestRequest;
import com.tcd.ds.wada.adoservice.model.GetAthleteListRequest;
import com.tcd.ds.wada.athleteservice.entity.Athlete;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface AdoController {
    String ADO_BASE_URL = "/ado";

    @ResponseBody
    @PostMapping(path = ADO_BASE_URL + "/getAthletes")
    public ResponseEntity<List<Athlete>> getListOfAthletes(
            @RequestBody GetAthleteListRequest getAthleteListRequest);

    @ResponseBody
    @PostMapping(path = ADO_BASE_URL + "/bookTestForAthlete")
    public void bookTestForAthlete(
            @RequestBody BookTestRequest bookTestRequest);

    @ResponseBody
    @PostMapping(path = ADO_BASE_URL + "/editBookedTestForAthlete")
    public void editBookedTestForAthlete(
            @RequestBody EditTestRequest editTestRequest);
}
