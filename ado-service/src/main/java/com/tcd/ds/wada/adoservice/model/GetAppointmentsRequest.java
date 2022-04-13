package com.tcd.ds.wada.adoservice.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetAppointmentsRequest {

    @NotNull
    private String adoId;
}
