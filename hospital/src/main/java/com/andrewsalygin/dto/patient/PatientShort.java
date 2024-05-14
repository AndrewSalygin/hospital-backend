package com.andrewsalygin.dto.patient;

import java.time.LocalDate;

public class PatientShort {
    private Integer patientId;
    private String lastName;
    private String firstName;
    private String middleName;
    private String gender;
    private LocalDate dateOfBirth;
    private Boolean isDeleted;

    public PatientShort() {}

    public PatientShort(
        Integer patientId,
        String lastName,
        String firstName,
        String middleName,
        String gender,
        LocalDate dateOfBirth,
        Boolean isDeleted
    ) {
        this.patientId = patientId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.isDeleted = isDeleted;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
