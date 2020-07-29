package com.safetynet.safetynetalerts.DTOs;

public class FloodPersonDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private MedicalRecordDTO medicalRecord;

    public FloodPersonDTO() {
    }

    public FloodPersonDTO(String firstName, String lastName, String phone, MedicalRecordDTO medicalRecord) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.medicalRecord = medicalRecord;
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

    public MedicalRecordDTO getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecordDTO medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
