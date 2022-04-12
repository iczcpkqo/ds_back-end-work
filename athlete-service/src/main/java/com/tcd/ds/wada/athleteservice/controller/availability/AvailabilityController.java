package com.tcd.ds.wada.athleteservice.controller.availability;

import com.tcd.ds.wada.athleteservice.model.AvailabilityRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface AvailabilityController {

    String AVAILABILITY_BASE_URL = "/athlete/availability";

    @PostMapping(path = AVAILABILITY_BASE_URL + "/{athleteId}")
    ResponseEntity<Object> add(@PathVariable(value = "athleteId") final String athleteId, @RequestBody AvailabilityRequest request);

    @PatchMapping(path = AVAILABILITY_BASE_URL + "/{athleteId}")
    ResponseEntity<?> update(@PathVariable(value = "athleteId") final String athleteId, @RequestBody AvailabilityRequest request);

    @DeleteMapping(path = AVAILABILITY_BASE_URL + "/{availabilityId}")
    ResponseEntity<?> delete(@PathVariable(value = "availabilityId") final String availabilityId);

}
