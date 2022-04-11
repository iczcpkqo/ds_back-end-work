package com.tcd.ds.wada.userservice.service;

import com.tcd.ds.wada.userservice.entity.Location;
import com.tcd.ds.wada.userservice.model.location.LocationRequest;
import com.tcd.ds.wada.userservice.model.location.LocationResponse;
import com.tcd.ds.wada.userservice.repository.LocationRepository;
import com.tcd.ds.wada.userservice.service.mapper.LocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private static final Logger logger = LoggerFactory.getLogger(LocationService.class);

    @Autowired
    LocationRepository repository;

    public ResponseEntity<String> add(List<LocationRequest> requests) {
        logger.info("Adding Location ... ");

        for (LocationRequest request: requests) {
            Location location = new LocationMapper().fromLocationRequestToEntity(request);
            location = repository.save(location);
            logger.info("Added location (" + location.getCountry() + ")");
        }

        return ResponseEntity.ok("Added " + requests.size());
    }

    public ResponseEntity<LocationResponse> get() {
        logger.info("Getting Locations ... ");

        LocationResponse response = new LocationResponse();
        response.setLocations((List<Location>) repository.findAll());

        return ResponseEntity.ok(response);
    }
}
