package com.medicalclinicapp.medicalclinicapp.dto;

import lombok.Data;

@Data
public class MailRequest {
    private String to;
    private String from;
    private String subject;

}
