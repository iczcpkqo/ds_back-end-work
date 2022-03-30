package com.tcd.ds.wada.athleteservice.model;

import lombok.Data;

@Data
public class AvailabilityRequest {
    private Integer athleteId;
    private Long timeStamp;
    private String location;
}
