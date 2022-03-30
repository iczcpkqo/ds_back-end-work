package com.tcd.ds.wada.athleteservice.controller.appointment;

import com.tcd.ds.wada.athleteservice.entity.Availability;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AppointmentController {

    String APPOINTMENT_BASE_URL = "/athlete/appointment";

    @GetMapping(path = APPOINTMENT_BASE_URL + "/get/{athleteId}")
    ResponseEntity<List<Availability>> getAppointment(@PathVariable(value = "athleteId") final String athleteId);
}
