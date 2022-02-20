package com.medicalclinicapp.medicalclinicapp;

import com.medicalclinicapp.medicalclinicapp.security.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedicalClinicAppApplication {

	private static User currentUser;
	public static void setCurrentUser(User user){
		currentUser = null;
	}
	public static User getCurrentUser(){
		return currentUser;
	}
	public static void main(String[] args) {
		SpringApplication.run(MedicalClinicAppApplication.class, args);
	}

}
