package com.tcd.ds.wada.userservice.model.location;

import com.tcd.ds.wada.userservice.entity.Location;
import lombok.Data;

import java.util.List;

@Data
public class LocationResponse {
    private List<Location> locations;
}
