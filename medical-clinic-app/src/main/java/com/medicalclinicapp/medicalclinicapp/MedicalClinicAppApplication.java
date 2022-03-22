package com.medicalclinicapp.medicalclinicapp;

import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.services.ExcelReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;

@SpringBootApplication
public class MedicalClinicAppApplication implements CommandLineRunner {

	@Autowired
	ExcelReadService excelReadService;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(MedicalClinicAppApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Ana");
		excelReadService.ReadDataFromExcel("src/main/resources/excelFile/UserDB.xlsx");
		System.out.println("Bana");
	}

}
