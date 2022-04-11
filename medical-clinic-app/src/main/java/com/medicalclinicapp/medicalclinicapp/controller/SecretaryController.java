package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import com.medicalclinicapp.medicalclinicapp.models.Patient;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.services.SecretaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class SecretaryController {
    @Autowired
    private SecretaryService secretaryService;

    @Transactional
    @PostMapping("/hospitalization/addHospitalization")
    public Hospitalization addHospitalization(@RequestParam String cnp,@RequestParam String cnpPatient, @RequestBody Hospitalization hospitalization, Principal principal) throws Exception {

        return secretaryService.addHospitalization(cnp,cnpPatient, hospitalization, principal);
    }

    @Transactional
    @PostMapping("/secretary/addPatient")
    public Patient addPatient(@RequestBody Patient patient) throws Exception {
    System.out.println("ana");
        return secretaryService.addPatient(patient);
    }


    @PostMapping(path = "/hospitalizationChangeEndData{registrationNoHospitalization}")
    public String changeHopEndDate(@RequestParam(value = "registrationNoHospitalization")String registrationNoHospitalization, @RequestBody String endDate) throws ParseException {
        System.out.println("ana are mere");
        System.out.println( endDate);
        System.out.println();
        return "da";
        // return hospitalizationService.changeHospitalizationDataEnd(registrationNoHospitalization, endDate);
    }
    @Transactional
    @PostMapping("/hospitalizationChangeNumberOfHospitalization{registrationNoHospitalization}")
    public String numberOfH(@RequestParam(value = "registrationNoHospitalization")String registrationNoHospitalization, @RequestBody Integer NumberOfHospitalization){
        return secretaryService.changeHospitalizationNumberOfHospitalization(registrationNoHospitalization, NumberOfHospitalization);
    }

    @GetMapping("/secretary/allHospitalization")
    public List<Hospitalization> getAllHospitalization(Principal principal){
        return secretaryService.getAllHospitalization(principal);
    }
    @GetMapping("/secretary/allCurants")
    public List<User> getAllCurants(){
        return secretaryService.seeAllCurant();
    }

}
