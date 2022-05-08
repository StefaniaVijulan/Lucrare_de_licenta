package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.models.Appointment;
import com.medicalclinicapp.medicalclinicapp.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/blockDate")
    public List<String> verificaDispDate(){
        return appointmentService.verificaDisponibilitate();
    }
    @GetMapping("/checkAvailabilityHour")
    public List<String> verificaDispHour(@RequestParam("date")String data){
        System.out.println("Inta in controller");
        return appointmentService.verificaHours(data);
    }
    @PostMapping("/addAppointment")
    public Appointment addAppointment(@RequestBody Appointment appointment){
        return appointmentService.addAppointment(appointment);
    }

}
