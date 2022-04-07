package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.security.models.Doctor;
import com.medicalclinicapp.medicalclinicapp.security.models.Moderator;
import com.medicalclinicapp.medicalclinicapp.security.models.Secretary;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.services.ModeratorService;
import com.medicalclinicapp.medicalclinicapp.security.services.SecretaryService;
import com.medicalclinicapp.medicalclinicapp.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ModeratorController {
    @Autowired
    private ModeratorService moderatorService;

    // add user - that can be used just if the user is moderator
    @PostMapping(path = "/registerModerator")
    public String registerModerator(@RequestBody Moderator moderator) throws IOException {
        System.out.println(moderator);
        return moderatorService.registerModerator(moderator);

    }
    @PostMapping(path = "/moderator/registerSecretary")
    public String registerSecretary(@RequestBody Secretary secretary) throws IOException {
        System.out.println(secretary);
        return moderatorService.registerSecretary(secretary);

    }
    // add user - that can be used just if the user is moderator
    @PostMapping(path = "/moderator/registerDoctor")
    public String registerDoctor(@RequestBody Doctor doctor) throws IOException {
        System.out.println(doctor);
        return moderatorService.registerDoctor(doctor);

    }
    @GetMapping("/moderator/allUsers")
    public List<User> getEmployees(){
        return moderatorService.getAllEmployees();
    }
    @GetMapping("/moderator/allDoctors")
    public List<Doctor> getAllDoctors(){
        return moderatorService.getAllDoctors();
    }
    @GetMapping("/moderator/allSecretaries")
    public List<Secretary> getAllSecretaries(){
        return moderatorService.getAllSecretaries();
    }
    @GetMapping("/moderator/userCnp{cnp}")
    public User getUserByCnp(@RequestParam(value = "cnp") String cnp){

        return moderatorService.getUserCnp(cnp);
    }
    @DeleteMapping("/moderator/deleteDoctor{cnp}")
    public String deleteDoctor(@RequestParam(value = "cnp") String cnp){
        return moderatorService.deleteDoctor(cnp);
    }
    @DeleteMapping("/moderator/deleteSecretary{cnp}")
    public String deleteSecretary(@RequestParam(value = "cnp") String cnp){
        return moderatorService.deleteSecretary(cnp);
    }
}
