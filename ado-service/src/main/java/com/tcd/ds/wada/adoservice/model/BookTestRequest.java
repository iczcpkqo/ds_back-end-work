package com.tcd.ds.wada.adoservice.model;

import com.tcd.ds.wada.athleteservice.entity.Availability;
import lombok.Data;

@Data
public class BookTestRequest {
    private Integer athleteId;
    private Integer availability;
    private Integer adoId;
}
