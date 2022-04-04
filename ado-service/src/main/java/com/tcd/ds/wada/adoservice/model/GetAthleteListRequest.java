package com.tcd.ds.wada.adoservice.model;

import lombok.Data;

import java.util.Optional;

@Data
public class GetAthleteListRequest {
    private Integer adoLocationId;
    private Long startTime;
    private Long endTime;
}
