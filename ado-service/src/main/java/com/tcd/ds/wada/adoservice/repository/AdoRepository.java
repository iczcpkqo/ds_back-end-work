package com.tcd.ds.wada.adoservice.repository;

import com.tcd.ds.wada.adoservice.entity.Ado;
import com.tcd.ds.wada.adoservice.entity.Athlete;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoRepository extends MongoRepository<Ado, String> {
    List<Athlete> findByLocation(String country);
}
