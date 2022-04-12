package com.medicalclinicapp.medicalclinicapp.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "hematology")
public class Hematology extends Consultation{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    Long colesterol_seric_total;
    Long hdl_colesterol;
    Long ldl_colesterol;
    Long trigliceride_serice;
    Long glicemie;
    Long tgo;
    Long tgp;
    Long uree_serica;
    Long creatina_serica;
    Long potasiu_seric;
    Long magneziu_seric;
    Long acid_uric;
    Long calciu_ionic_seric;
    Long calciu_seric_total;
    Long inr_cu_interpretare;
    Long hemoleucograma_completa;
    Long t3;
    Long t4;
    Long tsh;
}
