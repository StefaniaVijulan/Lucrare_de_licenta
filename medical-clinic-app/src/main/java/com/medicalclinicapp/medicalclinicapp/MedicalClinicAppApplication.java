package com.medicalclinicapp.medicalclinicapp;

import com.medicalclinicapp.medicalclinicapp.security.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication

public class MedicalClinicAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalClinicAppApplication.class, args);
	}

}
