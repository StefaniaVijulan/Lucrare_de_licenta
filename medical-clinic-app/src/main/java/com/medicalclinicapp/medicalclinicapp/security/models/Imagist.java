package com.medicalclinicapp.medicalclinicapp.security.models;

import com.medicalclinicapp.medicalclinicapp.models.Hematology;
import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import com.medicalclinicapp.medicalclinicapp.models.Radiology;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
@Table(name = "imagist")
@RequiredArgsConstructor
public class Imagist extends User {

    @OneToOne(mappedBy = "imagist")
    private Radiology radiology;

    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role);
        return Collections.singletonList(authority);
    }
}