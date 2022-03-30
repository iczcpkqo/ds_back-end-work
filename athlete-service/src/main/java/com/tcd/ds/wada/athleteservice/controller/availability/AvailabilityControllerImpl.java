package com.tcd.ds.wada.athleteservice.controller.availability;

import com.tcd.ds.wada.athleteservice.entity.Availability;
import com.tcd.ds.wada.athleteservice.model.AvailabilityRequest;
import com.tcd.ds.wada.athleteservice.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AvailabilityControllerImpl implements AvailabilityController {

    @Autowired
    AvailabilityService service;

    @Override
    public ResponseEntity<List<Availability>> get(String athleteId) {
        return service.get(athleteId);
    }

    @Override
    public ResponseEntity<String> add(AvailabilityRequest request) {
        return service.add(request);
    }

    @Override
    public ResponseEntity<?> update(String availabilityId, AvailabilityRequest request) {
        return service.update(availabilityId, request);
    }

    @Override
    public ResponseEntity<?> delete(String availabilityId) {
        return service.delete(availabilityId);
    }

}
