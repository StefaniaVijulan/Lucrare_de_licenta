package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.models.FisaPatient;
import com.medicalclinicapp.medicalclinicapp.models.Patient;
import com.medicalclinicapp.medicalclinicapp.repository.FisaPatientRepository;
import com.medicalclinicapp.medicalclinicapp.repository.PatientRepository;
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

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CardiologRepository cardiologRepository;

    @Autowired
    private FisaPatientRepository fisaPatientRepository;

    @Autowired
    private SecretaryRepository secretaryRepository;

    @Autowired
    private PatientRepository patientRepository;


    @Autowired
    private ImagistRepository imagistRepository;

    @Autowired
    private HematologRepository hematologRepository;

    @Autowired
    private UserRepository userRepository;

    /*

  @Autowired
  private HospitalizationRepository hospitalizationRepository;


*/
  public Moderator registerModerator(Moderator moderator) throws Exception {
      //verificam daca un user cu email-ul respectiv se gaseste deja
      Optional<Moderator> moderatorOptional = moderatorRepository.findByCnp(moderator.getCnp());
      if (moderatorOptional.isPresent()) {
          throw new IOException("Exista userul deja");
      }
      String parola = "parola";
      moderator.setPassword(bCryptPasswordEncoder.encode(parola));
      String emailtext;
      emailtext = "Buna ziua " + moderator.getLastName() + " " + moderator.getFirstName() +",\n\n Un nou cont bazat pe CNPul dumneavoastra a fost creat." +
              "\n\nParola corespunzatoare este: " + parola + ".";
      emailService.sendmail(moderator.getEmailUser(),"Medical Clinic App - Detalii cont",emailtext);
      moderator.setRole("MODERATOR");
      moderatorRepository.saveAndFlush(moderator);
      Patient patient = new Patient();
      patient.setCnp(moderator.getCnp());
      patient.setPassword(bCryptPasswordEncoder.encode(parola));
      patient.setFirstName(moderator.getFirstName());
      patient.setLastName(moderator.getLastName());
      patient.setEmailUser(moderator.getEmailUser());
      patient.setNumberUser(moderator.getNumberUser());
      patient.setRole("PACIENT");
      patientRepository.save(patient);
      FisaPatient fisaPatient = new FisaPatient();
      for (int i = 0; i < patientRepository.findAll().size(); i++) {
          {
              if(patientRepository.findAll().get(i).getCnp().equals(moderator.getCnp()))
                  fisaPatient.setPatient(patientRepository.findAll().get(i));
          }
      }
      fisaPatientRepository.save(fisaPatient);
      return moderator;
  };

  public Cardiolog registerCardiolog(Cardiolog cardiolog)  {
        if (cardiologRepository.existsByCnp(cardiolog.getCnp())) {
            //acest cardiolog exista deja
            return null;
        }

        String parola = "parola";
        cardiolog.setPassword(bCryptPasswordEncoder.encode(parola));
        String emailtext;
        emailtext = "Buna ziua " + cardiolog.getLastName() + " " + cardiolog.getFirstName() +",\n\n Un nou cont bazat pe CNPul dumneavoastra a fost creat." +
                "\n\nParola corespunzatoare este: " + parola + ".";
        emailService.sendmail(cardiolog.getEmailUser(),"Medical Clinic App - Detalii cont",emailtext);
        cardiolog.setRole("CARDIOLOG");

        cardiologRepository.save(cardiolog);
      Patient patient = new Patient();
      patient.setCnp(cardiolog.getCnp());
      patient.setPassword(bCryptPasswordEncoder.encode(parola));
      patient.setFirstName(cardiolog.getFirstName());
      patient.setLastName(cardiolog.getLastName());
      patient.setEmailUser(cardiolog.getEmailUser());
      patient.setNumberUser(cardiolog.getNumberUser());
      patient.setRole("PACIENT");
      patientRepository.save(patient);
      FisaPatient fisaPatient = new FisaPatient();
      for (int i = 0; i < patientRepository.findAll().size(); i++) {
          {
              if(patientRepository.findAll().get(i).getCnp().equals(cardiolog.getCnp()))
                  fisaPatient.setPatient(patientRepository.findAll().get(i));
          }
      }
      fisaPatientRepository.save(fisaPatient);
        return cardiolog;
    };
  public Secretary registerSecretary(Secretary secretary) {
         if (secretaryRepository.existsByCnp(secretary.getCnp())) {
             //acest cardiolog exista deja
             return null;
         }
         String parola = "parola";
         secretary.setPassword(bCryptPasswordEncoder.encode(parola));
         String emailtext;
         emailtext = "Buna ziua " + secretary.getLastName() + " " + secretary.getFirstName() +",\n\n Un nou cont bazat pe CNPul dumneavoastra a fost creat." +
                 "\n\nParola corespunzatoare este: " + parola + ".";
         emailService.sendmail(secretary.getEmailUser(),"Medical Clinic App - Detalii cont",emailtext);
         secretary.setRole("SECRETAR");
         secretaryRepository.save(secretary);
      Patient patient = new Patient();
      patient.setCnp(secretary.getCnp());
      patient.setPassword(bCryptPasswordEncoder.encode(parola));
      patient.setFirstName(secretary.getFirstName());
      patient.setLastName(secretary.getLastName());
      patient.setEmailUser(secretary.getEmailUser());
      patient.setNumberUser(secretary.getNumberUser());
      patient.setRole("PACIENT");
      patientRepository.save(patient);
      FisaPatient fisaPatient = new FisaPatient();
      for (int i = 0; i < patientRepository.findAll().size(); i++) {
          {
              if(patientRepository.findAll().get(i).getCnp().equals(secretary.getCnp()))
                  fisaPatient.setPatient(patientRepository.findAll().get(i));
          }
      }
      fisaPatientRepository.save(fisaPatient);
         return secretary;
    };
  public Imagist registerImagist(Imagist imagist) {
         if (imagistRepository.existsByCnp(imagist.getCnp())) {
             //acest cardiolog exista deja
             return null;
         }

         String parola = "parola";
         imagist.setPassword(bCryptPasswordEncoder.encode(parola));
         String emailtext;
         emailtext = "Buna ziua " + imagist.getLastName() + " " + imagist.getFirstName() +",\n\n Un nou cont bazat pe CNPul dumneavoastra a fost creat." +
                 "\n\nParola corespunzatoare este: " + parola + ".";
         emailService.sendmail(imagist.getEmailUser(),"Medical Clinic App - Detalii cont",emailtext);
         imagist.setRole("IMAGIST");

         imagistRepository.save(imagist);
      Patient patient = new Patient();
      patient.setCnp(imagist.getCnp());
      patient.setPassword(bCryptPasswordEncoder.encode(parola));
      patient.setFirstName(imagist.getFirstName());
      patient.setLastName(imagist.getLastName());
      patient.setEmailUser(imagist.getEmailUser());
      patient.setNumberUser(imagist.getNumberUser());
      patient.setRole("PACIENT");
      patientRepository.save(patient);
      FisaPatient fisaPatient = new FisaPatient();
      for (int i = 0; i < patientRepository.findAll().size(); i++) {
          {
              if(patientRepository.findAll().get(i).getCnp().equals(imagist.getCnp()))
                  fisaPatient.setPatient(patientRepository.findAll().get(i));
          }
      }
      fisaPatientRepository.save(fisaPatient);
         return imagist;
    };
  public Hematolog registerHematolog(Hematolog hematolog)  {
           if (hematologRepository.existsByCnp(hematolog.getCnp())) {
               //acest cardiolog exista deja
               return null;
           }

           String parola = "parola";
           hematolog.setPassword(bCryptPasswordEncoder.encode(parola));
           String emailtext;
           emailtext = "Buna ziua " + hematolog.getLastName() + " " + hematolog.getFirstName() +",\n\n Un nou cont bazat pe CNPul dumneavoastra a fost creat." +
                   "\n\nParola corespunzatoare este: " + parola + ".";
           emailService.sendmail(hematolog.getEmailUser(),"Medical Clinic App - Detalii cont",emailtext);
           hematolog.setRole("HEMATOLOG");

           hematologRepository.save(hematolog);
      Patient patient = new Patient();
      patient.setCnp(hematolog.getCnp());
      patient.setPassword(bCryptPasswordEncoder.encode(parola));
      patient.setFirstName(hematolog.getFirstName());
      patient.setLastName(hematolog.getLastName());
      patient.setEmailUser(hematolog.getEmailUser());
      patient.setNumberUser(hematolog.getNumberUser());
      patient.setRole("PACIENT");
      patientRepository.save(patient);
      FisaPatient fisaPatient = new FisaPatient();
      for (int i = 0; i < patientRepository.findAll().size(); i++) {
          {
              if(patientRepository.findAll().get(i).getCnp().equals(hematolog.getCnp()))
                  fisaPatient.setPatient(patientRepository.findAll().get(i));
          }
      }
      fisaPatientRepository.save(fisaPatient);
           return hematolog;
        };


  public List<User> getAllEmployees(){
        List<User> userList =  userRepository.findAll();
        return userList;
  }

  public List<Secretary> getAllSecretaries(){
        List<Secretary> secretaryList = new ArrayList<>();
        for(int i=0; i<secretaryRepository.findAll().size(); i++){
            if(secretaryRepository.findAll().get(i).getRole().equals("SECRETAR")){
                secretaryList.add(secretaryRepository.findAll().get(i));
            }}
        return secretaryList;
  }
  public List<Cardiolog> getAllCardiolog(){
        List<Cardiolog> generalistList = new ArrayList<>();
        for(int i=0; i<cardiologRepository.findAll().size(); i++){
            if(cardiologRepository.findAll().get(i).getRole().equals("CARDIOLOG")){
                generalistList.add(cardiologRepository.findAll().get(i));
            }}
        return generalistList;
  }
  public List<Hematolog> getAllHematologs(){
        List<Hematolog> hematologList = new ArrayList<>();
        for(int i=0; i<hematologRepository.findAll().size(); i++){
            if(hematologRepository.findAll().get(i).getRole().equals("HEMATOLOG")){
                hematologList.add(hematologRepository.findAll().get(i));
            }}
        return hematologList;
  }
  public List<Imagist> getAllImagists(){
        List<Imagist> imagistList = new ArrayList<>();
        for(int i=0; i<imagistRepository.findAll().size(); i++){
            if(imagistRepository.findAll().get(i).getRole().equals("IMAGIST")){
                imagistList.add(imagistRepository.findAll().get(i));
            }}
        return imagistList;
  }


    public User editUser(String role, String cnp, User user){
        if(role.equals("CARDIOLOG")){
            //Cardiologul cu acest cnp nu exista
            if(!cardiologRepository.existsByCnp(cnp))
                return null;
            Cardiolog cardiolog1 = cardiologRepository.findByCnp(cnp);
            cardiolog1.setCnp(cnp);
            cardiolog1.setFirstName(user.getFirstName());
            cardiolog1.setLastName(user.getLastName());
            cardiolog1.setEmailUser(user.getEmailUser());
            cardiolog1.setNumberUser(user.getNumberUser());
            cardiolog1.setRole("CARDIOLOG");
            cardiologRepository.save(cardiolog1);
            return cardiolog1;
        }
        else if(role.equals("SECRETAR")){
            if(!secretaryRepository.existsByCnp(cnp))
                return null;
            Secretary secretary = secretaryRepository.findByCnp(cnp);
            secretary.setCnp(cnp);
            secretary.setFirstName(user.getFirstName());
            secretary.setLastName(user.getLastName());
            secretary.setEmailUser(user.getEmailUser());
            secretary.setNumberUser(user.getNumberUser());
            secretary.setRole("SECRETAR");
            secretaryRepository.save(secretary);
            return secretary;
        }
        else if(role.equals("IMAGIST")){
            if(!imagistRepository.existsByCnp(cnp))
                return null;
            Imagist imagist = imagistRepository.findByCnp(cnp);
            imagist.setCnp(cnp);
            imagist.setFirstName(user.getFirstName());
            imagist.setLastName(user.getLastName());
            imagist.setEmailUser(user.getEmailUser());
            imagist.setNumberUser(user.getNumberUser());
            imagist.setRole("IMAGIST");
            imagistRepository.save(imagist);
            return imagist;
        }
        else if(role.equals("HEMATOLOG")){
            if(!hematologRepository.existsByCnp(cnp))
                return null;
            Hematolog hematolog = hematologRepository.findByCnp(cnp);
            hematolog.setCnp(cnp);
            hematolog.setFirstName(user.getFirstName());
            hematolog.setLastName(user.getLastName());
            hematolog.setEmailUser(user.getEmailUser());
            hematolog.setNumberUser(user.getNumberUser());
            hematolog.setRole("HEMATOLOG");
            hematologRepository.save(hematolog);
            return hematolog;
        }
        return null;
    }

    public String deleteUser(String cnp) throws Exception {

        if (cardiologRepository.existsByCnp(cnp)) {
            cardiologRepository.delete(cardiologRepository.findByCnp(cnp));
        } else if (secretaryRepository.existsByCnp(cnp))
            secretaryRepository.delete(secretaryRepository.findByCnp(cnp));
        else if (hematologRepository.existsByCnp(cnp))
            hematologRepository.delete(hematologRepository.findByCnp(cnp));
        else if (imagistRepository.existsByCnp(cnp))
            imagistRepository.delete(imagistRepository.findByCnp(cnp));
        return null;
    }
    public User resetPassword(String cnp) throws Exception {
        User currentUser = this.userRepository.findByCnp(cnp);

        String newPass = "parola1";
        currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPass));
        String emailtext;
        emailtext = "Buna ziua " + currentUser.getLastName() + " " + currentUser.getFirstName() +",\n\n Parola ta a fost resetata cu succes." +
                "\n\nNoua parola este: " + newPass + ". Iti recomandam sa schimbi parola la prima accesare.";
        emailService.sendmail(currentUser.getEmailUser(),"Medical Clinic App - Resetare parola",emailtext);

        this.userRepository.save(currentUser);
        System.out.println("Password change");

        return currentUser;
    }

}