package com.medicalclinicapp.medicalclinicapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="hospitalization")
public class Hospitalization {
    @Id
    private String registrationNoHospitalization;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_cnp", nullable = false)
    User user;

    private Date startDateHospitalization;
    private Date endDateHospitalization;
    private Integer numberOfHospitalization;

}
