package com.medicalclinicapp.medicalclinicapp.security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
@Setter
@Getter
@Entity
@RequiredArgsConstructor
@Table(name="user")
public class User implements UserDetails {
    /*  @Id
  @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;*/
    @Id
    private String cnp;
    private String firstName;
    private String lastName;
    private String emailUser;
    private String numberUser;
    private String imageUser;
    //cardiology, radiology
    private String specialty;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Hospitalization> hospitalizationList;
    public List<Hospitalization> getHospitalizationList() {
        return hospitalizationList;
    }

    public void setHospitalizationList(List<Hospitalization> hospitalizationList) {
        this.hospitalizationList = hospitalizationList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }
    //We will have username = cnp
    @Override
    public String getUsername() {
        return cnp;
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
        return cnp != null && Objects.equals(cnp, user.cnp);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }



}
