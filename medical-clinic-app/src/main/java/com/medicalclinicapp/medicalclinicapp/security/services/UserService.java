package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.security.models.Role;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void registerUser(User user) {
            //verificam daca un user cu email-ul respectiv se gaseste deja
            Optional<User> userOptional = userRepository.findByCnp(user.getCnp());
            if (userOptional.isPresent()) {
                throw new IllegalStateException("Cnp taken");
            }

            //incriptam parola si salvam userul in baza de date
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    @Override
    public UserDetails loadUserByUsername(String cnp) throws UsernameNotFoundException{

        return userRepository.findByCnp(cnp).orElseThrow(() ->
                new UsernameNotFoundException(
                        String.format("username with cnp %s not found", cnp)
                ));
    }
    public Role getRolesFromUser( UserDetails userDetails){
        Role roles= userRepository.findByCnp(userDetails.getUsername()).get().getRole();
        return roles;
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
    public User getUserByCnp(String cnp){
        System.out.println(!userRepository.existsByCnp(cnp));
        if(!userRepository.existsByCnp(cnp))
            throw  new IllegalStateException("User not found for this cnp :: " + cnp);
        else
            return userRepository.findUserByCnp(cnp);
    }
}
