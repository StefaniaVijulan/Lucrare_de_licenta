package com.medicalclinicapp.medicalclinicapp.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medicalclinicapp.medicalclinicapp.security.models.Cardiolog;
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
    @JoinColumn(name = "cardiolog_cnp", referencedColumnName = "cnp")
    private Cardiolog cardiolog;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patient_cnp",referencedColumnName = "cnp")
    Patient patient;

    private String startDateHospitalization;
    private String endDateHospitalization;
    private Integer numberOfHospitalization;
}

