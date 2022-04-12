package com.tcd.ds.wada.adoservice.entity;

import com.tcd.ds.wada.userservice.entity.Ado;
import com.tcd.ds.wada.userservice.entity.Athlete;
import com.tcd.ds.wada.userservice.entity.Availability;
import com.tcd.ds.wada.userservice.entity.Location;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Appointments")
public class Appointments {

    @Id
    private String appointmentId;
    private Athlete athlete;
    private Availability appointmentDetails;
    private String bookedBy;
}
