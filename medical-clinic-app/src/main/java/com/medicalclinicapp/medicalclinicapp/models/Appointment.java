package com.medicalclinicapp.medicalclinicapp.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@Table(name = "appointment")
@RequiredArgsConstructor
public class Appointment implements Comparable<Appointment> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cnp;
    private String firstName;
    private String lastName;
    private String emailUser;
    private String numberUser;
    private String dataA;
    private String hour;

    @Override
    public int compareTo(Appointment o) {
        return this.getDataA().compareTo(o.getDataA());
    }
}


