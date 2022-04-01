package com.tcd.ds.wada.athleteservice.entity;

import lombok.Data;

import javax.persistence.*;

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

    @Column(name = "START_TIMESTAMP")
    private Long startTimeStamp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LOCATION_ID", referencedColumnName = "ID")
    private Location location;
}
