package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.domain.Person;

public interface PersonService {
    void save(Person thePerson);

    Person findByFirstNameAndLastName(String firstName, String lastName);

    void deleteByFirstNameAndLastName(String firstName, String lastName);
}
