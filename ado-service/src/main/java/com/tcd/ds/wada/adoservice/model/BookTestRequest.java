package com.tcd.ds.wada.adoservice.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookTestRequest {

    @NotNull
    private String availabilityId;
}
