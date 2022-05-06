package com.medicalclinicapp.medicalclinicapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medicalclinicapp.medicalclinicapp.security.models.Cardiolog;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
/*
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="appointmentRadiology")*/
public class AppointmentRadiology {
   /* @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "cardiolog_cnp", referencedColumnName = "cnp")
    private Cardiolog cardiolog;

    @OneToOne(mappedBy = "appointmentRadiology")
    private Radiology radiology;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dataAppointment;
    private Number hourAppointment;
    private Number minAppointment;

    private Boolean eco;
    private Boolean ekg;
    private Boolean ct;
    private Boolean irm;*/
}
