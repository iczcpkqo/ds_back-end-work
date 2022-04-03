package com.tcd.ds.wada.userservice.service;

import com.tcd.ds.wada.userservice.entity.User;
import com.tcd.ds.wada.userservice.model.UserLoginRequest;
import com.tcd.ds.wada.userservice.repository.UserRepository;
import com.tcd.ds.wada.userservice.utils.JWTokenHelper;
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

    public ResponseEntity<String> login(UserLoginRequest request) {
    	logger.info("Login: Processing");

        Optional<User> user = repository.findById(request.getEmail());
        if (user.isPresent()) {
            User currentUser = user.get();
            if (request.getPassword().equals(currentUser.getPassword())) {
                JWTokenHelper helper = new JWTokenHelper();
                final String token = helper.generateToken(currentUser.getUserName());
                if (token == null || token.isEmpty())
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                logger.info("Login: Token Generated");
                return ResponseEntity.ok(token);
            } else
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
