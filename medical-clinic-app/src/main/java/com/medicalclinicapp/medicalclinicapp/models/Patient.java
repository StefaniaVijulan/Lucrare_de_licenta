package com.medicalclinicapp.medicalclinicapp.models;

import com.medicalclinicapp.medicalclinicapp.security.models.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "patient")
@RequiredArgsConstructor
public class Patient implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String cnp;
    @Column(name = "password")
    private String password;

    private String firstName;
    private String lastName;
    private String emailUser;
    private String numberUser;
    private String imageUser;

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

    //We will have username = cnp
    @Override
    public String getUsername() {
        return getCnp();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getCnp() != null && Objects.equals(getCnp(), user.getCnp());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
