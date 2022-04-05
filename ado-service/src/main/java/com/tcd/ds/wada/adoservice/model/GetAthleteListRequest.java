package com.tcd.ds.wada.adoservice.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Data
public class GetAthleteListRequest {

    @NotNull
    private String country;

    @NotNull
    private Long startTime;

    @NotNull
    private Long endTime;
}
