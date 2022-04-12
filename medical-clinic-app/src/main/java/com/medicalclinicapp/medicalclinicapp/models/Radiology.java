package com.medicalclinicapp.medicalclinicapp.models;


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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String eco;
    String ekg;
    String ct;
    String irm;
}
