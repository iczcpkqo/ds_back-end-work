package com.tcd.ds.wada.userservice.model;

import lombok.Data;

@Data
public class UserLoginRequest {
	
	private String email;
	private String password;
}
