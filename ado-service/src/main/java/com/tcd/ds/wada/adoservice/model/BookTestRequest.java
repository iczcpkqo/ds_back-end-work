package com.tcd.ds.wada.adoservice.model;

import com.tcd.ds.wada.userservice.entity.Athlete;
import com.tcd.ds.wada.userservice.entity.Availability;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookTestRequest {

    @NotNull
    private String availabilityId;
}
