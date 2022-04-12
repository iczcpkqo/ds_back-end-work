package com.tcd.ds.wada.adoservice.model;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Data
public class GetAthleteListRequest {

    @NotNull
    private Integer adoId;
}
