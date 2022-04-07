package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import com.medicalclinicapp.medicalclinicapp.security.repository.UserRepository;
import com.medicalclinicapp.medicalclinicapp.services.HospitalizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class HospitalizationController {
    @Autowired
    private HospitalizationService hospitalizationService;


    @Transactional
    @PostMapping("/hospitalization/addHospitalization")
    public Hospitalization addHospitalization(@RequestBody Hospitalization hospitalization, Principal principal){

        return hospitalizationService.addHospitalization(hospitalization, principal);
    }
    @Transactional
    @PostMapping("/hospitalizationChangeEndData{registrationNoHospitalization}")
    public String changeHopEndDate(@RequestParam(value = "registrationNoHospitalization")String registrationNoHospitalization, @RequestBody Date endDate){
        return hospitalizationService.changeHospitalizationDataEnd(registrationNoHospitalization, endDate);
    }
    @Transactional
    @PostMapping("/hospitalizationChangeNumberOfHospitalization{registrationNoHospitalization}")
    public String changeHopEndDate(@RequestParam(value = "registrationNoHospitalization")String registrationNoHospitalization, @RequestBody Integer NumberOfHospitalization){
        return hospitalizationService.changeHospitalizationNumberOfHospitalization(registrationNoHospitalization, NumberOfHospitalization);
    }

}
