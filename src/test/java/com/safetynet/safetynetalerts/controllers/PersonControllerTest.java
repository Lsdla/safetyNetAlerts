package com.safetynet.safetynetalerts.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
class PersonControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    PersonService personService;


    private Person newPerson() {
        Person person = new Person();
        person.setId(1L);
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");
        return person;
      }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findPersons() throws Exception{
        List<Person> persons = new ArrayList<>();
        persons.add(newPerson());
        when(personService.findAll()).thenReturn(persons);
        mockMvc.perform(get("/person/persons")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Boyd"))
                .andExpect(jsonPath("$[0].address").value("1509 Culver St"))
                .andExpect(jsonPath("$[0].city").value("Culver"))
                .andExpect(jsonPath("$[0].zip").value("97451"))
                .andExpect(jsonPath("$[0].phone").value("841-874-6512"))
                .andExpect(jsonPath("$[0].email").value("jaboyd@email.com"));




    }

    @Test
    void addPerson() throws Exception{
        when(personService.save(any(Person.class))).thenReturn(newPerson());

        mockMvc.perform(MockMvcRequestBuilders.post("/person/add")
                .content(new ObjectMapper().writeValueAsString(newPerson()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Boyd"))
                .andExpect(jsonPath("$.address").value("1509 Culver St"))
                .andExpect(jsonPath("$.city").value("Culver"))
                .andExpect(jsonPath("$.zip").value("97451"))
                .andExpect(jsonPath("$.phone").value("841-874-6512"))
                .andExpect(jsonPath("$.email").value("jaboyd@email.com"));

    }

    @Test
    void updatePerson() throws Exception{
        when(personService.save(any(Person.class))).thenReturn(newPerson());

        mockMvc.perform(MockMvcRequestBuilders.put("/person/update")
                .content(new ObjectMapper().writeValueAsString(newPerson()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Boyd"))
                .andExpect(jsonPath("$.address").value("1509 Culver St"))
                .andExpect(jsonPath("$.city").value("Culver"))
                .andExpect(jsonPath("$.zip").value("97451"))
                .andExpect(jsonPath("$.phone").value("841-874-6512"))
                .andExpect(jsonPath("$.email").value("jaboyd@email.com"));
    }

    @Test
    void deletePerson() throws Exception{
        when(personService.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(newPerson());

        mockMvc.perform(MockMvcRequestBuilders.delete("/person/delete/John&Boyd"))
                .andExpect(status().isOk());
    }
}