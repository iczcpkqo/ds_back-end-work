package com.tcd.ds.wada.athleteservice.service.mapper;

import com.tcd.ds.wada.athleteservice.entity.Availability;
import com.tcd.ds.wada.athleteservice.model.AvailabilityRequest;

public class AvailabilityMapper {
    public Availability fromAvailabilityRequestToEntity(AvailabilityRequest availabilityRequest) {
        Availability availability = new Availability();
        availability.setAthleteId(availabilityRequest.getAthleteId());
        availability.setLocation(availabilityRequest.getLocation());
        availability.setTimeStamp(availability.getTimeStamp());
        return availability;
    }
}
