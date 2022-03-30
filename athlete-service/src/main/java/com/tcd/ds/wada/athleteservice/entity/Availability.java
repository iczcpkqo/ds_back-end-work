package com.tcd.ds.wada.athleteservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Data
@Entity
@Table(name="AthleteAvailability")
public class Availability {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private String availabilityId;

    @Column(name = "ATHLETE_ID")
    private String athleteId;

    @Column(name = "TIMESTAMP")
    private Long timeStamp;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "IS_APPOINTMENT")
    private Boolean isAppointment;
}
