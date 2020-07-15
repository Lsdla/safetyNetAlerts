package com.safetynet.safetynetalerts.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
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
    void getId() {
        Long id = 1L;

        medicalRecord.setId(id);

        assertEquals(id,medicalRecord.getId());
    }

    @Test
    void getFirstName() {
        String firstName = "John";

        medicalRecord.setFirstName(firstName);

        assertEquals(firstName, medicalRecord.getFirstName());
    }

    @Test
    void getLastName() {
        String lastName = "Boyd";

        medicalRecord.setLastName(lastName);

        assertEquals(lastName, medicalRecord.getLastName());
    }

    @Test
    void getBirthDate() {
        Date birthDate = new Date();
        birthDate.getTime();

        medicalRecord.setBirthDate(birthDate);

        assertEquals(birthDate, medicalRecord.getBirthDate());
    }

    @Test
    void getAllergies() {
        List<String> allergies = new ArrayList<>();

        String allergy = "nillacilan";
        String allergy2 = "shellfish";

        allergies.add(allergy);
        allergies.add(allergy2);

        medicalRecord.setAllergies(allergies);

        assertEquals(2, medicalRecord.getAllergies().size());
    }

    @Test
    void getMedications() {
        List<String> medications = new ArrayList<>();

        String medication = "aznol:350mg";
        String medication2 = "hydrapermazol:100mg";

        medications.add(medication);
        medications.add(medication2);

        medicalRecord.setMedications(medications);

        assertEquals(2, medicalRecord.getMedications().size());
    }

    @Test
    void addAllergy() {
        String allergy = "nillacilan";

        medicalRecord.addAllergy(allergy);

        assertEquals(1, medicalRecord.getAllergies().size());

    }

    @Test
    void addMedication() {
        List<String> medications = null;
        String medication = "aznol:350mg";

        medicalRecord.addMedication(medication);

        assertEquals(1, medicalRecord.getMedications().size());
    }

    @Test
    void testToString() {
        String expected = medicalRecord.toString();
        assertEquals(expected, medicalRecord.toString());
    }
}