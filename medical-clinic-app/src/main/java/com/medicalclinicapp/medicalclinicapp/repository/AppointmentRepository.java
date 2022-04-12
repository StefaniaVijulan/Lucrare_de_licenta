package com.medicalclinicapp.medicalclinicapp.repository;

import com.medicalclinicapp.medicalclinicapp.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean findByDataAppointment(Date dataAppointment);

    boolean existsByDataAppointment(Date dataAppointment);

    boolean existsByHourAppointment(Number hourAppointment);
}
