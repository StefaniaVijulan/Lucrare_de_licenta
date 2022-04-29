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
        emailService.sendmail("vijulandumitru@gmail.com","test","body test");
        moderator.setRole("MODERATOR");
        moderatorRepository.saveAndFlush(moderator);
        return "Register moderator done";
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
    public String registerSecretary(Secretary secretary) throws IOException {
        //verificam daca un user cu email-ul respectiv se gaseste deja
        Optional<Secretary> secretaryOptional = secretaryRepository.findById(secretary.getCnp());
        if (secretaryOptional.isPresent()) {
            return "Cnp taken";
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
            if(secretaryRepository.findAll().get(i).getRole().equals("SECRETARY")){
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

    public Cardiolog editCardiolog(String role, String cnpCardiolog,Cardiolog cardiolog ){
        System.out.println("Intra in service edit");
        System.out.println(cardiolog.getCnp());
        System.out.println(cardiolog.getEmailUser());
        System.out.println(cardiolog.getFirstName());
        if(role.equals("CARDIOLOG")){
            System.out.println("intra si aici");
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

    public String deleteCardiolog(String cnp){

        if(!cardiologRepository.existsById(cnp))
            throw new IllegalStateException("User not found for this cnp :: " + cnp);
        Cardiolog cardiolog = cardiologRepository.findByCnp(cnp);
        for(int i=0; i<hospitalizationRepository.findAll().size(); i++){
            if(hospitalizationRepository.findAll().get(i).getCardiolog().getCnp().equals(cnp)){
                hospitalizationRepository.findAll().get(i).setCardiolog(cardiologRepository.findByCnp("5981023189682"));
            }}
        cardiologRepository.delete(cardiolog);
        System.out.println("deleted");
        return null;
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