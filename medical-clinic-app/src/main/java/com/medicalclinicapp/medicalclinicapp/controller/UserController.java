package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.MedicalClinicAppApplication;
import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import com.medicalclinicapp.medicalclinicapp.security.config.JwtUtil;
import com.medicalclinicapp.medicalclinicapp.security.dto.LoginRequest;
import com.medicalclinicapp.medicalclinicapp.security.dto.LoginResponse;
import com.medicalclinicapp.medicalclinicapp.security.models.Role;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.services.UserService;
import com.medicalclinicapp.medicalclinicapp.services.ExcelReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {

        private final UserService userService;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtUtil jwtTokenUtil;

        @Autowired
        private ExcelReadService excelReadService;

        // add user - that can be used just if the user is moderator
        @PostMapping(path = "/register")
        public void registerUser(@RequestBody User user) throws IOException {
            System.out.println(user);
            userService.registerUser(user);
        }
      /*  @PostMapping(path = "/changeUserPhoto")
        public void changePhoto(@ModelAttribute("file") String file, Principal principal)
        {
            userService.changePhoto(file,principal);
        }*/
        @PostMapping(path = "/login")
        public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        //    excelReadService.ReadDataFromExcel("src/main/resources/excelFile/UserDB.xlsx");
           // System.out.println("Intra");

            try {
             //   System.out.println("Intra 1");
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                                    loginRequest.getPassword()));
                }
                catch (BadCredentialsException e) {
                   // System.out.println("Intra 2");
                    throw new Exception("Incorrect username or password", e);
                }

            final UserDetails userDetails = userService
                    .loadUserByUsername(loginRequest.getUsername());
           // System.out.println("Intra 3");
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            User currentUser= userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
          //  System.out.println(jwt);
            return ResponseEntity.ok(new LoginResponse(jwt, currentUser));
        }

        @PostMapping(path="/allUser/changePass")
        public User changePass(@RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass, Principal principal, HttpSession httpSession){
            return userService.changePassword(oldPass, newPass, principal, httpSession);
        }
        @GetMapping("/moderator/allUsers")
        public List<User> getEmployees(){
            return userService.getAllEmployees();
        }

        @GetMapping("/moderator/allDoctors")
        public List<User> getAllDoctors(){
                return userService.getAllDoctors();
            }

        @GetMapping("/moderator/allSecretaries")
        public List<User> getAllSecretaries(){
            return userService.getAllSecretaries();
        }

        @GetMapping("/moderator/userCnp")
        public User getUserByCnp(@RequestParam(value = "cnp") String cnp){

            return userService.getUserCnp(cnp);
        }

        @GetMapping("/user/allHospitalization")
        public List<Hospitalization> getAllHospitalization(Principal principal){
            return userService.getAllHospitalizationByUser(principal);
        }
        @DeleteMapping("/moderator/delete{id}")
        public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") String cnp){
            return userService.deleteUser(cnp);
        }

}

