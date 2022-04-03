package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import com.medicalclinicapp.medicalclinicapp.models.Patient;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.repository.UserRepository;
import com.medicalclinicapp.medicalclinicapp.services.HospitalizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class HospitalizationController {

    private final HospitalizationService hospitalizationService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @PostMapping("/addHospitalization")
    public Hospitalization getUserByCnp(@RequestBody Hospitalization hospitalization, Principal principal){

        return hospitalizationService.addHospitalization(hospitalization, principal);
    }

}
