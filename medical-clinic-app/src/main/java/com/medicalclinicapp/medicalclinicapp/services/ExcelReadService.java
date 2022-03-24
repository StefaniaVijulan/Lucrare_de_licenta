package com.medicalclinicapp.medicalclinicapp.services;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.medicalclinicapp.medicalclinicapp.repository.SaveUserDataToDBRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.Role;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import lombok.AllArgsConstructor;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

@Service
@AllArgsConstructor
public class ExcelReadService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    SaveUserDataToDBRepository saveUserDataToDBRepository;



    public void ReadDataFromExcel(String excelPath) throws EncryptedDocumentException, InvalidFormatException, IOException {

        Workbook workbook = WorkbookFactory.create(new File(excelPath));
        // Retrieving the number of sheets in the Workbook
      //  System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
       // System.out.println("Retrieving Sheets using for-each loop");
        for (Sheet sheet : workbook) {
           // System.out.println("=> " + sheet.getSheetName());
            for (Row row : sheet) {

                //CNP
                String excelCnp = row.getCell(0).getStringCellValue();
                String excelFname = row.getCell(1).getStringCellValue();
                String excelLname = row.getCell(2).getStringCellValue();
                String exceEmail = row.getCell(3).getStringCellValue();
                String excelSpec = row.getCell(4).getStringCellValue();
                String excelPassword = row.getCell(5).getStringCellValue();
                String excelRole = row.getCell(6).getStringCellValue();
                Role roles;
                if( excelRole.toUpperCase().equals("DOCTOR"))
                    roles = Role.DOCTOR;
                else if(excelRole.toUpperCase().equals("MODERATOR"))
                    roles = Role.MODERATOR;
                else
                    roles = Role.SECRETARY;
                User newUser = new User();
                newUser.setCnp(excelCnp);
                newUser.setEmailUser(exceEmail);
                newUser.setRole(roles);
                newUser.setFirstName(excelFname);
                newUser.setLastName(excelLname);
                newUser.setSpecialty(excelSpec);
                newUser.setPassword(bCryptPasswordEncoder.encode(excelPassword));
              //  System.out.println(newUser);
                saveUserDataToDBRepository.save(newUser);
            }
        }
    }
}
