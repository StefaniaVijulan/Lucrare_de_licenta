package com.medicalclinicapp.medicalclinicapp;

import com.medicalclinicapp.medicalclinicapp.dto.MailRequest;
import com.medicalclinicapp.medicalclinicapp.dto.MailResponse;
import com.medicalclinicapp.medicalclinicapp.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration.class})
public class MedicalClinicAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalClinicAppApplication.class, args);
	}

}
