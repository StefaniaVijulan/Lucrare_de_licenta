package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.security.models.Doctor;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String cnp) throws UsernameNotFoundException {

        if(!userRepository.existsById(cnp)){
            throw new UsernameNotFoundException(
                    String.format("username with cnp %s not found", cnp));
        }
        return userRepository.findByCnp(cnp);
    }
    public User loginUser(String cnp, String password){
        if (!userRepository.existsById(cnp)) {
            throw new IllegalStateException("Cnp doesnt exist");
        }

        User userProfile = userRepository.findByCnp(cnp);
        String pass = userProfile.getPassword();
        if (!bCryptPasswordEncoder.matches(password, pass)) {
            throw new IllegalStateException("Cnp doesnt exist");

        }
        return userProfile;
    }
    public User changePassword(String oldPass, String newPass, Principal principal, HttpSession httpSession) throws Exception {
        System.out.println("Old pass" + oldPass);
        System.out.println("New pass" + newPass);

        String username =principal.getName();
        User currentUser = this.userRepository.findByCnp(username);

        if(this.bCryptPasswordEncoder.matches(oldPass, currentUser.getPassword()))
        {
            System.out.println("pass write is the same with the user pass");
            currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPass));
            this.userRepository.save(currentUser);
            System.out.println("Password change");
        }
        else
        {
            System.out.println("pass write is not the same with the user pass");
            throw new Exception("Incorrect username or password");
        }
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
