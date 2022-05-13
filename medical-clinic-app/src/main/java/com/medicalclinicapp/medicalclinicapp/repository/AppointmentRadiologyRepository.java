package com.medicalclinicapp.medicalclinicapp.repository;

import com.medicalclinicapp.medicalclinicapp.models.AppointmentHematology;
import com.medicalclinicapp.medicalclinicapp.models.AppointmentRadiology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AppointmentRadiologyRepository extends JpaRepository<AppointmentRadiology, Long> {
   
}
