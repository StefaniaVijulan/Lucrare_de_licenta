package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import com.medicalclinicapp.medicalclinicapp.repository.HospitalizationRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.Role;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.ml.distance.CanberraDistance;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HospitalizationRepository hospitalizationRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String registerUser(User user) throws IOException {
            //verificam daca un user cu email-ul respectiv se gaseste deja
            Optional<User> userOptional = userRepository.findByCnp(user.getCnp());
            if (userOptional.isPresent()) {
                throw new IllegalStateException("Cnp taken");
            }
            if(user.getImageUser() == null || user.getImageUser().trim().isEmpty()){
                user.setImageUser("");
            }

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            userRepository.saveAndFlush(user);
            return "Register done";
        };
    /*public void changePhoto(String file, Principal principal){

            String username =principal.getName();
            User currentUser = this.userRepository.findUserByCnp(username);
            try {
                byte[] fileContent =FileUtils.readFileToByteArray(new File(file));
                currentUser.setImage(Base64.getEncoder().encodeToString(fileContent));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    @Override
    public UserDetails loadUserByUsername(String cnp) throws UsernameNotFoundException{

        return userRepository.findByCnp(cnp).orElseThrow(() ->
                new UsernameNotFoundException(
                        String.format("username with cnp %s not found", cnp)
                ));
    }
    public User loginUser(String cnp, String password){
        if (!userRepository.existsByCnp(cnp)) {
            throw new IllegalStateException("Cnp doesnt exist");
        }
        System.out.println( userRepository.findUserByCnp(cnp).getHospitalizationList());
        User user = userRepository.findUserByCnp(cnp);
        String pass = user.getPassword();
        if (!bCryptPasswordEncoder.matches(password, pass)) {
            throw new IllegalStateException("Cnp doesnt exist");

        }
        System.out.println(user.getHospitalizationList());
        return user;
    }
    public User changePassword(String oldPass, String newPass, Principal principal, HttpSession httpSession){
        System.out.println("Old pass" + oldPass);
        System.out.println("New pass" + newPass);

        String username =principal.getName();
        User currentUser = this.userRepository.findUserByCnp(username);

        if(this.bCryptPasswordEncoder.matches(oldPass, currentUser.getPassword()))
        {
            System.out.println("pass write is the same with the user pass");
            currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPass));
            this.userRepository.save(currentUser);
            System.out.println("Pass change");
        }
        else
        {
            System.out.println("pass write is not the same with the user pass");
        }
        return currentUser;

    }
    public List<User> getAllEmployees(){
        List<User> userList =  userRepository.findAll();
        return userList;
    }
    public List<User> getAllDoctors(){
        List<User> doctorList = new ArrayList<>();
        for(int i=0; i<userRepository.findAll().size(); i++){
            if(userRepository.findAll().get(i).getRole().equals(Role.DOCTOR)){
                doctorList.add(userRepository.findAll().get(i));
            }}
        return doctorList;
    }
    public List<User> getAllSecretaries(){
        List<User> doctorList = new ArrayList<>();
        for(int i=0; i<userRepository.findAll().size(); i++){
            if(userRepository.findAll().get(i).getRole().equals(Role.SECRETARY)){
                doctorList.add(userRepository.findAll().get(i));
            }}
        return doctorList;
    }
    public User getUserCnp(String cnp){
        System.out.println(!userRepository.existsByCnp(cnp));
        if(!userRepository.existsByCnp(cnp))
            throw  new IllegalStateException("User not found for this cnp :: " + cnp);
        else
            return userRepository.findUserByCnp(cnp);
    }
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

}
