package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.domain.Person;

import java.util.List;

public interface PersonService {
    Person save(Person thePerson);

    Person findByFirstNameAndLastName(String firstName, String lastName);

    void deleteByFirstNameAndLastName(String firstName, String lastName);

    List<Person> findAll();
}
