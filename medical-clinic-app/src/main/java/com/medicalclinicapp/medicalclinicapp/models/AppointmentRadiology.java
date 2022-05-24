package com.medicalclinicapp.medicalclinicapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medicalclinicapp.medicalclinicapp.security.models.Cardiolog;
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
public class AppointmentRadiology  implements Comparable<AppointmentRadiology>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataAppointmentRadiology;
    private String hourAppointmentRadiology;

    private Boolean eco;
    private Boolean ekg;
    private Boolean ct;
    private Boolean irm;


    private Boolean done;
    @OneToOne
    private Patient patient;

    @Override
    public int compareTo(AppointmentRadiology o) {
        return this.getHourAppointmentRadiology().compareTo(o.getDataAppointmentRadiology());
    }
}
