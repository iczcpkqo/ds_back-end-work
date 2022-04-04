package com.tcd.ds.wada.userservice.repository;

import com.tcd.ds.wada.userservice.entity.Ado;
import com.tcd.ds.wada.userservice.entity.Athlete;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoRepository extends MongoRepository<Ado, Integer> {
    List<Athlete> findByLocation(Integer locationId);
}
