package com.tcd.ds.wada.athleteservice.entity;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.*;

@Data
@Entity
@Table(name="Athlete")
public class Athlete {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Integer athleteId;

    @Column(name = "EMAIL")
    private String emailId;

}
