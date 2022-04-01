package com.tcd.ds.wada.athleteservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="Location")
public class Location {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer locationId;

    @Column(name = "COUNTRY")
    private String country;

    @OneToMany(mappedBy = "location")
    private List<Athlete> athletes;
}
