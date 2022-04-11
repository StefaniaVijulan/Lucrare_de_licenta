package com.medicalclinicapp.medicalclinicapp.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medicalclinicapp.medicalclinicapp.security.models.Curant;
import com.medicalclinicapp.medicalclinicapp.security.models.Secretary;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @JoinColumn(name = "secretary_cnp", nullable = false)
    Secretary secretary;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "curant_cnp", referencedColumnName = "cnp")
    private Curant curant;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patient_cnp",referencedColumnName = "cnp")
    Patient patient;

    private Date startDateHospitalization;
    private Date endDateHospitalization;
    private Integer numberOfHospitalization;
}

