package com.medicalclinicapp.medicalclinicapp.security.models;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    private String cnp;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "emailUser")
    private String emailUser;

    //cardiology, radiology
    @Column(name = "specialty")
    private String specialty;

    @Column(name = "password")
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public User(String cnp, String firstName, String lastName, String emailUser, String specialty, String password, Role role) {
        this.cnp = cnp;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailUser = emailUser;
        this.specialty = specialty;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }
}
