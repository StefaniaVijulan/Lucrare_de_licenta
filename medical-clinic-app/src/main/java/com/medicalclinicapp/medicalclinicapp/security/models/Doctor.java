package com.medicalclinicapp.medicalclinicapp.security.models;


import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
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
@Table(name = "doctor")
@RequiredArgsConstructor
public class Doctor extends User {

    //cardiology, radiology
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    private String role;

    @OneToOne(mappedBy = "doctor")
    private Hospitalization hospitalization;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role);
        return Collections.singletonList(authority);
    }

}