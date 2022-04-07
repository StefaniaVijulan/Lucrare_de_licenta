package com.medicalclinicapp.medicalclinicapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class MedicalClinicAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalClinicAppApplication.class, args);
	}
}
