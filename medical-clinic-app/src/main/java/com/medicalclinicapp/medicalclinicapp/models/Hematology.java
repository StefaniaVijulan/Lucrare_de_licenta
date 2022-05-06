package com.medicalclinicapp.medicalclinicapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medicalclinicapp.medicalclinicapp.security.models.Hematolog;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
/*
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "hematology")*/
public class Hematology extends Consultation{

   /* @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "hematolog_cnp", referencedColumnName = "cnp")
    private Hematolog hematolog;


    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "appointmentHematology_id", referencedColumnName = "id")
    private AppointmentHematology appointmentHematology;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long colesterol_seric_total;
    private Long hdl_colesterol;
    private Long ldl_colesterol;
    private Long trigliceride_serice;
    private Long glicemie;
    private Long tgo;
    private Long tgp;
    private Long uree_serica;
    private Long creatina_serica;
    private Long potasiu_seric;
    private Long magneziu_seric;
    private Long acid_uric;
    private Long calciu_ionic_seric;
    private Long calciu_seric_total;
    private Long inr_cu_interpretare;
    private Long hemoleucograma_completa;
    private Long t3;
    private Long t4;
    private Long tsh;*/
}
