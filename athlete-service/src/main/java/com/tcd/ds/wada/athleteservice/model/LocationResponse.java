package com.tcd.ds.wada.athleteservice.model;

import com.tcd.ds.wada.athleteservice.entity.Location;
import lombok.Data;

import java.util.List;

@Data
public class LocationResponse {
    private List<Location> locations;
}
