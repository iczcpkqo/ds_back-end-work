package com.tcd.ds.wada.athleteservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name="ATHLETE")
@Data
@Document("Athlete")
@AllArgsConstructor
@NoArgsConstructor
@CompoundIndexes({
    @CompoundIndex(name = "region_id", def = "{'region' : 1, 'athleteid': 1}")
})
public class Athlete {
	@Id
	private String id;
	
	private String name;
	
	private Integer athleteid;
	
	private String region;

}
