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
    private String startDateHospitalization;
    private String endDateHospitalization;
    private Integer numberOfHospitalization;

    private String typeHospitalization;

    @ManyToOne
    @JoinColumn(name = "secretary_cnp")
    private Secretary secretary;

    @ManyToOne
    @JoinColumn(name = "cardiolog_cnp")
    private Cardiolog cardiolog;

    @ManyToOne
    @JoinColumn(name = "patient_cnp")
    private Patient patient;


}

