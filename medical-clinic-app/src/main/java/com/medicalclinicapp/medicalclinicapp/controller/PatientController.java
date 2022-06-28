package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.models.*;
import com.medicalclinicapp.medicalclinicapp.security.config.JwtUtil;
import com.medicalclinicapp.medicalclinicapp.security.dto.LoginPatientResponse;
import com.medicalclinicapp.medicalclinicapp.security.dto.LoginRequest;
import com.medicalclinicapp.medicalclinicapp.security.dto.LoginResponse;
import com.medicalclinicapp.medicalclinicapp.security.models.Cardiolog;
import com.medicalclinicapp.medicalclinicapp.security.models.User;

import com.medicalclinicapp.medicalclinicapp.security.services.UserService;
import com.medicalclinicapp.medicalclinicapp.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class PatientController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private PatientService patientService;

    @PostMapping(path = "/loginPatient")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        //    excelReadService.ReadDataFromExcel("src/main/resources/excelFile/UserDB.xlsx");

        try {
            System.out.println("Intra in try");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));

        }
        catch (BadCredentialsException e) {
            return null;
        }
        final UserDetails userDetails = userService
                .loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        Patient currentUser= patientService.loginPatient(loginRequest.getUsername(), loginRequest.getPassword());
        System.out.println(currentUser);
        return ResponseEntity.ok(new LoginPatientResponse(jwt, currentUser));
    }

    @PostMapping(path = "/pacient/addAppointment")
    public int addAppointment(@RequestParam(value = "cnpP") String cnpP, @RequestParam(value = "cnpC")String cnpC, @RequestBody Appointment appointment) throws ParseException {
        return patientService.addAppointment(cnpP, cnpC, appointment);
    }

    @GetMapping("/pacient/allCardiolog")
    public List<Cardiolog> getAllCardiolog(){
        return patientService.allCardio();
    }
    @GetMapping("/pacient/nextAppointment")
    public Appointment getNextAppointment(@RequestParam(value = "cnpP") String cnpP) throws ParseException {
        return patientService.getNextAppointment(cnpP);
    }
    @GetMapping("/pacient/nextAppointmentHematology")
    public AppointmentHematology getNextAppointmentH(@RequestParam(value = "cnpP") String cnpP) throws ParseException {
        return patientService.getNextAppointmentHematology(cnpP);
    }
    @GetMapping("/pacient/nextAppointmentRadiology")
    public AppointmentRadiology getNextAppointmentR(@RequestParam(value = "cnpP") String cnpP) throws ParseException {
        return patientService.getNextAppointmentRadiology(cnpP);
    }
    @GetMapping("/pacient/blockDateForCardio")
    public List<String> verificaDispDateCardio(@RequestParam (value = "cnpC") String cnpC){
        return patientService.verificaDisponibilitateDoctor(cnpC);
    }
    @GetMapping("/pacient/checkAvailabilityHourCardio")
    public List<String> verificaDispHourCardio(@RequestParam(value = "cnpC") String cnpC,@RequestParam("date")String data){
        System.out.println("Inta in controller");
        return patientService.verificaHoursDoctor(cnpC, data);
    }
    @PutMapping("/pacient/reprogrameazaAppointment")
    public Appointment reprogrameazaAppointment(@RequestParam(value = "cnpP") String cnpP, @RequestBody Appointment appointment) throws ParseException {
        return patientService.reprogrameazaAppointment(cnpP, appointment);
    }
    @PutMapping("/pacient/reprogrameazaAppointmentHematology")
    public AppointmentHematology reprogrameazaAppointment(@RequestParam(value = "cnpP") String cnpP, @RequestBody AppointmentHematology appointment) throws ParseException {
        return patientService.reprogrameazaAppointmentH(cnpP, appointment);
    }
    @PutMapping("/pacient/reprogrameazaAppointmentRadiology")
    public AppointmentRadiology reprogrameazaAppointment(@RequestParam(value = "cnpP") String cnpP, @RequestBody AppointmentRadiology appointment) throws ParseException {
        return patientService.reprogrameazaAppointmentR(cnpP, appointment);
    }
    @GetMapping("/pacient/blockDateHematology")
    public List<String> verificaDisp(){
        return patientService.verificaDisponibilitateHematology();
    }
    @GetMapping("/pacient/checkAvailabilityHematology")
    public List<String> verificaDispHour(@RequestParam("dateA")String data){
        return patientService.verificaHoursHematology(data);
    }
    @GetMapping("/pacient/blockDateRadiology")
    public List<String> verificaDispRad(){
        return patientService.verificaDisponibilitateRadiology();
    }
    @GetMapping("/pacient/checkAvailabilityRadiology")
    public List<String> verificaDispHourRad(@RequestParam("dateA")String data){
        return patientService.verificaHoursRadiology(data);
    }
    @GetMapping("/pacient/radiologyResult")
    public List<RadiologyResult> getAllRadiologyResult(@RequestParam("cnpP") String cnpP){ return patientService.allRadiologyResult(cnpP);
    }
    @GetMapping("/pacient/hematologyResult")
    public List<HematologyResult> getAllHematologyResult(@RequestParam("cnpP") String cnpP){
        return patientService.allHematologyResult(cnpP);
    }
}
