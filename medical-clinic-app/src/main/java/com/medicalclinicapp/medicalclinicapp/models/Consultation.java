package com.medicalclinicapp.medicalclinicapp.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@Setter
@Getter
@RequiredArgsConstructor
@MappedSuperclass
public abstract class Consultation {
}
