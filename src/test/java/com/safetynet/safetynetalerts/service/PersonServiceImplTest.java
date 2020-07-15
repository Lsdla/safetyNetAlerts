package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonServiceImplTest {

    PersonServiceImpl personServiceImpl;

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        personServiceImpl = new PersonServiceImpl(personRepository);
    }

    @AfterEach
    void tearDown() {
        personServiceImpl = null;
    }

    @Test
    void findAll() {
        Person person = new Person();
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        when(personRepository.findAll()).thenReturn(personList);

        personServiceImpl.findAll();

        verify(personRepository, times(1)).findAll();

        assertEquals(personList.size(), 1);
    }

    @Test
    void save() {
        Person person = new Person();
        when(personRepository.save(person)).thenReturn(person);
        personServiceImpl.save(person);
        verify(personRepository, times(1)).save(person);

    }

    @Test
    void findByFirstNameAndLastName() {
        personServiceImpl.findByFirstNameAndLastName(anyString(), anyString());
        verify(personRepository, times(1)).findByFirstNameAndLastName(anyString(), anyString());
    }

    @Test
    void deleteByFirstNameAndLastName() {
        personServiceImpl.deleteByFirstNameAndLastName(anyString(), anyString());
        verify(personRepository, times(1)).deleteByFirstNameAndLastName(anyString(), anyString());
    }
}