package com.tcd.ds.wada.athleteservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Availability {
    @Id
    private String availabilityId;
    private Long startTimeStamp;
    private Location location;
}
