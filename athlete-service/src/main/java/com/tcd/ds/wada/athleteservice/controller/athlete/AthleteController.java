package com.tcd.ds.wada.athleteservice.controller.athlete;

import com.tcd.ds.wada.athleteservice.entity.Athlete;
import com.tcd.ds.wada.athleteservice.model.AthleteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface AthleteController {

    String ATHLETE_BASE_URL = "/athlete";

    @PostMapping(path = ATHLETE_BASE_URL)
    ResponseEntity<String> add(@RequestBody AthleteRequest request);

    @GetMapping(path = ATHLETE_BASE_URL + "/{athleteId}")
    ResponseEntity<Athlete> get(@PathVariable(value = "athleteId") final String athleteId);

    @GetMapping(path = ATHLETE_BASE_URL)
    ResponseEntity<List<Athlete>> get();

    @DeleteMapping(path = ATHLETE_BASE_URL + "/{athleteId}")
    ResponseEntity<String> delete(@PathVariable(value = "athleteId") final String athleteId);
}
