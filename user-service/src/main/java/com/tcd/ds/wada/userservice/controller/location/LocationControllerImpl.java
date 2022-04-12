package com.tcd.ds.wada.userservice.controller.location;

import com.tcd.ds.wada.userservice.model.location.LocationRequest;
import com.tcd.ds.wada.userservice.model.location.LocationResponse;
import com.tcd.ds.wada.userservice.service.LocationService;
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
