package com.tcd.ds.wada.adoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AdoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdoServiceApplication.class, args);
	}

}
