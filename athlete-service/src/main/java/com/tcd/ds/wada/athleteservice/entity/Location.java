package com.tcd.ds.wada.athleteservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("Location")
public class Location implements Serializable {

    private static final long serialVersionUID = 7156526077883281624L;

    @Id
    private String country;
    private String region;
}