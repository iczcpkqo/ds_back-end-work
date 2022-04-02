package com.tcd.ds.wada.adoservice.entity;

import com.tcd.ds.wada.athleteservice.entity.Athlete;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("Ado")
public class Ado {

    @Id
    private Long adoId;
    private String adoName;
    //private Location location;
    private List<Athlete> athletes;
}
