package com.tcd.ds.wada.athleteservice.repository;

import com.tcd.ds.wada.athleteservice.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AvailabilityRepository extends CrudRepository<Availability, String> {
    Optional<List<Availability>> findByAthleteId(String athleteId);
    Optional<List<Availability>> findByAthleteIdAndIsAppointment(String athleteId, Boolean isAppointment);
}
