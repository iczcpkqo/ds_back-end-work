package com.tcd.ds.wada.athleteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AthleteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AthleteServiceApplication.class, args);
	}

}
