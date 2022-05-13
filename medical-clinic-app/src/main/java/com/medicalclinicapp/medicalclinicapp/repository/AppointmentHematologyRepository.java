package com.medicalclinicapp.medicalclinicapp.repository;

import com.medicalclinicapp.medicalclinicapp.models.AppointmentHematology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

public interface AppointmentHematologyRepository extends JpaRepository<AppointmentHematology, Long> {

}