package com.medicalclinicapp.medicalclinicapp.security.models;

import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "secretary")
@RequiredArgsConstructor
public class Secretary extends User {
    private String role;


    @OneToMany(mappedBy = "secretary")
    private List<Hospitalization> hospitalizationList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role);
        return Collections.singletonList(authority);
    }
}
