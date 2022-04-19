package com.tcd.ds.wada.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("User")
@AllArgsConstructor
@NoArgsConstructor
@CompoundIndexes({
		@CompoundIndex(name = "user_region_id", def = "{'location.region' : 1, 'userEmail': 1}")
})
public class User {
	@Id
	private String userEmail;
	private String name;
	private String password;
	private Boolean isAthlete;
	private Location location;
}
