package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import com.medicalclinicapp.medicalclinicapp.models.Patient;
import com.medicalclinicapp.medicalclinicapp.security.models.Cardiolog;
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
    public Hospitalization addHospitalization(@RequestParam(value = "cnpS") String cnpS, @RequestParam(value = "cnpD") String cnpD,@RequestParam(value = "cnpP") String cnpPatient, @RequestBody Hospitalization hospitalization) throws Exception {
        return secretaryService.addHospitalization(cnpS, cnpD,cnpPatient, hospitalization);
    }

    @Transactional
    @PostMapping("/secretary/addPatient")
    public Patient addPatient(@RequestBody Patient patient) throws Exception {
    System.out.println("ana");
        return secretaryService.addPatient(patient);
    }

    @GetMapping("/secretary/editHospitalization")
    public Hospitalization editHosp(@RequestParam("idHospitalization") String id) throws ParseException {

        return secretaryService.editHospitalization(id);
    }

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



    @GetMapping("/secretary/allHospitalization")
    public List<Hospitalization> getAllHospitalization(Principal principal){
        return secretaryService.getAllHospitalization(principal);
    }
    @GetMapping("/secretary/specificP")
    public Patient specificP(@RequestParam("registrationNoHospitalization") String registrationNoHospitalization){
        return secretaryService.getSpecificPatient(registrationNoHospitalization);
    }
    @GetMapping("/secretary/specificD")
    public Cardiolog specificD(@RequestParam("registrationNoHospitalization") String registrationNoHospitalization){
        return secretaryService.getSpecificDoctor(registrationNoHospitalization);
    }
    @GetMapping("/secretary/allCurants")
    public List<User> getAllCurants(){
        return secretaryService.seeAllCurant();
    }


}
