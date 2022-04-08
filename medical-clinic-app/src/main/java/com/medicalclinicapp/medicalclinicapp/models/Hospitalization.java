package com.medicalclinicapp.medicalclinicapp.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medicalclinicapp.medicalclinicapp.security.models.Doctor;
import com.medicalclinicapp.medicalclinicapp.security.models.Secretary;
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
    @JoinColumn(name = "secretary_cnp", nullable = false)
    Secretary secretary;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "doctor_cnp", referencedColumnName = "cnp")
    private Doctor doctor;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patient_cnp",referencedColumnName = "cnp")
    Patient patient;

    private Date startDateHospitalization;
    private Date endDateHospitalization;
    private Integer numberOfHospitalization;
}

