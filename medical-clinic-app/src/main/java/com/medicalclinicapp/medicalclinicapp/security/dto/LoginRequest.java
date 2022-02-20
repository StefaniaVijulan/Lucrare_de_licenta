package com.medicalclinicapp.medicalclinicapp.security.dto;

public class LoginRequest {
    private String cnp;
    private String password;

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
