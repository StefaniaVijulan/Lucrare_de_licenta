package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.security.models.Doctor;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
   /*  /* public void changePhoto(String file, Principal principal){

            String username =principal.getName();
            User currentUser = this.userRepository.findUserByCnp(username);
            try {
                byte[] fileContent =FileUtils.readFileToByteArray(new File(file));
                currentUser.setImage(Base64.getEncoder().encodeToString(fileContent));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
 /*  public UserProfile changePassword(String oldPass, String newPass, Principal principal, HttpSession httpSession){
        System.out.println("Old pass" + oldPass);
        System.out.println("New pass" + newPass);

        String username =principal.getName();
        UserProfile currentUser = this.userProfileRepository.findUserProfileByCnp(username);

        if(this.bCryptPasswordEncoder.matches(oldPass, currentUser.getPassword()))
        {
            System.out.println("pass write is the same with the user pass");
            currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPass));
            this.userProfileRepository.save(currentUser);
            System.out.println("Pass change");
        }
        else
        {
            System.out.println("pass write is not the same with the user pass");
        }
        return currentUser;

    }
    public List<UserProfile> getAllEmployees(){
        List<UserProfile> userProfilesList =  userProfileRepository.findAllUserProfile();
        return userProfilesList;
    }*/
    /*
    public List<UserProfile> getAllDoctors(){
        List<UserProfile> userProfileArrayList = new ArrayList<>();
        for(int i=0; i<userProfileRepository.findAll().size(); i++){
            if(userProfileRepository.findAll().get(i).get().equals(Role.DOCTOR)){
                userProfileArrayList.add(userProfileRepository.findAll().get(i));
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
*/
}
