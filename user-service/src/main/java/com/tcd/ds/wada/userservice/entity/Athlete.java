package com.tcd.ds.wada.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Data
@Document("Athlete")
@AllArgsConstructor
@NoArgsConstructor
@CompoundIndexes({
        @CompoundIndex(name = "region_id", def = "{'location.region' : 1, 'athleteid': 1}")
})
public class Athlete {
    @Id
    private String athleteId;
    private String athleteName;
    private Location location;
    private String athleteEmail;
}
