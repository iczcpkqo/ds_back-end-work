package com.tcd.ds.wada.athleteservice.service.mapper;

import com.tcd.ds.wada.athleteservice.entity.Availability;
import com.tcd.ds.wada.athleteservice.model.AvailabilityRequest;
import com.tcd.ds.wada.athleteservice.model.AvailabilityResponse;

import java.util.ArrayList;
import java.util.List;

public class AvailabilityMapper {
    public Availability fromRequestToNewEntity(AvailabilityRequest availabilityRequest) {
        Availability availability = new Availability();
        availability.setStartTimeStamp(availabilityRequest.getStartTimeStamp());
        availability.setLocation(availabilityRequest.getLocation());
        availability.setIsAppointment(false);
        return availability;
    }

    public Availability fromRequestToUpdateEntity(AvailabilityRequest availabilityRequest) {
        Availability availability = fromRequestToNewEntity(availabilityRequest);
        availability.setAvailabilityId(availabilityRequest.getAvailabilityId());
        return availability;
    }

    public AvailabilityResponse fromEntityToResponse(Availability availability) {
        AvailabilityResponse response = new AvailabilityResponse();
        response.setAvailabilityId(availability.getAvailabilityId());
        response.setLocation(availability.getLocation());
        response.setAthlete(availability.getAthlete());
        response.setStartTimeStamp(availability.getStartTimeStamp());
        return response;
    }

    public List<AvailabilityResponse> fromEntityListToResponseList(List<Availability> availabilityList) {
        List<AvailabilityResponse> availabilityResponseList = new ArrayList<>();
        for (Availability availability: availabilityList)
            availabilityResponseList.add(fromEntityToResponse(availability));
        return availabilityResponseList;
    }
}
