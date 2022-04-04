package com.tcd.ds.wada.athleteservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.List;

@Data
@Document("Athlete")
@AllArgsConstructor
@NoArgsConstructor
@CompoundIndexes({
        @CompoundIndex(name = "region_id", def = "{'region' : 1, 'athleteid': 1}")
})
public class Athlete {
    @Id
    private String athleteId;
    private String athleteName;
    private Location homeLocation;

    @Indexed(unique = true)
    private String emailId;
    private List<Availability> availabilities;
}
