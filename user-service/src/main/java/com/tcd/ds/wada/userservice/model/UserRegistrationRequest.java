package com.tcd.ds.wada.userservice.model;

import com.tcd.ds.wada.userservice.entity.Location;
import lombok.Data;

@Data
public class UserRegistrationRequest {
	private String name;
	private String email;
	private String password;
	private Location location;
	private Boolean isAthlete;
}
