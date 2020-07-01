package com.safetynet.safetynetalerts.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Lob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "medical_record")
public class MedicalRecord {

    @Id
    @Column(name = "medical_record_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private Date birthDate;


    @Lob
    @Column(name = "allergies")
    private List<String> allergies;

    @Lob
    @Column(name = "medications")
    private List<String> medications;

    public MedicalRecord() {
    }

    public MedicalRecord(String firstName, String lastName,
                         Date birthDate, List<String> allergies,
                         List<String> medications) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.medications = medications;
        this.allergies = allergies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    //a convenience method for adding allergies
    public void addAllergy(String allergy) {
        if (allergies == null) {
            allergies = new ArrayList<>();
        }
        allergies.add(allergy);
    }

    //a convenience method for adding medications
    public void addMedication(String medication) {
        if (medications == null) {
            medications = new ArrayList<>();
        }
        medications.add(medication);
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", allergies=" + allergies +
                ", medications=" + medications +
                '}';
    }
}
