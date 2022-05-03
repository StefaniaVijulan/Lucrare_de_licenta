package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import com.medicalclinicapp.medicalclinicapp.repository.HospitalizationRepository;
import com.medicalclinicapp.medicalclinicapp.security.dto.LoginResponse;
import com.medicalclinicapp.medicalclinicapp.security.models.*;
import com.medicalclinicapp.medicalclinicapp.security.repository.*;
import com.medicalclinicapp.medicalclinicapp.services.EmailService;
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
    private CardiologRepository cardiologRepository;

    @Autowired
    private SecretaryRepository secretaryRepository;

    @Autowired
    private ImagistRepository imagistRepository;

    @Autowired
    private HematologRepository hematologRepository;

    @Autowired
    private HospitalizationRepository hospitalizationRepository;

    @Autowired
    private EmailService emailService;



    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Moderator registerModerator(Moderator moderator) throws Exception {
        //verificam daca un user cu email-ul respectiv se gaseste deja
        Optional<Moderator> moderatorOptional = moderatorRepository.findById(moderator.getCnp());
        if (moderatorOptional.isPresent()) {
            throw new IOException("Exista userul deja");
        }
        if(moderator.getImageUser() == null || moderator.getImageUser().trim().isEmpty()){
            moderator.setImageUser("");
        }

        moderator.setPassword(bCryptPasswordEncoder.encode(moderator.getPassword()));
        emailService.sendmail("vijulandumitru@gmail.com","test","body test");
        moderator.setRole("MODERATOR");
        moderatorRepository.saveAndFlush(moderator);
        return moderator;
    };
    public Cardiolog registerCardiolog(Cardiolog cardiolog) throws IOException {
        //verificam daca un user cu email-ul respectiv se gaseste deja
        Optional<Cardiolog> cardiologOptional = cardiologRepository.findById(cardiolog.getCnp());
        if (cardiologOptional.isPresent()) {
            throw new IllegalStateException("Cnp exist!");
        }
        emailService.sendmail(cardiolog.getEmailUser(),"test","body test");
        cardiolog.setPassword(bCryptPasswordEncoder.encode("parola"));
        cardiolog.setRole("CARDIOLOG");
        cardiologRepository.save(cardiolog);
        return cardiolog;
    };
    public Secretary registerSecretary(Secretary secretary) throws IOException {
        //verificam daca un user cu email-ul respectiv se gaseste deja
        Optional<Secretary> secretaryOptional = secretaryRepository.findById(secretary.getCnp());
        if (secretaryOptional.isPresent()) {
            throw new IllegalStateException("Cnp exist!");
        }
        if(secretary.getImageUser() == null || secretary.getImageUser().trim().isEmpty()){
            secretary.setImageUser("");
        }

        secretary.setPassword(bCryptPasswordEncoder.encode("parola"));
        secretary.setRole("SECRETAR");
        secretaryRepository.saveAndFlush(secretary);
        System.out.println(secretary.getEmailUser());
        System.out.println(secretary.getEmailUser().getClass().getSimpleName());
        emailService.sendmail(secretary.getEmailUser(),"test","sper ca merge");
        return secretary;
    };
    public Imagist registerImagist(Imagist imagist) throws IOException {
        //verificam daca un user cu email-ul respectiv se gaseste deja
        Optional<Imagist> imagistOptional = imagistRepository.findById(imagist.getCnp());
        if (imagistOptional.isPresent()) {
            throw new IllegalStateException("Cnp exist!");
        }
        if(imagist.getImageUser() == null || imagist.getImageUser().trim().isEmpty()){
            imagist.setImageUser("");
        }

        imagist.setPassword(bCryptPasswordEncoder.encode("parola"));
        imagist.setRole("IMAGIST");
        imagistRepository.saveAndFlush(imagist);
        emailService.sendmail(imagist.getEmailUser(),"test","sper ca merge");
        return imagist;
    };
    public Hematolog registerHematolog(Hematolog hematolog) throws IOException {
        //verificam daca un user cu email-ul respectiv se gaseste deja
        Optional<Hematolog> hematologOptional = hematologRepository.findById(hematolog.getCnp());
        if (hematologOptional.isPresent()) {
            throw new IllegalStateException("Cnp exist!");
        }
        if(hematolog.getImageUser() == null || hematolog.getImageUser().trim().isEmpty()){
            hematolog.setImageUser("");
        }

        hematolog.setPassword(bCryptPasswordEncoder.encode("parola"));
        hematolog.setRole("HEMATOLOG");
        hematologRepository.saveAndFlush(hematolog);
        emailService.sendmail(hematolog.getEmailUser(),"test","sper ca merge");
        return hematolog;
    };


    public List<User> getAllEmployees(){
        List<User> userList =  userRepository.findAll();
        return userList;
    }
    public List<Cardiolog> getAllCardiolog(){
        List<Cardiolog> generalistList = new ArrayList<>();

        for(int i=0; i<cardiologRepository.findAll().size(); i++){
            if(cardiologRepository.findAll().get(i).getRole().equals("CARDIOLOG")){
                generalistList.add(cardiologRepository.findAll().get(i));
            }}
        return generalistList;
    }
    public List<Secretary> getAllSecretaries(){
        List<Secretary> secretaryList = new ArrayList<>();
        for(int i=0; i<secretaryRepository.findAll().size(); i++){
            if(secretaryRepository.findAll().get(i).getRole().equals("SECRETAR")){
                secretaryList.add(secretaryRepository.findAll().get(i));
            }}
        return secretaryList;
    }
    public List<Imagist> getAllImagists(){
        List<Imagist> imagistList = new ArrayList<>();
        for(int i=0; i<imagistRepository.findAll().size(); i++){
            if(imagistRepository.findAll().get(i).getRole().equals("IMAGIST")){
                imagistList.add(imagistRepository.findAll().get(i));
            }}
        return imagistList;
    }
    public List<Hematolog> getAllHematologs(){
        List<Hematolog> hematologList = new ArrayList<>();
        for(int i=0; i<hematologRepository.findAll().size(); i++){
            if(hematologRepository.findAll().get(i).getRole().equals("HEMATOLOG")){
                hematologList.add(hematologRepository.findAll().get(i));
            }}
        return hematologList;
    }

    public List<Hospitalization> AllHospiyalizationCardiolog(String cnp){
        List<Hospitalization> hospitalizationsList = new ArrayList<>();
        for(int i=0; i<hospitalizationRepository.findAll().size(); i++){
            if(hospitalizationRepository.findAll().get(i).getCardiolog().getCnp().equals(cnp)){
                hospitalizationsList.add(hospitalizationRepository.findAll().get(i));
            }}
        return hospitalizationsList;
    }

    public User editCardiolog(String role, String cnpCardiolog, Cardiolog cardiolog ){
        System.out.println("Intra in service edit");
        System.out.println(cardiolog.getCnp());
        System.out.println(cardiolog.getEmailUser());
        System.out.println(cardiolog.getFirstName());
        if(role.equals("CARDIOLOG")){
            Cardiolog cardiolog1 = cardiologRepository.findById(cnpCardiolog)
                    .orElseThrow(() -> new RuntimeException("Nu exista cardiolog cu acest cnp: " + cnpCardiolog));
            cardiolog1.setCnp(cnpCardiolog);
            cardiolog1.setFirstName(cardiolog.getFirstName());
            cardiolog1.setLastName(cardiolog.getLastName());
            cardiolog1.setEmailUser(cardiolog.getEmailUser());
            cardiolog1.setNumberUser(cardiolog.getNumberUser());
            cardiolog1.setRole("CARDIOLOG");
            cardiologRepository.save(cardiolog1);
                    return cardiolog1;
        }
        else if(role.equals("SECRETAR")){
            Secretary secretary = secretaryRepository.findById(cnpCardiolog)
                    .orElseThrow(() -> new RuntimeException("Nu exista cardiolog cu acest cnp: " + cnpCardiolog));
            secretary.setCnp(cnpCardiolog);
            secretary.setFirstName(cardiolog.getFirstName());
            secretary.setLastName(cardiolog.getLastName());
            secretary.setEmailUser(cardiolog.getEmailUser());
            secretary.setNumberUser(cardiolog.getNumberUser());
            secretary.setRole("SECRETAR");
            secretaryRepository.save(secretary);
            return secretary;
        }
        else if(role.equals("IMAGIST")){
            Imagist imagist = imagistRepository.findById(cnpCardiolog)
                    .orElseThrow(() -> new RuntimeException("Nu exista cardiolog cu acest cnp: " + cnpCardiolog));
            imagist.setCnp(cnpCardiolog);
            imagist.setFirstName(cardiolog.getFirstName());
            imagist.setLastName(cardiolog.getLastName());
            imagist.setEmailUser(cardiolog.getEmailUser());
            imagist.setNumberUser(cardiolog.getNumberUser());
            imagist.setRole("IMAGIST");
            imagistRepository.save(imagist);
            return imagist;
        }
        else if(role.equals("HEMATOLOG")){
            Hematolog hematolog = hematologRepository.findById(cnpCardiolog)
                    .orElseThrow(() -> new RuntimeException("Nu exista cardiolog cu acest cnp: " + cnpCardiolog));
            hematolog.setCnp(cnpCardiolog);
            hematolog.setFirstName(cardiolog.getFirstName());
            hematolog.setLastName(cardiolog.getLastName());
            hematolog.setEmailUser(cardiolog.getEmailUser());
            hematolog.setNumberUser(cardiolog.getNumberUser());
            hematolog.setRole("HEMATOLOG");
            hematologRepository.save(hematolog);
            return hematolog;
        }
        return null;
    }

    public User getUserCnp(String cnp){

        if(!userRepository.existsById(cnp))
            throw new IllegalStateException("User not found for this cnp :: " + cnp);
        else
            return userRepository.findByCnp(cnp);
    }

    public String deleteUser(String cnp) throws Exception {

        if(cardiologRepository.existsById(cnp))
            cardiologRepository.delete(cardiologRepository.findByCnp(cnp));
        else if(secretaryRepository.existsById(cnp))
            secretaryRepository.delete(secretaryRepository.findByCnp(cnp));
        else if(hematologRepository.existsById(cnp))
            hematologRepository.delete(hematologRepository.findByCnp(cnp));
        else if(imagistRepository.existsById(cnp))
            imagistRepository.delete(imagistRepository.findByCnp(cnp));
        System.out.println("intra aici");
        return null;
    }

    public User resetPassword(String cnp) throws Exception {
        System.out.println("Intra in service resetPass");
        User currentUser = this.userRepository.findByCnp(cnp);

        String newPass = "parola1";
        currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPass));
        emailService.sendmail(currentUser.getEmailUser(),"test","Noua parola este " + newPass);
        this.userRepository.save(currentUser);
        System.out.println("Password change");

        return currentUser;

    }
}