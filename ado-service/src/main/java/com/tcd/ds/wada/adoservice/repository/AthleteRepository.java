package com.tcd.ds.wada.adoservice.repository;
import com.tcd.ds.wada.adoservice.entity.Athlete;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteRepository extends MongoRepository<Athlete, String> {
}