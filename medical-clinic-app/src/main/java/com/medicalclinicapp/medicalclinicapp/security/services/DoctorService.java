package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.security.models.Doctor;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService  {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String registerUser(Doctor doctor) throws IOException {
        //verificam daca un user cu email-ul respectiv se gaseste deja
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctor.getCnp());
        if (doctorOptional.isPresent()) {
            throw new IllegalStateException("Cnp taken");
        }
        if(doctor.getImageUser() == null || doctor.getImageUser().trim().isEmpty()){
            doctor.setImageUser("");
        }

        doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));

        doctorRepository.saveAndFlush(doctor);
        return "Register done";
    };


}
