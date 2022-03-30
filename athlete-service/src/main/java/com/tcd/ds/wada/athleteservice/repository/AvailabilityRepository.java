package com.tcd.ds.wada.athleteservice.repository;

import com.tcd.ds.wada.athleteservice.entity.Availability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, Integer> {
    Optional<List<Availability>> findByAthleteId(Integer athleteId);
}
