package com.tcd.ds.wada.athleteservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Data
@Entity
@Table(name="Athlete")
public class Athlete {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private String athleteId;

}
