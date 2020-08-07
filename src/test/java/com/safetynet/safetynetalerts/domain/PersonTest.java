package com.safetynet.safetynetalerts.domain;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("domain")
class PersonTest {

    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person();
    }

    @AfterEach
    void tearDown() {
        person = null;
    }

    @Test
    void getId() {
        Long id = 1L;

        person.setId(id);

        assertEquals(id, person.getId());
    }

    @DisplayName("Get a person's first name")
    @Test
    void getFirstName() {
        String firstName = "John";

        person.setFirstName(firstName);

        assertEquals(firstName, person.getFirstName());
    }

    @DisplayName("Get a person's last name")
    @Test
    void getLastName() {
        String lastName = "Boyd";

        person.setLastName(lastName);

        assertEquals(lastName, person.getLastName());
    }

    @DisplayName("Get a person's address")
    @Test
    void getAddress() {
        String address = "1509 Culver St";

        person.setAddress(address);

        assertEquals(address, person.getAddress());
    }

    @DisplayName("Get the city where a person lives")
    @Test
    void getCity() {
        String city = "Culver";

        person.setCity(city);

        assertEquals(city, person.getCity());
    }

    @DisplayName("Get a person's zip code")
    @Test
    void getZip() {
        String zip = "97451";

        person.setZip(zip);

        assertEquals(zip, person.getZip());
    }

    @DisplayName("Get a person's phone number")
    @Test
    void getPhone() {
        String phone = "841-874-6512";

        person.setPhone(phone);

        assertEquals(phone, person.getPhone());
    }

    @DisplayName("Get a person's email")
    @Test
    void getEmail() {
        String email = "jaboyd@email.com";

        person.setEmail(email);

        assertEquals(email, person.getEmail());
    }

    @DisplayName("Get a person's medical record")
    @Test
    void getMedicalRecord() {
        MedicalRecord medicalRecord = new MedicalRecord();

        person.setMedicalRecord(medicalRecord);

        assertEquals(medicalRecord, person.getMedicalRecord());
    }

    @DisplayName("Get the fire stations that cover a person")
    @Test
    void getFireStations() {
        FireStation fireStation = new FireStation();
        List<FireStation> fireStations = new ArrayList<>();

        fireStation.setAddress("112 Steppes Pl");
        fireStation.setStation(3);
        fireStations.add(fireStation);

        person.setFireStations(fireStations);

        assertEquals(1, person.getFireStations().size());
    }

    @DisplayName("Add a fire station to the person's fire station list")
    @Test
    void addFireStation() {
        FireStation fireStation = new FireStation();

        fireStation.setAddress("112 Steppes Pl");
        fireStation.setStation(3);

        person.addFireStation(fireStation);

        assertEquals(1, person.getFireStations().size());
    }

    @DisplayName("To string method")
    @Test
    void testToString() {
        String expected = person.toString();
        assertEquals(expected, person.toString());
    }
}