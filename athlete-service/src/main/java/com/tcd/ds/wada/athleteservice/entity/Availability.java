package com.tcd.ds.wada.athleteservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Availability")
@AllArgsConstructor
@NoArgsConstructor
@CompoundIndexes({
        @CompoundIndex(name = "availability_region_id", def = "{'athlete.location.region' : 1, 'availabilityId': 1}")
})
public class Availability {
    @Id
    private String availabilityId;
    private Athlete athlete;
    private Long startTimeStamp;
    private Location location;
    private Boolean isAppointment;
}