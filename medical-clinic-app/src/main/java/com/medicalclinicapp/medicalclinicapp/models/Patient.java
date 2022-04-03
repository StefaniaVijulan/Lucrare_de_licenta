package com.medicalclinicapp.medicalclinicapp.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="patient")
public class Patient {
    @Id
    private String cnpPatient;
    private String passwordPatient;
    private String dadLetterPatient;
    private String lastNamePatient;
    private String firstNamePatient;
    private String seriesPatient;
    private String numberPatient;
    private String sexPatient;
    //Address
    private String cityPatient;
    private String townPatient;
    private String streetPatient;
    private String noPatient;

    private String placePatient; //rural/urban
    private String citizenshipPatient;
    private String jobPatient;
    private String bloodTypePatient;
    private String rhPatient;
    private String allergyPatient;
    private Boolean insurancePatient;

}