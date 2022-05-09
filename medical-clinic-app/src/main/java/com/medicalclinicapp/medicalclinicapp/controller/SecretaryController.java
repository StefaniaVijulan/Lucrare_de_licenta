package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.models.Appointment;
import com.medicalclinicapp.medicalclinicapp.models.FisaPatient;
import com.medicalclinicapp.medicalclinicapp.models.Patient;
import com.medicalclinicapp.medicalclinicapp.security.models.Cardiolog;
import com.medicalclinicapp.medicalclinicapp.security.services.SecretaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class SecretaryController {
    @Autowired
    private SecretaryService secretaryService;

    @GetMapping("/secretary/allAppointments")
    public List<Appointment> getAllAppointment() throws ParseException {
        return secretaryService.currentAppointments();
    }
    @GetMapping("/secretary/todayAppointments")
    public List<Appointment> getAllTodayAppointment() throws ParseException {
        return secretaryService.todayAppointments();
    }
    @PostMapping("/secretary/addPatient")
    public Patient addPatient(@RequestBody Patient patient) throws Exception {
        System.out.println("ana");
        return secretaryService.addPatient(patient);
    }
    @GetMapping("/secretary/checkPatient")
    public Patient getcheckPatient(@RequestParam("cnp")String cnp) throws ParseException {
        return secretaryService.checkPatient(cnp);
    }
    @DeleteMapping("/secretary/deletePatient")
    public Patient deleteP(@RequestParam("cnpP") String cnpP){
        return secretaryService.deletePatient(cnpP);
    }
    @GetMapping("/secretary/allCardiolog")
    public List<Cardiolog> getAllCardiolog(){
        return secretaryService.seeAllCardiolog();
    }
    @GetMapping("/secretary/specificD")
    public Cardiolog specificD(@RequestParam("cnpP") String cnp){
        return secretaryService.getSpecificCardiologOfPatient(cnp);
    }

    @PostMapping("/secretary/addFisa")
    public FisaPatient addFisa1(@RequestParam("cnpP") String cnpP, @RequestBody FisaPatient fisa){
        return secretaryService.addFisa(cnpP, fisa);
    }
}
/*
    @PostMapping("/secretary/addPatient")
    public Patient addPatient(@RequestBody Patient patient) throws Exception {
        System.out.println("ana");
        return secretaryService.addPatient(patient);
    }

    @PostMapping("/hospitalization/addHospitalization")
    public Hospitalization addHospitalization(@RequestParam(value = "cnpS") String cnpS, @RequestParam(value = "cnpD") String cnpD, @RequestParam(value = "cnpP") String cnpPatient, @RequestBody Hospitalization hospitalization) throws Exception {
        return secretaryService.addHospitalization(cnpS, cnpD, cnpPatient, hospitalization);
    }

    @GetMapping("/secretary/allHospitalizationActive")
    public List<Hospitalization> getAllHospitalizationActive(Principal principal){
        return secretaryService.getAllHospitalizationActive(principal);
    }

    @GetMapping("/secretary/allHospitalization")
    public List<Hospitalization> getAllHospitalization(Principal principal){
        return secretaryService.getAllHospitalization(principal);
    }


    @GetMapping("/secretary/specificHospitalization")
    public Hospitalization getSpecificHospit(@RequestParam("noHosp") String noHosp){
        return secretaryService.getSpecificHospitalization(noHosp);
    }

    @GetMapping("/secretary/editHospitalization")
    public Hospitalization editHosp(@RequestParam("idHospitalization") String id) throws ParseException {

        return secretaryService.editHospitalization(id);
    }
}
 /*



    @GetMapping("/secretary/allPatients")
    public List<Patient> getAllPatient(){
        return secretaryService.allPatient();
    }

    @GetMapping("/secretary/infoPatient")
    public Patient moreInfoP(@RequestParam(value = "cnpP")String cnpP){
        return secretaryService.moreInfo(cnpP);
    }
    @GetMapping("/secretary/infoHospitalization")
    public Hospitalization moreInfoH(@RequestParam(value = "cnpP")String cnpP){
        return secretaryService.moreInfoHospitalization(cnpP);
    }




    @GetMapping("/secretary/specificP")
    public Patient specificP(@RequestParam("registrationNoHospitalization") String registrationNoHospitalization){
        return secretaryService.getSpecificPatient(registrationNoHospitalization);
    }



    @GetMapping("/secretary/afisare")
    public ResponseEntity<?> afisP(@RequestParam String cnpP){
        return  secretaryService.afisareP(cnpP);
    }

}
        */