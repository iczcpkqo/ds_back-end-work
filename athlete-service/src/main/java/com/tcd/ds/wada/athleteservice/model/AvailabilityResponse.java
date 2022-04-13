package com.tcd.ds.wada.athleteservice.model;

import com.tcd.ds.wada.athleteservice.entity.Athlete;
import com.tcd.ds.wada.athleteservice.entity.Location;
import lombok.Data;

@Data
public class AvailabilityResponse {
    private String availabilityId;
    private Athlete athlete;
    private Long startTimeStamp;
    private Location location;
}
