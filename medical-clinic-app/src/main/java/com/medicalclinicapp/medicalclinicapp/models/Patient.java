package com.medicalclinicapp.medicalclinicapp.models;

import com.medicalclinicapp.medicalclinicapp.security.models.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "patient")
@RequiredArgsConstructor
public class Patient extends User {
    private String dadLetterPatient;
    private String seriesPatient;
    private String numberPatient;
    private String sexPatient;
    private String citizenshipPatient;
    //Address
    private String cityPatient;
    private String townPatient;
    private String streetPatient;
    private String noPatient;
    private String placePatient;

    @Column(length = 65555)
    private String jobTypePatient;
    @Column(length = 65555)
    private String insurancePatient;
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role);
        return Collections.singletonList(authority);
    }
}
