package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.security.models.Doctor;
import com.medicalclinicapp.medicalclinicapp.security.models.Moderator;
import com.medicalclinicapp.medicalclinicapp.security.repository.DoctorRepository;
import com.medicalclinicapp.medicalclinicapp.security.repository.ModeratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModeratorService {
    @Autowired
    private ModeratorRepository moderatorRepository;


    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String registerModerator(Moderator moderator) throws IOException {
        //verificam daca un user cu email-ul respectiv se gaseste deja
        Optional<Moderator> moderatorOptional = moderatorRepository.findById(moderator.getCnp());
        if (moderatorOptional.isPresent()) {
            throw new IllegalStateException("Cnp taken");
        }
        if(moderator.getImageUser() == null || moderator.getImageUser().trim().isEmpty()){
            moderator.setImageUser("");
        }

        moderator.setPassword(bCryptPasswordEncoder.encode(moderator.getPassword()));
        moderator.setRole("MODERATOR");
        moderatorRepository.saveAndFlush(moderator);
        return "Register moderator done";
    };
}
