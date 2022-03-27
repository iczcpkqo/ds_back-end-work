package com.tcd.ds.wada.userservice.service;

import com.tcd.ds.wada.userservice.entity.User;
import com.tcd.ds.wada.userservice.model.UserLoginRequest;
import com.tcd.ds.wada.userservice.model.UserLoginResponse;
import com.tcd.ds.wada.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserLoginService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserLoginService.class);

    @Autowired
    private UserRepository repository;

    public ResponseEntity<Object> login(UserLoginRequest request) {
    	logger.info("Processing login request");
        //JWTokenHelper helper = new JWTokenHelper();
        //final String token;
        Optional<User> user = repository.findById(request.getEmail());
        if(!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not exist");
        }
        else {
            User ruser = user.get();
            if(!request.getPassword().equals(ruser.getPassword())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect password");
            }
//            token = helper.generateToken(ruser.getUserName());
//            if(token == null || token.isEmpty()) {
//            	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("System cannot process request at this time");
//            }
            UserLoginResponse response = new UserLoginResponse();
            response.setToken(null);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }
}
