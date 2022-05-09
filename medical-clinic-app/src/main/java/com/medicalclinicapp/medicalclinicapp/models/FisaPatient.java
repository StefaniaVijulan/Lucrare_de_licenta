package com.medicalclinicapp.medicalclinicapp.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "fisaPatient")
@RequiredArgsConstructor
public class FisaPatient {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long noFile;

    private String bloodTypePatient;
    private String rhPatient;
    private String allergyPatient;

    //ANAMNEZA

    private String familyHistory;
    private String personalHistory; // ce boli a mai avut, nasteri etc
    private String lifeAndWorkConditional; //daca lucreaza rural sau traieste in zona toxica
    private String behavior;
    private String pillsHistory;

    //EXAMEN OBIECTIV
    private String generalCondition;
    private String waist;
    private String weight;


    //doctorul de familie
    private String presumptiveDiagnostic;

    @OneToOne
    private Patient patient;
}
