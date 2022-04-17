package com.tcd.ds.wada.userservice.model;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private Boolean isAthlete;
    private String id;
}
