package com.tcd.ds.wada.athleteservice.service.mapper;

import com.tcd.ds.wada.userservice.entity.Availability;
import com.tcd.ds.wada.athleteservice.model.AvailabilityRequest;

public class AvailabilityMapper {
    public Availability fromAvailabilityRequestToAddEntity(AvailabilityRequest availabilityRequest) {
        Availability availability = new Availability();
        availability.setStartTimeStamp(availabilityRequest.getStartTimeStamp());
        availability.setLocation(availabilityRequest.getLocation());

        return availability;
    }

    public Availability fromAvailabilityRequestToUpdateEntity(AvailabilityRequest availabilityRequest) {
        Availability availability = fromAvailabilityRequestToAddEntity(availabilityRequest);
        availability.setAvailabilityId(availabilityRequest.getAvailabilityId());
        return availability;
    }
}
