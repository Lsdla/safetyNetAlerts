package com.safetynet.safetynetalerts.DTOs;

public class PersonFireDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private MedicalRecordDTO medicalRecords;

    public PersonFireDTO() {
    }

    public PersonFireDTO(String firstName, String lastName, String phone,
                         MedicalRecordDTO medicalRecords) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.medicalRecords = medicalRecords;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MedicalRecordDTO getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(MedicalRecordDTO medicalRecords) {
        this.medicalRecords = medicalRecords;
    }
}
