package com.tcd.ds.wada.athleteservice.repository;

import com.tcd.ds.wada.userservice.entity.Availability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, String> {
}
