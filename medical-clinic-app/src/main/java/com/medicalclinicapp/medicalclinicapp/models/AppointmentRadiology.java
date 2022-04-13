package com.medicalclinicapp.medicalclinicapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medicalclinicapp.medicalclinicapp.security.models.Curant;
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
@Table(name="appointmentRadiology")
public class AppointmentRadiology {
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "curant_cnp", referencedColumnName = "cnp")
    private Curant curant;

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
    private Boolean irm;
}
