package com.tcd.ds.wada.athleteservice.service.mapper;

import com.tcd.ds.wada.athleteservice.entity.Availability;
import com.tcd.ds.wada.athleteservice.model.AvailabilityRequest;

public class AvailabilityMapper {
    public Availability fromAvailabilityRequestToNewEntity(AvailabilityRequest availabilityRequest) {
        Availability availability = new Availability();
        availability.setStartTimeStamp(availabilityRequest.getStartTimeStamp());
        availability.setLocation(availabilityRequest.getLocation());

        return availability;
    }

    public Availability fromAvailabilityRequestToUpdateEntity(AvailabilityRequest availabilityRequest) {
        Availability availability = fromAvailabilityRequestToNewEntity(availabilityRequest);
        availability.setAvailabilityId(availabilityRequest.getAvailabilityId());
        return availability;
    }
}
