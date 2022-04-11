package com.tcd.ds.wada.userservice.service.mapper;

import com.tcd.ds.wada.userservice.entity.Athlete;
import com.tcd.ds.wada.userservice.entity.Availability;
import com.tcd.ds.wada.userservice.model.UserRegistrationRequest;

import java.util.ArrayList;
import java.util.List;

public class AthleteMapper {
    public Athlete fromRequestToEntity(UserRegistrationRequest request) {
        Athlete athlete = new Athlete();
        athlete.setAthleteName(request.getName());
        athlete.setAvailabilities(new ArrayList<>());
        athlete.setAthleteEmail(request.getEmail());
        athlete.setLocation(request.getLocation());

        return athlete;
    }

}
