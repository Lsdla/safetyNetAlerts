package com.safetynet.safetynetalerts.dtos.floodDto;

import com.safetynet.safetynetalerts.dtos.MedicalRecordDTO;

public class FloodPersonDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private MedicalRecordDTO medicalRecord;

    public FloodPersonDTO() {
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
