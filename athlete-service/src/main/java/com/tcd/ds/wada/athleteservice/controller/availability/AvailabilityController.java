package com.tcd.ds.wada.athleteservice.controller.availability;

import com.tcd.ds.wada.athleteservice.entity.Availability;
import com.tcd.ds.wada.athleteservice.model.AvailabilityRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AvailabilityController {

    String AVAILABILITY_BASE_URL = "/athlete/availability";

    @GetMapping(path = AVAILABILITY_BASE_URL + "/{athleteId}")
    ResponseEntity<List<Availability>> get(@PathVariable(value = "athleteId") final String athleteId);

    @PostMapping(path = AVAILABILITY_BASE_URL)
    ResponseEntity<String> add(@RequestBody AvailabilityRequest request);

    @PatchMapping(path = AVAILABILITY_BASE_URL + "/{availabilityId}")
    ResponseEntity<?> update(@PathVariable(value = "availabilityId") final String availabilityId, @RequestBody AvailabilityRequest request);

    @DeleteMapping(path = AVAILABILITY_BASE_URL + "/{availabilityId}")
    ResponseEntity<?> delete(@PathVariable(value = "availabilityId") final String availabilityId);

}
