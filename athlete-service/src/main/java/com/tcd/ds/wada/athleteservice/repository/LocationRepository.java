package com.tcd.ds.wada.athleteservice.repository;

import com.tcd.ds.wada.userservice.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, String> {
}
