package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.MedicalClinicAppApplication;
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

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;


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
        public void registerUser(@RequestBody User user) {
            System.out.println(user);
            userService.registerUser(user);
        }

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
            Role roll =  userService.getRolesFromUser(userDetails);
          //  System.out.println(jwt);
            return ResponseEntity.ok(new LoginResponse(jwt, roll));
        }

        @PostMapping(path="/user/changePass")
        public User changePass(@RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass, Principal principal, HttpSession httpSession){
            return userService.changePassword(oldPass, newPass, principal, httpSession);
        }
        @GetMapping("/user/all")
        public List<User> getEmployees(){
            return userService.getAllEmployees();
        }

        @GetMapping("/doctor/all")
        public List<User> getAllDoctors(){
                return userService.getAllDoctors();
            }

        @GetMapping("/secretaries/all")
        public List<User> getAllSecretaries(){
            return userService.getAllSecretaries();
        }

        @GetMapping("/user")
        public User getUserByCnp(@RequestParam(value = "cnp") String cnp){

            return userService.getUserCnp(cnp);
        }
}

