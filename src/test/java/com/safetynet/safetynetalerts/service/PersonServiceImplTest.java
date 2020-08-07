package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.convertor.PersonConverter;
import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.repository.PersonRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@Tag("Service")
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

    @DisplayName("findAll calls personRepository.findAll")
    @Test
    void findAll_shouldCallTheAppropriateMethodInPersonRepository() {
        List<Person> personList = new ArrayList<>();

        when(personRepository.findAll()).thenReturn(personList);

        personServiceImpl.findAll();

        verify(personRepository, times(1)).findAll();
    }

    @DisplayName("save calls personRepository.save")
    @Test
    void save_shouldCallTheAppropriateMethodInPersonRepository() {
        Person person = new Person();
        when(personRepository.save(person)).thenReturn(person);
        personServiceImpl.save(person);
        verify(personRepository, times(1)).save(person);
    }

    @DisplayName("findByFirstNameAndLastName calls personRepository.findByFirstNameAndLastName")
    @Test
    void findByFirstNameAndLastName_shouldCallTheAppropriateMethodInPersonRepository() {
        personServiceImpl.findByFirstNameAndLastName(anyString(), anyString());
        verify(personRepository, times(1)).findByFirstNameAndLastName(anyString(), anyString());
    }

    @DisplayName("deleteByFirstNameAndLastName calls personRepository.deleteByFirstNameAndLastName")
    @Test
    void deleteByFirstNameAndLastName_shouldCallTheAppropriateMethodInPersonRepository() {
        personServiceImpl.deleteByFirstNameAndLastName(anyString(), anyString());
        verify(personRepository, times(1)).deleteByFirstNameAndLastName(anyString(), anyString());
    }

    @DisplayName("findPersonsByFirstNameAndLastName calls personRepository.findAll")
    @Test
    void findPersonsByFirstNameAndLastName_shouldCallTheAppropriateMethodInPersonRepository() {
        personServiceImpl.findPersonsByFirstNameAndLastName(anyString(), anyString());
        verify(personRepository, times(1)).findPersonsByFirstNameAndLastName(anyString(), anyString());
    }

    @DisplayName("findEmailsByCity calls personRepository.findEmailsByCity")
    @Test
    void findEmailsByCity_shouldCallTheAppropriateMethodInPersonRepository() {
        personServiceImpl.findEmailsByCity(anyString());
        verify(personRepository, times(1)).findEmailsByCity(anyString());
    }

    @DisplayName("findByAddress calls personRepository.findByAddress")
    @Test
    void findByAddress_shouldCallTheAppropriateMethodInPersonRepository() {
        personServiceImpl.findByAddress(anyString());
        verify(personRepository, times(1)).findByAddress(anyString());
    }

    @DisplayName("findChildrenByAddress calls personRepository.findByAddress")
    @Test
    void findChildrenByAddress_shouldCallTheAppropriateMethodInPersonRepository() {
        personServiceImpl.findChildrenByAddress(anyString());
        verify(personRepository, times(1)).findByAddress(anyString());
    }

    @DisplayName("retrievePeopleByAddress calls personRepository.retrievePeopleByAddress")
    @Test
    void retrievePeopleByAddress_shouldCallTheAppropriateMethodInPersonRepository() {
        personServiceImpl.retrievePeopleByAddress(anyString());
        verify(personRepository, times(1)).findByAddress(anyString());
    }
}