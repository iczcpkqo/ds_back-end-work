package com.tcd.ds.wada.athleteservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("Ado")
public class Ado {
    @Id
    private String adoId;
    private String adoName;
    private String adoEmail;
    private Location location;
    private List<Athlete> athletes;
}
