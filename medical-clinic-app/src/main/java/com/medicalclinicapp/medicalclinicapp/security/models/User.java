package com.medicalclinicapp.medicalclinicapp.security.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
@Setter
@Getter
@RequiredArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User implements UserDetails{
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
