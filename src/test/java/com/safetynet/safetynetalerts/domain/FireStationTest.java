package com.safetynet.safetynetalerts.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void getId() {
        Long id = 1L;

        fireStation.setId(id);

        assertEquals(id, fireStation.getId());
    }

    @Test
    void getAddress() {
        String address = "station address";

        fireStation.setAddress(address);

        assertEquals(address, fireStation.getAddress());
    }

    @Test
    void getStation() {
        String station = "1";

        fireStation.setStation(station);

        assertEquals(station, fireStation.getStation());
    }

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

    @Test
    void testToString() {
        String expected = fireStation.toString();

        assertEquals(expected, fireStation.toString());
    }
}