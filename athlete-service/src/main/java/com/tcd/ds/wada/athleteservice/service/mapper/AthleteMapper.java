package com.tcd.ds.wada.athleteservice.service.mapper;

import com.tcd.ds.wada.userservice.entity.Athlete;
import com.tcd.ds.wada.userservice.entity.Availability;
import com.tcd.ds.wada.athleteservice.model.AthleteRequest;

import java.util.ArrayList;
import java.util.List;

public class AthleteMapper {
    public Athlete fromAthleteRequestToEntity(AthleteRequest athleteRequest) {
        Athlete athlete = new Athlete();
        athlete.setAthleteEmail(athleteRequest.getEmailId());
        athlete.setAthleteName(athleteRequest.getAthleteName());
        athlete.setLocation(athleteRequest.getHomeLocation());
        return athlete;
    }
}
