package com.tcd.ds.wada.adoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Sharded;

import java.io.Serializable;

@Data
@Document("Availability")
@AllArgsConstructor
@NoArgsConstructor
@CompoundIndexes({
        @CompoundIndex(name = "region_id", def = "{'athlete.location.region' : 1, 'availabilityId': 1}")
})
@Sharded(shardKey = { "athlete.location.region", "availabilityId" })
public class Availability implements Serializable {

    private static final long serialVersionUID = 7156526077883281626L;

    @Id
    private String availabilityId;
    private Athlete athlete;
    private Long startTimeStamp;
    private Location location;
    private Boolean isAppointment;
}