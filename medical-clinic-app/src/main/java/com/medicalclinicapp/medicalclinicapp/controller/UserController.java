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
        // add user from exel

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

    @DeleteMapping(value = "/deleteDoctor", params = {"cnp"})
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<String> deleteDoctor(@RequestParam String cnp) throws RuntimeException {
        try {
            System.out.println("auth");
          // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            //System.out.println(auth);
          //  String requsterUsername = auth.getName();
            userService.deleteDoctor(cnp);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<>(cnp, HttpStatus.OK);
    }
    @GetMapping(path = "/user")
    public String user() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping(path = "/admin")
    public String admin() {
        return ("<h1>Welcome Admin </h1>");
    }

    @GetMapping(path = "/home")
    public String home() {
        return ("<h1>Home</h1>");
    }

    }

