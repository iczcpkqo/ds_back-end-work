package com.tcd.ds.wada.athleteservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tcd.ds.wada.athleteservice.entity.Athlete;

@Repository
public interface AthleteRepository extends MongoRepository<Athlete,String> {

}
