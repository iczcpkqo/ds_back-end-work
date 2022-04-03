package com.tcd.ds.wada.athleteservice.controller.location;

import com.tcd.ds.wada.athleteservice.entity.Location;
import com.tcd.ds.wada.athleteservice.model.LocationRequest;
import com.tcd.ds.wada.athleteservice.model.LocationResponse;
import com.tcd.ds.wada.athleteservice.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LocationControllerImpl implements LocationController {

    @Autowired
    LocationService service;

    @Override
    public ResponseEntity<String> add(List<LocationRequest> requests) {
        return service.add(requests);
    }

    @Override
    public ResponseEntity<LocationResponse> get() {
        return service.get();
    }
}
