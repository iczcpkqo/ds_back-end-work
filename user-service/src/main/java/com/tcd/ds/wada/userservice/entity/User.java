package com.tcd.ds.wada.userservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("User")
public class User {
	@Id
	private String userEmail;
	private String userName;
	private String password;
}
