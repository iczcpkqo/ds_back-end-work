package com.tcd.ds.wada.adoservice.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetAthleteListRequest {

    @NotNull
    private String adoId;
}
