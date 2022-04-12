package com.tcd.ds.wada.athleteservice.model;

import com.tcd.ds.wada.userservice.entity.Location;
import lombok.Data;

@Data
public class AvailabilityRequest {
    private String availabilityId;
    private Long startTimeStamp;
    private Location location;
}