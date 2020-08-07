package com.safetynet.safetynetalerts.domain;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("domain")
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

    @DisplayName("Get medical record id")
    @Test
    void getId_shouldReturnTheCorrectId() {
        Long id = 1L;

        medicalRecord.setId(id);

        assertEquals(id,medicalRecord.getId());
    }

    @DisplayName("Get the first name from the medical record")
    @Test
    void getFirstName_shouldReturnFirstName() {
        String firstName = "John";

        medicalRecord.setFirstName(firstName);

        assertEquals(firstName, medicalRecord.getFirstName());
    }

    @DisplayName("Get last name from medical record")
    @Test
    void getLastName_shouldReturnLastName() {
        String lastName = "Boyd";

        medicalRecord.setLastName(lastName);

        assertEquals(lastName, medicalRecord.getLastName());
    }

    @DisplayName("Get birthdate from medical record")
    @Test
    void getBirthDate_shouldReturnBirthDate() {
        LocalDate birthDate = LocalDate.of(2012, 12, 21);

        medicalRecord.setBirthDate(birthDate);

        assertEquals(birthDate, medicalRecord.getBirthDate());
    }

    @DisplayName("Get allergies from medical record")
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

    @DisplayName("Get medications from medical record")
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

    @DisplayName("Get age name from medical record")
    @Test
    void getAge_shouldReturnAge() {
        Double age = 10.0;
        medicalRecord.setAge(age);

        assertEquals(age, medicalRecord.getAge());
    }

    @DisplayName("Add a new allergy")
    @Test
    void addAllergy_shouldInsertNewAllergy() {
        String allergy = "nillacilan";

        medicalRecord.addAllergy(allergy);

        assertEquals(1, medicalRecord.getAllergies().size());
    }

    @DisplayName("Add a new medication")
    @Test
    void addMedication_shouldInsertNewMedication() {
        String medication = "aznol:350mg";

        medicalRecord.addMedication(medication);

        assertEquals(1, medicalRecord.getMedications().size());
    }

    @DisplayName("To string method")
    @Test
    void toString_shouldReturnDescription() {
        String expected = medicalRecord.toString();
        assertEquals(expected, medicalRecord.toString());
    }
}