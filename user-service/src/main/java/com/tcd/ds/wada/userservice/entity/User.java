package com.tcd.ds.wada.userservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Data
@Entity
@Table(name="Users")
public class User {
	@Column(name = "USERNAME")
	private String userName;

	@Id
	@Column(name = "EMAIL")
	private String userEmail;

	@Column(name = "PASSWORD")
	private String password;
}
