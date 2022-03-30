package com.tcd.ds.wada.athleteservice.model;

import lombok.Data;

@Data
public class AvailabilityRequest {
    private String athleteId;
    private Long timeStamp;
    private String location;
}
