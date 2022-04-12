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
@Table(name="appointment")
public class Appointment {
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "curant_cnp", referencedColumnName = "cnp")
    private Curant curant;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Analize de sange
    private Date dataAppointment;
    private Number hourAppointment;
    private Number minAppointment;
    private Boolean colesterol_seric_total;
    private Boolean hdl_colesterol;
    private Boolean ldl_colesterol;
    private Boolean trigliceride_serice;
    private Boolean glicemie;
    private Boolean tgo;
    private Boolean tgp;
    private Boolean uree_serica;
    private Boolean creatina_serica;
    private Boolean potasiu_seric;
    private Boolean magneziu_seric;
    private Boolean acid_uric;
    private Boolean calciu_ionic_seric;
    private Boolean calciu_seric_total;
    private Boolean inr_cu_interpretare;
    private Boolean hemoleucograma_completa;
    private Boolean t3;
    private Boolean t4;
    private Boolean tsh;
    //Imagistica
    private Boolean eco;
    private Boolean ekg;
    private Boolean ct;
    private Boolean irm;
}
