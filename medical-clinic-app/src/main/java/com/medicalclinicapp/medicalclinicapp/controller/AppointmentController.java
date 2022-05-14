package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.models.Appointment;
import com.medicalclinicapp.medicalclinicapp.models.Patient;
import com.medicalclinicapp.medicalclinicapp.security.models.Cardiolog;
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

    @GetMapping("/allCardiolog")
    public List<Cardiolog> getAllCardiolog(){
        return appointmentService.seeAllCardiolog();
    }

    @GetMapping("/infoPatient")
    public Patient getPatient(@RequestParam (value = "cnpP") String cnpP){
        return appointmentService.getSpecificPatient(cnpP);
    }

    @GetMapping("/blockDateForCardio")
    public List<String> verificaDispDateCardio(@RequestParam (value = "cnpC") String cnpC){
        return appointmentService.verificaDisponibilitateDoctor(cnpC);
    }
    @GetMapping("/checkAvailabilityHourCardio")
    public List<String> verificaDispHourCardio(@RequestParam(value = "cnpC") String cnpC,@RequestParam("date")String data){
        System.out.println("Inta in controller");
        return appointmentService.verificaHoursDoctor(cnpC, data);
    }
    @PostMapping("/addAppointment")
    public Appointment addAppointment(@RequestParam (value = "cnpC")String cnpC, @RequestBody Appointment appointment){
        return appointmentService.addAppointment(cnpC, appointment);
    }
    @DeleteMapping("/deleteAppointment")
    public Appointment deleteAppointment(@RequestParam(value = "idA")Long idA){
        return appointmentService.deleteAppointment(idA);
    }


}
