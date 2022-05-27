package com.medicalclinicapp.medicalclinicapp.models;

import com.medicalclinicapp.medicalclinicapp.security.models.Hematolog;
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
@Table(name="resultHematology")
public class HematologyResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Float colesterol_seric_total;
    private Float hdl_colesterol;
    private Float ldl_colesterol;
    private Float trigliceride_serice;
    private Float glicemie;
    private Float tgo;
    private Float tgp;
    private Float uree_serica;
    private Float creatina_serica;
    private Float potasiu_seric;
    private Float magneziu_seric;
    private Float acid_uric;
    private Float calciu_ionic_seric;
    private Float calciu_seric_total;
    private Float globule_rosii;
    private Float hemoglobina;
    private Float hematocrit;
    private Float globule_albe;
    private Float trombocite;
    private Float t3;
    private Float t4;
    private Float tsh;

    private String interpretare;
    private Boolean done;

    @OneToOne
    private Hematolog hematolog;

    @OneToOne
    private AppointmentHematology appointmentHematology;
}
