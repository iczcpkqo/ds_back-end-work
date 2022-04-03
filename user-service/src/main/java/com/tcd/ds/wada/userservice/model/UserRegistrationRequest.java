package com.tcd.ds.wada.userservice.model;

import lombok.Data;

@Data
public class UserRegistrationRequest {
	private String username;
	private String email;
	private String password;
	private Boolean isAthlete;
}
