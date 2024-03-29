package com.tcd.ds.wada.userservice.controller.location;

import com.tcd.ds.wada.userservice.model.location.LocationRequest;
import com.tcd.ds.wada.userservice.model.location.LocationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface LocationController {

    String LOCATION_BASE_URL = "/location";

    @PostMapping(path = LOCATION_BASE_URL)
    ResponseEntity<String> add(@RequestBody List<LocationRequest> requests);

    @GetMapping(path = LOCATION_BASE_URL)
    ResponseEntity<LocationResponse> get();
}
