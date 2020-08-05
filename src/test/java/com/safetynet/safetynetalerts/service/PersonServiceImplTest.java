package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.convertor.PersonConverter;
import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class PersonServiceImplTest {

    private PersonServiceImpl personServiceImpl;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonConverter personConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        personServiceImpl = new PersonServiceImpl(personRepository, personConverter);
    }

    @AfterEach
    void tearDown() {
        personServiceImpl = null;
    }

    @Test
    void findAll_shouldCallTheAppropriateMethodInPersonRepository() {
        List<Person> personList = new ArrayList<>();

        when(personRepository.findAll()).thenReturn(personList);

        personServiceImpl.findAll();

        verify(personRepository, times(1)).findAll();
    }

    @Test
    void save_shouldCallTheAppropriateMethodInPersonRepository() {
        Person person = new Person();
        when(personRepository.save(person)).thenReturn(person);
        personServiceImpl.save(person);
        verify(personRepository, times(1)).save(person);
    }

    @Test
    void findByFirstNameAndLastName_shouldCallTheAppropriateMethodInPersonRepository() {
        personServiceImpl.findByFirstNameAndLastName(anyString(), anyString());
        verify(personRepository, times(1)).findByFirstNameAndLastName(anyString(), anyString());
    }

    @Test
    void deleteByFirstNameAndLastName_shouldCallTheAppropriateMethodInPersonRepository() {
        personServiceImpl.deleteByFirstNameAndLastName(anyString(), anyString());
        verify(personRepository, times(1)).deleteByFirstNameAndLastName(anyString(), anyString());
    }

    @Test
    void findPersonsByFirstNameAndLastName_shouldCallTheAppropriateMethodInPersonRepository() {
        personServiceImpl.findPersonsByFirstNameAndLastName(anyString(), anyString());
        verify(personRepository, times(1)).findPersonsByFirstNameAndLastName(anyString(), anyString());
    }

    @Test
    void findEmailsByCity_shouldCallTheAppropriateMethodInPersonRepository() {
        personServiceImpl.findEmailsByCity(anyString());
        verify(personRepository, times(1)).findEmailsByCity(anyString());
    }

    @Test
    void findByAddress_shouldCallTheAppropriateMethodInPersonRepository() {
        personServiceImpl.findByAddress(anyString());
        verify(personRepository, times(1)).findByAddress(anyString());
    }

    @Test
    void findChildrenByAddress_shouldCallTheAppropriateMethodInPersonRepository() {
        personServiceImpl.findChildrenByAddress(anyString());
        verify(personRepository, times(1)).findByAddress(anyString());
    }

    @Test
    void retrievePeopleByAddress_shouldCallTheAppropriateMethodInPersonRepository() {
        personServiceImpl.retrievePeopleByAddress(anyString());
        verify(personRepository, times(1)).findByAddress(anyString());
    }
}