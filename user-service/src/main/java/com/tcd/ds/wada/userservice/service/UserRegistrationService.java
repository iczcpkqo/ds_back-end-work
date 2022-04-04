package com.tcd.ds.wada.userservice.service;

import com.tcd.ds.wada.userservice.entity.Ado;
import com.tcd.ds.wada.userservice.entity.Athlete;
import com.tcd.ds.wada.userservice.entity.Availability;
import com.tcd.ds.wada.userservice.entity.User;
import com.tcd.ds.wada.userservice.model.UserRegistrationRequest;
import com.tcd.ds.wada.userservice.repository.AdoRepository;
import com.tcd.ds.wada.userservice.repository.AthleteRepository;
import com.tcd.ds.wada.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserRegistrationService {
	private static final Logger logger = LoggerFactory.getLogger(UserRegistrationService.class);

    @Autowired
    private AdoRepository adoRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Object> register(UserRegistrationRequest request) {
        logger.info("Registration: Processing");

        if(request == null || !request.getEmail().matches("^[a-zA-Z0-9_.+-]+@wada\\.com$")) {
            logger.warn("Registration: Email Mismatch");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration: Invalid request");
        }

        UserMapper mapper = new UserMapper();
        User user = mapper.fromRegistrationRequestToEntity(request);

        Optional<User> existingUser = userRepository.findById(request.getEmail());
        if(existingUser.isPresent()) {
            logger.warn("Registration: Email already exists");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Registration: A user with that email already exists.");
        }

        try {
            userRepository.save(user);
            if(user.getIsAthlete()){
                Athlete athlete = new Athlete();
                athlete.setAthleteName(user.getName());
                athlete.setAvailabilities(new ArrayList<Availability>());
                athlete.setEmailId(user.getUserEmail());
                athlete.setHomeLocation(request.getLocation());
                athleteRepository.save(athlete);
            }
            else{
                Ado ado = new Ado();
                ado.setAdoName(user.getName());
                ado.setAthletes(new ArrayList<Athlete>());
                ado.setLocation(request.getLocation());
                ado.setAdoEmail(user.getUserEmail());
                adoRepository.save(ado);
            }
            logger.info("Registration: Complete");
        }
        catch(Exception e){
            logger.error("Registration: Error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
