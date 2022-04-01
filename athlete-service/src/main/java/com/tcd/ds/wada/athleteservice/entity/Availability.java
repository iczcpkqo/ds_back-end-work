package com.tcd.ds.wada.athleteservice.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Data
@Entity
@Table(name="Availability")
public class Availability {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer availabilityId;

    @Column(name = "ATHLETE_ID")
    private Integer athleteId;

    @Column(name = "TIMESTAMP")
    private Long startTimeStamp;

    @Column(name = "LOCATION")
    private String location;
}
