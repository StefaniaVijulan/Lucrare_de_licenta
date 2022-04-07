package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.security.models.Moderator;
import com.medicalclinicapp.medicalclinicapp.security.models.Secretary;
import com.medicalclinicapp.medicalclinicapp.security.services.ModeratorService;
import com.medicalclinicapp.medicalclinicapp.security.services.SecretaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
}
