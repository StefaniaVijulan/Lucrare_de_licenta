package com.medicalclinicapp.medicalclinicapp.models;

import com.medicalclinicapp.medicalclinicapp.security.models.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    //Address
    private String cityPatient;
    private String townPatient;
    private String streetPatient;
    private String noPatient;

    private String placePatient; //rural/urban
    private String citizenshipPatient;
    private String jobPatient;
    private String bloodTypePatient;
    private String rhPatient;
    private String allergyPatient;
    private Boolean insurancePatient;
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role);
        return Collections.singletonList(authority);
    }
}
