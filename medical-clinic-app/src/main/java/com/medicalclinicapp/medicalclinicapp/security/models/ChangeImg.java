package com.medicalclinicapp.medicalclinicapp.security.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@RequiredArgsConstructor
public class ChangeImg {
    private String imageUser;

}
