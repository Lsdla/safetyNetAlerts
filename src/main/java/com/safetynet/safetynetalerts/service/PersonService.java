package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.DTOs.ChildDTO;
import com.safetynet.safetynetalerts.domain.Person;

import java.util.List;

public interface PersonService {
    Person save(Person thePerson);

    Person findByFirstNameAndLastName(String firstName, String lastName);

    void deleteByFirstNameAndLastName(String firstName, String lastName);

    List<Person> findAll();

    List<Person> findPersonsByFirstNameAndLastName(String firstName, String lastName);

    List<Person> findEmailsByCity(String city);

    List<Person> findByAddress(String address);

    List<Person> findChildrenByAddress(String address);
}
