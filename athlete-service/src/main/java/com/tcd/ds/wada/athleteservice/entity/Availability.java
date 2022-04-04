package com.tcd.ds.wada.athleteservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Availability")
public class Availability {
    @Id
    private String availabilityId;
    private String athleteId;
    private Long startTimeStamp;
    private Location location;
}
