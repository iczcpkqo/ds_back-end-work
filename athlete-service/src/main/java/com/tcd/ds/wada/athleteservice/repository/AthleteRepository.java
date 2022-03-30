package com.tcd.ds.wada.athleteservice.repository;

import com.tcd.ds.wada.athleteservice.entity.Athlete;
import org.springframework.data.repository.CrudRepository;

public interface AthleteRepository extends CrudRepository<Athlete, String> {
}
