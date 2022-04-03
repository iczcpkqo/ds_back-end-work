package com.tcd.ds.wada.athleteservice.service.mapper;

import com.tcd.ds.wada.athleteservice.entity.Athlete;
import com.tcd.ds.wada.athleteservice.entity.Availability;
import com.tcd.ds.wada.athleteservice.model.AthleteRequest;
import com.tcd.ds.wada.athleteservice.model.AvailabilityRequest;

import java.util.ArrayList;
import java.util.List;

public class AthleteMapper {
    public Athlete fromAthleteRequestToEntity(AthleteRequest athleteRequest) {
        Athlete athlete = new Athlete();
        athlete.setEmailId(athleteRequest.getEmailId());
        athlete.setAthleteName(athleteRequest.getAthleteName());
        athlete.setHomeLocation(athleteRequest.getHomeLocation());

        List<Availability> availabilityList = new ArrayList<>();
        athlete.setAvailabilities(availabilityList);

        return athlete;
    }
}
