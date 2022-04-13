package com.medicalclinicapp.medicalclinicapp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medicalclinicapp.medicalclinicapp.security.models.Hematolog;
import com.medicalclinicapp.medicalclinicapp.security.models.Imagist;
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
@Table(name = "radiology")
public class Radiology extends Consultation{

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "imagist_cnp", referencedColumnName = "cnp")
    private Imagist imagist;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "appointmentRadiology_idp", referencedColumnName = "id")
    private AppointmentRadiology appointmentRadiology;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eco;
    private String ekg;
    private String ct;
    private String irm;
}
