package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.security.config.JwtUtil;
import com.medicalclinicapp.medicalclinicapp.security.dto.LoginRequest;
import com.medicalclinicapp.medicalclinicapp.security.dto.LoginResponse;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserService userService;
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        //    excelReadService.ReadDataFromExcel("src/main/resources/excelFile/UserDB.xlsx");
        // System.out.println("Intra");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userService
                .loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        User currentUser= userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new LoginResponse(jwt, currentUser));
    }

    @GetMapping(path="/changePass")
    public User changePass(@RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass, @RequestParam("cnp")String cnp) throws Exception {
        return userService.changePassword(oldPass, newPass, cnp);
    }


       /*



        @GetMapping("/user/allHospitalizationCurrent")
        public List<Hospitalization> getAllHospitalizationCurrent(Principal principal){
            return userService.getAllHospitalizationByUser(principal);
        }
        @GetMapping("/user/allHospitalization")
        public List<Hospitalization> getAllHospitalization(Principal principal){
            return userService.getAllHospitalization(principal);
        }
        @DeleteMapping("/moderator/delete{id}")
        public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") String cnp){
            return userService.deleteUser(cnp);
        }
*/
}

