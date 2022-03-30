package com.tcd.ds.wada.athleteservice.controller.appointment;

import com.tcd.ds.wada.athleteservice.entity.Availability;
import com.tcd.ds.wada.athleteservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AppointmentControllerImpl implements AppointmentController {

    @Autowired
    AppointmentService service;

    @Override
    public ResponseEntity<List<Availability>> getAppointment(String athleteId) {
        return service.get(athleteId);
    }
}
