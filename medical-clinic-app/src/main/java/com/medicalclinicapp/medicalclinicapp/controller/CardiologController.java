package com.medicalclinicapp.medicalclinicapp.controller;


import com.medicalclinicapp.medicalclinicapp.models.Appointment;
import com.medicalclinicapp.medicalclinicapp.models.AppointmentHematology;
import com.medicalclinicapp.medicalclinicapp.models.AppointmentRadiology;

import com.medicalclinicapp.medicalclinicapp.models.FisaPatient;
import com.medicalclinicapp.medicalclinicapp.security.models.Cardiolog;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.services.CardiologService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CardiologController {

    @Autowired
    private CardiologService cardiologService;


    @GetMapping("/cardiolog/allSpecificAppointment")
    public List<Appointment> getSpecificAppointment(@RequestParam(value = "cnpC") String cnpC){
        return cardiologService.getAllSpecificAppointment(cnpC);
    }
    @GetMapping("/cardiolog/allTodaySpecificAppointment")
    public List<Appointment> getTodaySpecificAppointment(@RequestParam(value = "cnpC") String cnpC){
        return cardiologService.todaySpecificAppointment(cnpC);
    }

    @GetMapping("/cardiolog/PatientAppointmentRadiology")
    public AppointmentRadiology AppointmentRadiology(@RequestParam(value = "cnpP") String cnpP){
        return cardiologService.getPatientAppointmentsRadiology(cnpP);
    }
    @GetMapping("/cardiolog/PatientAppointmentHematology")
    public AppointmentHematology AppointmentHematology (@RequestParam(value = "cnpP") String cnpP){
        return cardiologService.getPatientAppointmentsHematology(cnpP);
    }

    @PutMapping(path = "/cardiolog/editFisaP")
    public FisaPatient editUser(@RequestParam(value = "cnpP")  String cnpP, @RequestBody FisaPatient fisaPatient) throws IOException {
        return cardiologService.editFisaPatient(cnpP, fisaPatient);

    }
    @DeleteMapping(path="/deleteFisa")
    public FisaPatient delete(@RequestParam(value = "blood") Long blood){
        return cardiologService.delaleteFisa(blood);
    }
    @GetMapping("/cardiolog/specificFisa")
    public FisaPatient getSpecificF (@RequestParam("cnpP") String cnpP){
        return cardiologService.getInfoFisa(cnpP);
    }
    @GetMapping("/blockDateHematology")
    public List<String> verificaDisp(){
        return cardiologService.verificaDisponibilitateHematology();
    }
    @GetMapping("/checkAvailabilityHematology")
    public List<String> verificaDispHour(@RequestParam("dateA")String data){
        System.out.println("Inta in controller");
        return cardiologService.verificaHoursHematology(data);
    }

    @GetMapping("/blockDateRadiology")
    public List<String> verificaDispRad(){
        return cardiologService.verificaDisponibilitateRadiology();
    }
    @GetMapping("/checkAvailabilityRadiology")
    public List<String> verificaDispHourRad(@RequestParam("dateA")String data){
        System.out.println("Inta in controller");
        return cardiologService.verificaHoursRadiology(data);
    }

    @DeleteMapping("/appointmentD")
    public String deleteA(){
        return cardiologService.deltete();
    }
    @PostMapping("/cardiolog/addAppointmentHematology")
    public AppointmentHematology addAppointment(@RequestParam (value = "cnpP")String cnpP, @RequestBody AppointmentHematology appointment) throws Exception {
        return cardiologService.addAppointmentHematology(cnpP, appointment);
    }
    @PostMapping("/cardiolog/addAppointmentRadiology")
    public AppointmentRadiology addAppointment(@RequestParam (value = "cnpP")String cnpP, @RequestBody AppointmentRadiology appointment) throws Exception {
        return cardiologService.addAppointmentRadiology(cnpP, appointment);
    }
}

/*
    @PostMapping("/cardiolog/addAppointmentHematology")
    public AppointmentHematology addAppointmentHematology(@RequestBody AppointmentHematology appointment, Principal principal) throws Exception {

        return cardiologService.addAppointmentHematology(appointment, principal);
    }
    @PostMapping("/cardiolog/addAppointmentRadiology")
    public AppointmentRadiology addAppointmentRadiology(@RequestBody AppointmentRadiology appointment, Principal principal) throws Exception {

        return cardiologService.addAppointmentRadiology(appointment, principal);
    }

}
*/