package com.tcd.ds.wada.athleteservice.controller.location;

import com.tcd.ds.wada.athleteservice.entity.Location;
import com.tcd.ds.wada.athleteservice.model.LocationRequest;
import com.tcd.ds.wada.athleteservice.model.LocationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface LocationController {

    String ATHLETE_BASE_URL = "/location";

    @PostMapping(path = ATHLETE_BASE_URL)
    ResponseEntity<String> add(@RequestBody List<LocationRequest> requests);

    @GetMapping(path = ATHLETE_BASE_URL)
    ResponseEntity<LocationResponse> get();
}
