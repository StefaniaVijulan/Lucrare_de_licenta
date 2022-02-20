package com.medicalclinicapp.medicalclinicapp.security.dto;

public class LoginResponse {
    private String cnp;
    private String accessToken;

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}