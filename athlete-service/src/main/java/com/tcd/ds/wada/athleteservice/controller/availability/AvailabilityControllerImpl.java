package com.tcd.ds.wada.athleteservice.controller.availability;

import com.tcd.ds.wada.athleteservice.model.AvailabilityRequest;
import com.tcd.ds.wada.athleteservice.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AvailabilityControllerImpl implements AvailabilityController {

    @Autowired
    AvailabilityService service;

//    @Override
//    public ResponseEntity<List<Availability>> get(Integer athleteId) {
//        return service.get(athleteId);
//    }

    @Override
    public ResponseEntity<Object> add(String athleteId, AvailabilityRequest request) {
        return service.add(athleteId, request);
    }

    @Override
    public ResponseEntity<?> update(String athleteId, AvailabilityRequest request) {
        return service.update(athleteId, request);
    }

    @Override
    public ResponseEntity<?> delete(String athleteId, AvailabilityRequest request) {
        return service.delete(athleteId, request);
    }

}
