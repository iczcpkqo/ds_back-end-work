package com.tcd.ds.wada.adoservice.repository;

import com.tcd.ds.wada.adoservice.entity.Athlete;
import com.tcd.ds.wada.adoservice.entity.Availability;
import com.tcd.ds.wada.adoservice.entity.Location;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, String> {
    List<Availability> findByLocationAndIsAppointment(Location location, Boolean isAppointment);

    @Query(value = "{ '$or':[ {'athlete.location':?0}, {'location':?0} ], 'isAppointment' : ?1 }")
    List<Availability> findByAthleteLocationAndIsAppointment(Location location, Boolean isAppointment);

}
