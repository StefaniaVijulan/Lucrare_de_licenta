package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.security.models.Doctor;
import com.medicalclinicapp.medicalclinicapp.security.models.Moderator;
import com.medicalclinicapp.medicalclinicapp.security.models.Secretary;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.repository.DoctorRepository;
import com.medicalclinicapp.medicalclinicapp.security.repository.ModeratorRepository;
import com.medicalclinicapp.medicalclinicapp.security.repository.SecretaryRepository;
import com.medicalclinicapp.medicalclinicapp.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModeratorService {
    @Autowired
    private ModeratorRepository moderatorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SecretaryRepository secretaryRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String registerModerator(Moderator moderator) throws IOException {
        //verificam daca un user cu email-ul respectiv se gaseste deja
        Optional<Moderator> moderatorOptional = moderatorRepository.findById(moderator.getCnp());
        if (moderatorOptional.isPresent()) {
            return "Cnp taken";
        }
        if(moderator.getImageUser() == null || moderator.getImageUser().trim().isEmpty()){
            moderator.setImageUser("");
        }

        moderator.setPassword(bCryptPasswordEncoder.encode(moderator.getPassword()));
        moderator.setRole("MODERATOR");
        moderatorRepository.saveAndFlush(moderator);
        return "Register moderator done";
    };
    public String registerDoctor(Doctor doctor) throws IOException {
        //verificam daca un user cu email-ul respectiv se gaseste deja
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctor.getCnp());
        if (doctorOptional.isPresent()) {
            return "Cnp taken";
        }
        if(doctor.getImageUser() == null || doctor.getImageUser().trim().isEmpty()){
            doctor.setImageUser("");
        }

        doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
        doctor.setRole("DOCTOR");
        doctorRepository.saveAndFlush(doctor);
        return "Register doctor done";
    };
    public String registerSecretary(Secretary secretary) throws IOException {
        //verificam daca un user cu email-ul respectiv se gaseste deja
        Optional<Secretary> secretaryOptional = secretaryRepository.findById(secretary.getCnp());
        if (secretaryOptional.isPresent()) {
            return "Cnp taken";
        }
        if(secretary.getImageUser() == null || secretary.getImageUser().trim().isEmpty()){
            secretary.setImageUser("");
        }

        secretary.setPassword(bCryptPasswordEncoder.encode(secretary.getPassword()));
        secretary.setRole("SECRETARY");
        secretaryRepository.saveAndFlush(secretary);
        return "Register secretary done";
    };
    public List<User> getAllEmployees(){
        List<User> userList =  userRepository.findAll();
        return userList;
    }
    public List<Doctor> getAllDoctors(){
        List<Doctor> doctorList = new ArrayList<>();
        for(int i=0; i<doctorRepository.findAll().size(); i++){
            if(doctorRepository.findAll().get(i).getRole().equals("DOCTOR")){
                doctorList.add(doctorRepository.findAll().get(i));
            }}
        return doctorList;
    }
    public List<Secretary> getAllSecretaries(){
        List<Secretary> secretaryList = new ArrayList<>();
        for(int i=0; i<secretaryRepository.findAll().size(); i++){
            if(secretaryRepository.findAll().get(i).getRole().equals("SECRETARY")){
                secretaryList.add(secretaryRepository.findAll().get(i));
            }}
        return secretaryList;
    }
    public User getUserCnp(String cnp){

        if(!userRepository.existsById(cnp))
            throw new IllegalStateException("User not found for this cnp :: " + cnp);
        else
            return userRepository.findByCnp(cnp);
    }
    public String deleteDoctor(String cnp){
        if(!doctorRepository.existsById(cnp))
            throw new IllegalStateException("User not found for this cnp :: " + cnp);
        Doctor doctor = doctorRepository.findByCnp(cnp);
        doctorRepository.delete(doctor);

        return "Doctor deleted";
    }
    public String deleteSecretary(String cnp){
        if(!secretaryRepository.existsById(cnp))
            throw new IllegalStateException("User not found for this cnp :: " + cnp);
        Secretary secretary = secretaryRepository.findByCnp(cnp);
        secretaryRepository.delete(secretary);

        return "Secretary deleted";
    }
}
