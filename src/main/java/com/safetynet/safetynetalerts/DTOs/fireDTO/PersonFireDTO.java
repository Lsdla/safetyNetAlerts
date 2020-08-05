package com.safetynet.safetynetalerts.DTOs.fireDTO;

import com.safetynet.safetynetalerts.DTOs.MedicalRecordDTO;

import java.util.List;

public class PersonFireDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private MedicalRecordDTO medicalRecord;
    private List<FireStationFireDTO> fireStations;

    public PersonFireDTO() {
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

    public List<FireStationFireDTO> getFireStations() {
        return fireStations;
    }

    public void setFireStations(List<FireStationFireDTO> fireStations) {
        this.fireStations = fireStations;
    }
}
