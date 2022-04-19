package com.tcd.ds.wada.athleteservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("Athlete")
@AllArgsConstructor
@NoArgsConstructor
@CompoundIndexes({
        @CompoundIndex(name = "region_id", def = "{'location.region' : 1, 'athleteId': 1}")
})
public class Athlete implements Serializable {

    private static final long serialVersionUID = 7156526077883281625L;

    @Id
    private String athleteId;
    private String athleteName;
    private Location location;
    private String athleteEmail;
}
