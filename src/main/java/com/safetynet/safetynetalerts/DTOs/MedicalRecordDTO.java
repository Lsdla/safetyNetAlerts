package com.safetynet.safetynetalerts.DTOs;

import java.util.List;

public class MedicalRecordDTO {

    private Double age;
    private List<String> allergies;
    private List<String> medications;

    public MedicalRecordDTO() {
    }

    public MedicalRecordDTO(Double age, List<String> allergies, List<String> medications) {
        this.age = age;
        this.allergies = allergies;
        this.medications = medications;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }
}
