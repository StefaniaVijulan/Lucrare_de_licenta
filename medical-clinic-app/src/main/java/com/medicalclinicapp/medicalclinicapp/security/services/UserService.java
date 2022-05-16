package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.repository.PatientRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.repository.UserRepository;
import com.medicalclinicapp.medicalclinicapp.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;


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
        System.out.println("Old pass" + oldPass);
        System.out.println("New pass" + newPass);
        String username;
        username = userRepository.findByCnp(cnpC).getCnp();
        User currentUser = this.userRepository.findByCnp(username);
        if(this.bCryptPasswordEncoder.matches(oldPass, currentUser.getPassword()))
        {
            if(oldPass.equals(newPass))
            //"Noua parola este la fel cu parola curenta");
            {System.out.println("Noua parola este la fel cu parola curenta");
                return null;}
            else
            {
                currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPass));
                String emailtext;
                emailtext = "Buna " + currentUser.getLastName() + " " + currentUser.getFirstName() +",\n\n Parola ta a fost schimbata cu succes!" ;
                emailService.sendmail(currentUser.getEmailUser(),"Medical Clinic App - Schimbare Parola",emailtext);
                this.userRepository.save(currentUser);
                System.out.println("Password change");
            }
        } else
        // Parola curenta nu se potriveste
        {
            System.out.println("Parola curenta nu se potriveste");
            return null;}

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