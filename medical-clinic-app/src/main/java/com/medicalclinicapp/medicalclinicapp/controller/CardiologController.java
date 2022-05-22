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
import java.text.ParseException;
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
    @GetMapping("/cardiolog/allFutureSpecificAppointment")
    public List<Appointment> getFutureSpecificAppointment(@RequestParam(value = "cnpC") String cnpC) throws ParseException {
        return cardiologService.getAllFutureAppointment(cnpC);
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
    @GetMapping("/cardiolog/checkDateBeforeBlock")
    public Boolean verifD(@RequestParam("dataV")String dataV, @RequestParam("cnpC") String cnpC){
        return cardiologService.verficaDateBeforeBlock(dataV, cnpC);
    }
    @DeleteMapping("/appointmentD")
    public String deleteA(){
        return cardiologService.deltete();
    }
    @PostMapping("/cardiolog/addAppointmentHematology")
    public AppointmentHematology addAppointment(@RequestParam (value = "cnpP")String cnpP, @RequestBody AppointmentHematology appointment) throws Exception {
        return cardiologService.addAppointmentHematology(cnpP, appointment);
    }
    @GetMapping("/cardiolog/specificAppointmentHematology")
    public AppointmentHematology specificAppointmentHema(@RequestParam("cnpP")String cnpP) throws ParseException {
        return cardiologService.getSpecificAppointmentHematology(cnpP);
    }
    @GetMapping("/cardiolog/specificAppointmentRadiology")
    public AppointmentRadiology specificAppointmentRadio(@RequestParam("cnpP")String cnpP) throws ParseException {
        return cardiologService.getSpecificAppointmentRadiology(cnpP);
    }
    @PostMapping("/cardiolog/addAppointmentRadiology")
    public AppointmentRadiology addAppointment(@RequestParam (value = "cnpP")String cnpP, @RequestBody AppointmentRadiology appointment) throws Exception {
        return cardiologService.addAppointmentRadiology(cnpP, appointment);
    }


    @PutMapping(("/cardiolog/editAppointment"))
    public Appointment editAppointment(@RequestParam("id")Long id, @RequestBody Appointment appointment)  {
        return cardiologService.editAppointment(id, appointment);
    }
}
