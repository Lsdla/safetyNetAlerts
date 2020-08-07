package com.safetynet.safetynetalerts.domain;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("domain")
class FireStationTest {

    FireStation fireStation;

    @BeforeEach
    void setUp() {
        fireStation = new FireStation();
    }

    @AfterEach
    void tearDown() {
        fireStation = null;
    }

    @DisplayName("Get the fire station's id")
    @Test
    void getId() {
        Long id = 1L;

        fireStation.setId(id);

        assertEquals(id, fireStation.getId());
    }

    @DisplayName("Get the fire station's address")
    @Test
    void getAddress() {
        String address = "station address";

        fireStation.setAddress(address);

        assertEquals(address, fireStation.getAddress());
    }

    @DisplayName("get station number")
    @Test
    void getStation() {
        Integer station = 1;

        fireStation.setStation(station);

        assertEquals(station, fireStation.getStation());
    }

    @DisplayName("Get persons covered by a fire station")
    @Test
    void getPersons() {

        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setId(1L);
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("123123123");
        person.setEmail("email@email.com");
        personList.add(person);

        fireStation.setPersons(personList);

        assertEquals(1, fireStation.getPersons().size());
    }

    @DisplayName("Add a person to a fire station")
    @Test
    void addPerson() {
        Person person = new Person();
        person.setId(1L);
        person.setFirstName("Jhon");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("123123123");
        person.setEmail("email@email.com");

        fireStation.addPerson(person);
        assertEquals(1, fireStation.getPersons().size());
    }

    @DisplayName("To string")
    @Test
    void testToString() {
        String expected = fireStation.toString();

        assertEquals(expected, fireStation.toString());
    }
}