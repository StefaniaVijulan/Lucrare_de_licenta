package com.medicalclinicapp.medicalclinicapp.models;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Fisa {

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

    private Patient patient;
}
