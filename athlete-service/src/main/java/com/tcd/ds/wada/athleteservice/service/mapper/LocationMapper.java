package com.tcd.ds.wada.athleteservice.service.mapper;

import com.tcd.ds.wada.athleteservice.entity.Location;
import com.tcd.ds.wada.athleteservice.model.LocationRequest;

public class LocationMapper {
    public Location fromLocationRequestToEntity(LocationRequest request) {
        Location location = new Location();
        location.setCountry(request.getCountry());
        location.setRegion(request.getRegion());
        return location;
    }
}
