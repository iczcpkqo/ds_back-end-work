package com.tcd.ds.wada.adoservice.entity;

import com.tcd.ds.wada.athleteservice.entity.Athlete;
import com.tcd.ds.wada.athleteservice.entity.Location;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Appointments")
public class Appointments {

    @Id
    private Integer appointmentId;
    private Athlete athlete;
    private Location location;
}
