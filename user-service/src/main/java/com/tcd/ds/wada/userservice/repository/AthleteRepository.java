package com.tcd.ds.wada.userservice.repository;
import com.tcd.ds.wada.userservice.entity.Athlete;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteRepository extends MongoRepository<Athlete, String> {
}