package com.tcd.ds.wada.userservice.service;

import com.tcd.ds.wada.userservice.entity.User;
import com.tcd.ds.wada.userservice.model.UserRegistrationRequest;
import com.tcd.ds.wada.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRegistrationService {
	private static final Logger logger = LoggerFactory.getLogger(UserRegistrationService.class);

    @Autowired
    private UserRepository repository;

    public ResponseEntity<Object> register(UserRegistrationRequest request) {
        if(request == null ||
                !request.getEmail().matches("^[a-zA-Z0-9_.+-]+@dublincity\\.ie$")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }
        UserMapper mapper = new UserMapper();
        User user = mapper.fromRegistrationRequestToEntity(request);
        Optional<User> existingUser = repository.findById(request.getEmail());
        if(existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("A user with that email already exists.");
        }
        try {
            repository.save(user);
        }
        catch(Exception e){
          logger.error("Error in UserRegistrationService register method", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
