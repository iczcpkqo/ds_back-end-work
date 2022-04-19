package com.tcd.ds.wada.adoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("Ado")
@AllArgsConstructor
@NoArgsConstructor
@CompoundIndexes({
        @CompoundIndex(name = "ado_region_id", def = "{'location.region' : 1, 'adoId': 1}")
})
public class Ado {
    @Id
    private String adoId;
    private String adoName;
    private String adoEmail;
    private Location location;
    private List<Athlete> athletes;
}
