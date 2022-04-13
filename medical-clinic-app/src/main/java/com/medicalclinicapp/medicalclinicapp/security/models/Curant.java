package com.medicalclinicapp.medicalclinicapp.security.models;


import com.medicalclinicapp.medicalclinicapp.models.AppointmentHematology;
import com.medicalclinicapp.medicalclinicapp.models.AppointmentRadiology;
import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@Table(name = "curant")
@RequiredArgsConstructor
public class Curant extends User {

    private String role;

    @OneToOne(mappedBy = "curant")
    private Hospitalization hospitalization;


    @OneToOne(mappedBy = "curant")
    private AppointmentHematology appointmentHematology;

    @OneToOne(mappedBy = "curant")
    private AppointmentRadiology appointmentRadiology;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role);
        return Collections.singletonList(authority);
    }
}
