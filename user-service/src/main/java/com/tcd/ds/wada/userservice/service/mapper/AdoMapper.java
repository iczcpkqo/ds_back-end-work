package com.tcd.ds.wada.userservice.service.mapper;

import com.tcd.ds.wada.userservice.entity.Ado;
import com.tcd.ds.wada.userservice.entity.Athlete;
import com.tcd.ds.wada.userservice.model.UserLoginRequest;
import com.tcd.ds.wada.userservice.model.UserRegistrationRequest;

import java.util.ArrayList;

public class AdoMapper {
    public Ado fromRequestToEntity(UserRegistrationRequest request) {
        Ado ado = new Ado();
        ado.setAdoName(request.getName());
        ado.setAthletes(new ArrayList<>());
        ado.setLocation(request.getLocation());
        ado.setAdoEmail(request.getEmail());

        return ado;
    }
}
