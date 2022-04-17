package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.security.models.*;
import com.medicalclinicapp.medicalclinicapp.security.repository.HematologRepository;
import com.medicalclinicapp.medicalclinicapp.security.services.ModeratorService;
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
    public String registerModerator(@RequestBody Moderator moderator) throws IOException {
        System.out.println(moderator);
        return moderatorService.registerModerator(moderator);

    }
    @PostMapping(path = "/moderator/registerSecretary")
    public String registerSecretary(@RequestBody Secretary secretary) throws IOException {
        System.out.println(secretary);
        return moderatorService.registerSecretary(secretary);

    }
    @PostMapping(path = "/moderator/registerCurant")
    public String registerCurant(@RequestBody Curant curant) throws IOException {
        System.out.println(curant);
        return moderatorService.registerCurant(curant);

    }
    @PostMapping(path = "/moderator/registerImagist")
    public String registerImagist(@RequestBody Imagist imagist) throws IOException {
        System.out.println(imagist);
        return moderatorService.registerImagist(imagist);

    }
    @PostMapping(path = "/moderator/registerHematolog")
    public String registerHematolog(@RequestBody Hematolog hematolog) throws IOException {
        System.out.println(hematolog);
        return moderatorService.registerHematolog(hematolog);

    }


    @GetMapping("/moderator/allUsers")
    public List<User> getEmployees(){

        return moderatorService.getAllEmployees();
    }
    @GetMapping("/moderator/allCurant")
    public List<Curant> getAllGeneralists(){
        return moderatorService.getAllCurant();
    }
    @GetMapping("/moderator/allSecretaries")
    public List<Secretary> getAllSecretaries(){
        return moderatorService.getAllSecretaries();
    }
    @GetMapping("/moderator/userCnp")
    public User getUserByCnp(@RequestParam(value = "cnp") String cnp){

        return moderatorService.getUserCnp(cnp);
    }
    @DeleteMapping("/moderator/deleteCurant")
    public String deleteCurant(@RequestParam(value = "cnp") String cnp){
        return moderatorService.deleteCurant(cnp);
    }
    @DeleteMapping("/moderator/deleteSecretary{cnp}")
    public String deleteSecretary(@RequestParam(value = "cnp") String cnp){
        return moderatorService.deleteSecretary(cnp);
    }
}
