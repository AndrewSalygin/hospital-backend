package com.andrewsalygin.dto.patient;

import java.time.LocalDate;

public class PatientWithoutIdDTO {
    private String lastName;
    private String firstName;
    private String middleName;
    private String gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String insuranceInformation;

    public PatientWithoutIdDTO() {}

    public PatientWithoutIdDTO(
        String lastName,
        String firstName,
        String middleName,
        String gender,
        LocalDate dateOfBirth,
        String phoneNumber,
        String insuranceInformation
    ) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.insuranceInformation = insuranceInformation;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInsuranceInformation() {
        return insuranceInformation;
    }

    public void setInsuranceInformation(String insuranceInformation) {
        this.insuranceInformation = insuranceInformation;
    }
}
