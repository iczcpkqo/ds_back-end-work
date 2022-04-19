package com.tcd.ds.wada.athleteservice.controller.availability;

import com.tcd.ds.wada.athleteservice.entity.Availability;
import com.tcd.ds.wada.athleteservice.model.AvailabilityRequest;
import com.tcd.ds.wada.athleteservice.model.AvailabilityResponse;
import com.tcd.ds.wada.athleteservice.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AvailabilityControllerImpl implements AvailabilityController {

    @Autowired
    AvailabilityService service;

    @Override
    public ResponseEntity<Availability> get(String availabilityId) {
        return service.get(availabilityId);
    }

    @Override
    public ResponseEntity<List<Availability>> get() {
        return service.get();
    }

    @Override
    public ResponseEntity<List<Availability>> getForAthlete(String athleteId) {
        return service.getForAthlete(athleteId);
    }

    @Override
    public ResponseEntity<Object> add(String athleteId, AvailabilityRequest request) {
        return service.add(athleteId, request);
    }

    @Override
    public ResponseEntity<?> update(String athleteId, AvailabilityRequest request) {
        return service.update(athleteId, request);
    }

    @Override
    public ResponseEntity<?> delete(String availabilityId) {
        return service.delete(availabilityId);
    }

}
