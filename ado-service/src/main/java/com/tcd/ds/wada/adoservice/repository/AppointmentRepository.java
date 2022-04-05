package com.tcd.ds.wada.adoservice.repository;

import com.tcd.ds.wada.adoservice.entity.Appointments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointments, String> {
}
