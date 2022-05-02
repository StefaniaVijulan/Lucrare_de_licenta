package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import com.medicalclinicapp.medicalclinicapp.security.dto.LoginResponse;
import com.medicalclinicapp.medicalclinicapp.security.models.*;
import com.medicalclinicapp.medicalclinicapp.security.repository.HematologRepository;
import com.medicalclinicapp.medicalclinicapp.security.services.ModeratorService;
import com.medicalclinicapp.medicalclinicapp.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ModeratorController {


    @Autowired
    private ModeratorService moderatorService;

    // add users
    @PostMapping(path = "/registerModerator")
    public Moderator registerModerator(@RequestBody Moderator moderator) throws Exception {
        System.out.println(moderator);
        return moderatorService.registerModerator(moderator);

    }
    @PostMapping(path = "/moderator/registerSecretary")
    public Secretary registerSecretary(@RequestBody Secretary secretary) throws IOException {
        System.out.println(secretary);
        return moderatorService.registerSecretary(secretary);

    }
    @PostMapping(path = "/moderator/registerCardiolog")
    public Cardiolog registerCardiolog(@RequestBody Cardiolog cardiolog) throws IOException {
        System.out.println(cardiolog);
        System.out.println("poate");
        return moderatorService.registerCardiolog(cardiolog);

    }
    @PostMapping(path = "/moderator/registerImagist")
    public Imagist registerImagist(@RequestBody Imagist imagist) throws IOException {
        System.out.println(imagist);
        return moderatorService.registerImagist(imagist);

    }
    @PostMapping(path = "/moderator/registerHematolog")
    public Hematolog registerHematolog(@RequestBody Hematolog hematolog) throws IOException {
        System.out.println(hematolog);
        return moderatorService.registerHematolog(hematolog);

    }

    @GetMapping(path="/moderator/resetPass")
    public User resetPass(@RequestParam("cnp") String cnp) throws Exception {
        System.out.println("Intra in controller resetPass");
        return moderatorService.resetPassword(cnp);
    }

    @GetMapping("/moderator/allUsers")
    public List<User> getEmployees(){

        return moderatorService.getAllEmployees();
    }
    @GetMapping("/moderator/allCurant")
    public List<Cardiolog> getAllCardiologs(){
        return moderatorService.getAllCardiolog();
    }
    @GetMapping("/moderator/allSecretaries")
    public List<Secretary> getAllSecretaries(){
        return moderatorService.getAllSecretaries();
    }
    @GetMapping("/moderator/allImagist")
    public List<Imagist> getAllImagists(){
        return moderatorService.getAllImagists();
    }
    @GetMapping("/moderator/allHematologs")
    public List<Hematolog> getAllHematologs(){
        return moderatorService.getAllHematologs();
    }

    @GetMapping("/moderator/allHospitalizationCardiolog")
    public List<Hospitalization> allHospi(@RequestParam String cnp){
        return moderatorService.AllHospiyalizationCardiolog(cnp);
    }

    @PutMapping(path = "/moderator/editUser")
    public User editUser(@RequestParam(value = "role")  String role, @RequestParam(value = "cnp") String cnp, @RequestBody Cardiolog cardiolog) throws IOException {
        System.out.println(cardiolog);
        return moderatorService.editCardiolog(role,cnp,cardiolog);

    }
    @GetMapping("/moderator/userCnp")
    public User getUserByCnp(@RequestParam(value = "cnp") String cnp){

        return moderatorService.getUserCnp(cnp);
    }

    @DeleteMapping("/moderator/deleteUser")
    public String deleteUser(@RequestParam(value = "cnp") String cnp) throws Exception {
        return moderatorService.deleteUser(cnp);
    }

}