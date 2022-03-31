package com.tcd.ds.wada.athleteservice.repository;

import com.tcd.ds.wada.athleteservice.entity.Athlete;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteRepository extends CrudRepository<Athlete, Integer> {
}
