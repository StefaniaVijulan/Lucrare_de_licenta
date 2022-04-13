package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.repository.HospitalizationRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.*;
import com.medicalclinicapp.medicalclinicapp.security.repository.*;
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
    private CurantRepository curantRepository;

    @Autowired
    private SecretaryRepository secretaryRepository;

    @Autowired
    private ImagistRepository imagistRepository;

    @Autowired
    private HematologRepository hematologRepository;

    @Autowired
    private HospitalizationRepository hospitalizationRepository;



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
    public String registerCurant(Curant curant) throws IOException {
        //verificam daca un user cu email-ul respectiv se gaseste deja
        Optional<Curant> curantOptional = curantRepository.findById(curant.getCnp());
        if (curantOptional.isPresent()) {
            return "Cnp taken";
        }
        if(curant.getImageUser() == null || curant.getImageUser().trim().isEmpty()){
            curant.setImageUser("");
        }

        curant.setPassword(bCryptPasswordEncoder.encode(curant.getPassword()));
        curant.setRole("CURANT");
        curantRepository.save(curant);
        return "Register curant done";
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
    public String registerImagist(Imagist imagist) throws IOException {
        //verificam daca un user cu email-ul respectiv se gaseste deja
        Optional<Imagist> imagistOptional = imagistRepository.findById(imagist.getCnp());
        if (imagistOptional.isPresent()) {
            return "Cnp taken";
        }
        if(imagist.getImageUser() == null || imagist.getImageUser().trim().isEmpty()){
            imagist.setImageUser("");
        }

        imagist.setPassword(bCryptPasswordEncoder.encode(imagist.getPassword()));
        imagist.setRole("IMAGIST");
        imagistRepository.saveAndFlush(imagist);
        return "Register imagist done";
    };
    public String registerHematolog(Hematolog hematolog) throws IOException {
        //verificam daca un user cu email-ul respectiv se gaseste deja
        Optional<Hematolog> hematologOptional = hematologRepository.findById(hematolog.getCnp());
        if (hematologOptional.isPresent()) {
            return "Cnp taken";
        }
        if(hematolog.getImageUser() == null || hematolog.getImageUser().trim().isEmpty()){
            hematolog.setImageUser("");
        }

        hematolog.setPassword(bCryptPasswordEncoder.encode(hematolog.getPassword()));
        hematolog.setRole("HEMATOLOG");
        hematologRepository.saveAndFlush(hematolog);
        return "Register hematolog done";
    };


    public List<User> getAllEmployees(){
        List<User> userList =  userRepository.findAll();
        return userList;
    }
    public List<Curant> getAllCurant(){
        List<Curant> generalistList = new ArrayList<>();

        for(int i=0; i<curantRepository.findAll().size(); i++){
            if(curantRepository.findAll().get(i).getRole().equals("GENERALIST")){
                generalistList.add(curantRepository.findAll().get(i));
            }}
        return generalistList;
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
    public String deleteCurant(String cnp){
        if(!curantRepository.existsById(cnp))
            throw new IllegalStateException("User not found for this cnp :: " + cnp);
        Curant curant = curantRepository.findByCnp(cnp);
        for(int i=0; i<hospitalizationRepository.findAll().size(); i++){
            if(hospitalizationRepository.findAll().get(i).getCurant().getCnp().equals(cnp)){
                hospitalizationRepository.findAll().get(i).setCurant(curantRepository.findByCnp("5981023189682"));
            }}
        curantRepository.delete(curant);

        return "Doctor deleted";
    }
    public String deleteSecretary(String cnp){
        if(!secretaryRepository.existsById(cnp))
            throw new IllegalStateException("User not found for this cnp :: " + cnp);
        Secretary secretary = secretaryRepository.findByCnp(cnp);
        for(int i=0; i<hospitalizationRepository.findAll().size(); i++){
            if(hospitalizationRepository.findAll().get(i).getSecretary().getCnp().equals(cnp)){
                hospitalizationRepository.findAll().get(i).setSecretary(secretaryRepository.findByCnp("5981023189682"));
            }}
        secretaryRepository.delete(secretary);

        return "Secretary deleted";
    }
}
