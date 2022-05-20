package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.dto.MailRequest;
import com.medicalclinicapp.medicalclinicapp.models.Patient;
import com.medicalclinicapp.medicalclinicapp.repository.PatientRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.ChangeImg;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.repository.UserRepository;
import com.medicalclinicapp.medicalclinicapp.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientRepository patientRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String cnp) throws UsernameNotFoundException {

        UserDetails user = userRepository.findByCnp(cnp);

        if(user == null){
            user = patientRepository.findByCnp(cnp);
        }
        if(user == null){
            throw new UsernameNotFoundException(
                    String.format("username with cnp %s not found", cnp));
        }

        return user;
    }
    public User loginUser(String cnp, String password){
        if (!userRepository.existsByCnp(cnp)) {
            throw new IllegalStateException("Cnp doesnt exist");
        }

        User userProfile = userRepository.findByCnp(cnp);
        String pass = userProfile.getPassword();
        if (!bCryptPasswordEncoder.matches(password, pass)) {
            throw new IllegalStateException("Cnp doesnt exist");

        }
        System.out.println(userProfile);
        return userProfile;
    }
    public User changePassword(String oldPass, String newPass, String cnpC) throws Exception {
        String username;
        username = userRepository.findByCnp(cnpC).getCnp();
        User currentUser = this.userRepository.findByCnp(username);
        if(this.bCryptPasswordEncoder.matches(oldPass, currentUser.getPassword()))
        {
            if(oldPass.equals(newPass))
            {System.out.println("Noua parola este la fel ca parola curenta");
                return null;}
            else
            {
                currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPass));
                String emailtext;
                emailtext = "Resetarea parolei s-a realizat cu succes. \n\t Noua parola este: " + newPass + "" +
                        ". Vă recomandăm să vă schimbați parola la prima accesare a contului.";
                MailRequest mailRequest = new MailRequest();
                mailRequest.setTo(currentUser.getEmailUser());
                mailRequest.setSubject("Your Heart Clinic - Resetare parola");
                Map<String, Object> model = new HashMap<>();
                model.put("action","Resetare parola");
                model.put("body", emailtext);
                emailService.sendmail(mailRequest, model);
                this.userRepository.save(currentUser);
                System.out.println("Password change");
            }
        } else
        // Parola curenta nu se potriveste
        {
            System.out.println("Parola curenta nu se potriveste");
            return null;}

        if(patientRepository.existsByCnp(cnpC)){
            System.out.println("In patienti =>");
            Patient patient = this.patientRepository.findByCnp(cnpC);
            System.out.println(patientRepository.existsByCnp(cnpC));
            System.out.println("newPass =>" + newPass);
            System.out.println("oldPass =>" + oldPass);
            if(this.bCryptPasswordEncoder.matches(oldPass, patient.getPassword()))
            {
                patient.setPassword(this.bCryptPasswordEncoder.encode(newPass));

                this.patientRepository.save(patient);
                System.out.println("Password change patient");
            }
        }


        return currentUser;
    }

    public User changeImage(String cnpU, ChangeImg changeImg){
        User user = userRepository.findByCnp(cnpU);
        if(changeImg.getImageUser() == null){
            return null;
        }
        else{
            user.setImageUser(changeImg.getImageUser());
            userRepository.save(user);
        }
        return user;
    }
    public User deleteImage(String cnpU){
        User user = userRepository.findByCnp(cnpU);
        if(user.getImageUser() == null){
            return null;
        }
        else{
            user.setImageUser("https://www.nicepng.com/png/detail/380-3807237_doctor-tools-vector-png-download-heart-stethoscope.png");
            userRepository.save(user);
        }
        return user;
    }
    public List<User> getAllEmployees(){
        List<User> userList =  userRepository.findAll();
        return userList;
    }

    public User forgotPass(String cnpC) {
        User currentUser = userRepository.findByCnp(cnpC);
        if(currentUser == null)
            return null;
        System.out.println(currentUser);
        String newParola = "parola2";
        String emailtext;
        emailtext = "Resetarea parolei s-a realizat cu succes. \n\t Noua parolă corespunzătoare CNP-ului dumneavoastră este: " + newParola + "" +
                ". Vă recomandăm să vă schimbați parola la prima accesare a contului.";
        MailRequest mailRequest = new MailRequest();
        mailRequest.setTo(currentUser.getEmailUser());
        mailRequest.setSubject("Your Heart Clinic - Resetare parolă");
        Map<String, Object> model = new HashMap<>();
        model.put("action","Resetare parolă");
        model.put("body", emailtext);
        emailService.sendmail(mailRequest, model);
        currentUser.setPassword(this.bCryptPasswordEncoder.encode(newParola));
        this.userRepository.save(currentUser);
        Patient patient = patientRepository.findByCnp(cnpC);
        if (patient == null)
            return null;
        patient.setPassword(this.bCryptPasswordEncoder.encode(newParola));
        this.patientRepository.save(patient);
        System.out.println("Password reset");
        return currentUser;
    }

    /*


    public List<Hospitalization> getAllHospitalizationByUser(Principal principal){
        String username = principal.getName();
        User currentUser = this.userRepository.findUserByCnp(username);

        List<Hospitalization> hospitalizationList = new ArrayList<>();
        for(int i=0; i<hospitalizationRepository.findAll().size(); i++){
            if(hospitalizationRepository.findAll().get(i).getUser().equals(currentUser)){
                if(hospitalizationRepository.findAll().get(i).getEndDateHospitalization() == null)
                hospitalizationList.add(hospitalizationRepository.findAll().get(i));
            }
        }
        return hospitalizationList;
    }
    public List<Hospitalization> getAllHospitalization(Principal principal){
        String username = principal.getName();
        User currentUser = this.userRepository.findUserByCnp(username);

        List<Hospitalization> hospitalizationList = new ArrayList<>();
        for(int i=0; i<hospitalizationRepository.findAll().size(); i++){
            if(hospitalizationRepository.findAll().get(i).getUser().equals(currentUser)){
                if(hospitalizationRepository.findAll().get(i).getEndDateHospitalization() == null)
                    hospitalizationList.add(hospitalizationRepository.findAll().get(i));
            }
        }
        return hospitalizationList;
    }
    public Map<String, Boolean> deleteUser(String cnp) throws UsernameNotFoundException {
        if(!userRepository.existsByCnp(cnp)){
            throw new IllegalStateException("User not found for this cnp :: " + cnp);
        }
        User user = userRepository.findUserByCnp(cnp);

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
*/
}