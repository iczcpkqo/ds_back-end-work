package com.tcd.ds.wada.athleteservice.service.mapper;

import com.tcd.ds.wada.athleteservice.entity.Athlete;
import com.tcd.ds.wada.athleteservice.model.AthleteRequest;

public class AthleteMapper {
    public Athlete fromAthleteRequestToEntity(AthleteRequest athleteRequest) {
        Athlete athlete = new Athlete();
        return athlete;
    }
}
