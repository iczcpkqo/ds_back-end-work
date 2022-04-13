package com.tcd.ds.wada.adoservice.repository;
import com.tcd.ds.wada.adoservice.entity.Athlete;
import com.tcd.ds.wada.adoservice.entity.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleteRepository extends MongoRepository<Athlete, String> {
    List<Athlete> findByLocation(Location location);
}