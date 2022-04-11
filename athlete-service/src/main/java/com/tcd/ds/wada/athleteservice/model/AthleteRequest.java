package com.tcd.ds.wada.athleteservice.model;

import com.tcd.ds.wada.userservice.entity.Location;
import lombok.Data;

@Data
public class AthleteRequest {
    private String athleteName;
    private Location homeLocation;
    private String emailId;
}
