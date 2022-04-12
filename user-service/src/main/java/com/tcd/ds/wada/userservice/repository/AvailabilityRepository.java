package com.tcd.ds.wada.userservice.repository;

import com.tcd.ds.wada.userservice.entity.Athlete;
import com.tcd.ds.wada.userservice.entity.Availability;
import com.tcd.ds.wada.userservice.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, String> {
    List<Availability> findByLocation(Location location);
}
