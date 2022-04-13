package com.tcd.ds.wada.athleteservice.controller.availability;

import com.tcd.ds.wada.athleteservice.entity.Athlete;
import com.tcd.ds.wada.athleteservice.entity.Availability;
import com.tcd.ds.wada.athleteservice.model.AvailabilityRequest;
import com.tcd.ds.wada.athleteservice.model.AvailabilityResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AvailabilityController {

    String AVAILABILITY_BASE_URL = "/availability";

    @GetMapping(path = AVAILABILITY_BASE_URL + "/{availabilityId}")
    ResponseEntity<AvailabilityResponse> get(@PathVariable(value = "availabilityId") final String availabilityId);

    @GetMapping(path = AVAILABILITY_BASE_URL)
    ResponseEntity<List<AvailabilityResponse>> get();

    @GetMapping(path = AVAILABILITY_BASE_URL + "/athlete/{athleteId}")
    ResponseEntity<List<AvailabilityResponse>> getForAthlete(@PathVariable(value = "athleteId") final String athleteId);

    @PostMapping(path = AVAILABILITY_BASE_URL + "/{athleteId}")
    ResponseEntity<Object> add(@PathVariable(value = "athleteId") final String athleteId, @RequestBody AvailabilityRequest request);

    @PatchMapping(path = AVAILABILITY_BASE_URL + "/{athleteId}")
    ResponseEntity<?> update(@PathVariable(value = "athleteId") final String athleteId, @RequestBody AvailabilityRequest request);

    @DeleteMapping(path = AVAILABILITY_BASE_URL + "/{availabilityId}")
    ResponseEntity<?> delete(@PathVariable(value = "availabilityId") final String availabilityId);

}
