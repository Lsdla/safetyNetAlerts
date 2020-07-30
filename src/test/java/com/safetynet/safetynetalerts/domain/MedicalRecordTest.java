package com.safetynet.safetynetalerts.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedicalRecordTest {

    private MedicalRecord medicalRecord;

    @BeforeEach
    void setUp() {
        medicalRecord = new MedicalRecord();
    }

    @AfterEach
    void tearDown() {
        medicalRecord = null;
    }

    @Test
    void getId_shouldReturnTheCorrectId() {
        Long id = 1L;

        medicalRecord.setId(id);

        assertEquals(id,medicalRecord.getId());
    }

    @Test
    void getFirstName_shouldReturnFirstName() {
        String firstName = "John";

        medicalRecord.setFirstName(firstName);

        assertEquals(firstName, medicalRecord.getFirstName());
    }

    @Test
    void getLastName_shouldReturnLastName() {
        String lastName = "Boyd";

        medicalRecord.setLastName(lastName);

        assertEquals(lastName, medicalRecord.getLastName());
    }

    @Test
    void getBirthDate_shouldReturnBirthDate() {
        LocalDate birthDate = LocalDate.of(2012, 12, 21);

        medicalRecord.setBirthDate(birthDate);

        assertEquals(birthDate, medicalRecord.getBirthDate());
    }

    @Test
    void getAllergies_shouldReturnAllergiesList() {
        List<String> allergies = new ArrayList<>();

        String allergy = "nillacilan";
        String allergy2 = "shellfish";

        allergies.add(allergy);
        allergies.add(allergy2);

        medicalRecord.setAllergies(allergies);

        assertEquals(2, medicalRecord.getAllergies().size());
    }

    @Test
    void getMedications_shouldReturnMedicationsList() {
        List<String> medications = new ArrayList<>();

        String medication = "aznol:350mg";
        String medication2 = "hydrapermazol:100mg";

        medications.add(medication);
        medications.add(medication2);

        medicalRecord.setMedications(medications);

        assertEquals(2, medicalRecord.getMedications().size());
    }

    @Test
    void getAge_shouldReturnAge() {
        Double age = 10.0;
        medicalRecord.setAge(age);

        assertEquals(age, medicalRecord.getAge());
    }

    @Test
    void addAllergy_shouldInsertNewAllergy() {
        String allergy = "nillacilan";

        medicalRecord.addAllergy(allergy);

        assertEquals(1, medicalRecord.getAllergies().size());
    }

    @Test
    void addMedication_shouldInsertNewMedication() {
        String medication = "aznol:350mg";

        medicalRecord.addMedication(medication);

        assertEquals(1, medicalRecord.getMedications().size());
    }

    @Test
    void toString_shouldReturnDescription() {
        String expected = medicalRecord.toString();
        assertEquals(expected, medicalRecord.toString());
    }
}