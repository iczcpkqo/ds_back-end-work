package com.tcd.ds.wada.adoservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Location")
public class Location {
    @Id
    private String country;
    private String region;
}