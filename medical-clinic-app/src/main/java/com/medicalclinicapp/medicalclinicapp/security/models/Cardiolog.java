package com.medicalclinicapp.medicalclinicapp.security.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@Table(name = "cardiolog")
@RequiredArgsConstructor
public class Cardiolog extends User {

    private String role;
/*
    @OneToOne(mappedBy = "cardiolog")
    private AppointmentHematology appointmentHematology;
*/
    /*
    @OneToOne(mappedBy = "cardiolog")
    private AppointmentRadiology appointmentRadiology;
*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role);
        return Collections.singletonList(authority);
    }
}