package com.tcd.ds.wada.userservice.service.mapper;

import com.tcd.ds.wada.userservice.entity.Location;
import com.tcd.ds.wada.userservice.model.location.LocationRequest;

public class LocationMapper {
    public Location fromLocationRequestToEntity(LocationRequest request) {
        Location location = new Location();
        location.setCountry(request.getCountry());
        location.setRegion(request.getRegion());
        return location;
    }
}
