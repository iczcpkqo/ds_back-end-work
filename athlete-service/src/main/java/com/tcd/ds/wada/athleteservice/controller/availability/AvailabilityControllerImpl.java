package com.tcd.ds.wada.athleteservice.controller.availability;

import com.tcd.ds.wada.athleteservice.entity.Availability;
import com.tcd.ds.wada.athleteservice.model.AvailabilityRequest;
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
    public ResponseEntity<List<Availability>> get(Integer athleteId) {
        return service.get(athleteId);
    }

    @Override
    public ResponseEntity<Integer> add(AvailabilityRequest request) {
        return service.add(request);
    }

    @Override
    public ResponseEntity<?> update(Integer availabilityId, AvailabilityRequest request) {
        return service.update(availabilityId, request);
    }

    @Override
    public ResponseEntity<?> delete(Integer availabilityId) {
        return service.delete(availabilityId);
    }

}
