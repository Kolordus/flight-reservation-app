package com.kolak.reservation.jwtUtil.model;

public class AuthentcationResponse {

    private final String jwt;

    public AuthentcationResponse(String jwtToken) {
        this.jwt = jwtToken;
    }

    public String getJwt() {
        return jwt;
    }
}
