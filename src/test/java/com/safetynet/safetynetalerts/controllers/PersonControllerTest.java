package com.safetynet.safetynetalerts.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.DTOs.FireStationDTO;
import com.safetynet.safetynetalerts.DTOs.MedicalRecordDTO;
import com.safetynet.safetynetalerts.DTOs.PersonDTO;
import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.DisplayName;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;


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

    @DisplayName("GET: OK retrieving all persons from DB")
    @Test
    void givenAListOfPersonDTOs_whenGetRequestIsSent_thenTheListShouldBeReturned() throws Exception{
        List<PersonDTO> personDTOS = new ArrayList<>();
        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName("John");
        personDTO.setLastName("Boyd");
        personDTO.setAddress("1509 Culver St");
        personDTO.setCity("Culver");
        personDTO.setZip("97451");
        personDTO.setPhone("841-874-6512");
        personDTO.setEmail("jaboyd@email.com");
        List<FireStationDTO> fireStationDTOS = new ArrayList<>();
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        medicalRecordDTO.setAge(11.2);
        List<String> allergies = new ArrayList<>();
        List<String> medications = new ArrayList<>();
        medicalRecordDTO.setAllergies(allergies);
        medicalRecordDTO.setMedications(medications);
        personDTO.setMedicalRecord(medicalRecordDTO);
        personDTO.setFireStations(fireStationDTOS);
        personDTOS.add(personDTO);
        when(personService.findAll()).thenReturn(personDTOS);
        mockMvc.perform(get("/person/persons")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Boyd"))
                .andExpect(jsonPath("$[0].address").value("1509 Culver St"))
                .andExpect(jsonPath("$[0].city").value("Culver"))
                .andExpect(jsonPath("$[0].zip").value("97451"))
                .andExpect(jsonPath("$[0].phone").value("841-874-6512"))
                .andExpect(jsonPath("$[0].email").value("jaboyd@email.com"));
    }

    @DisplayName("POST: OK saving a new person")
    @Test
    void givenANewPerson_whenPostRequestIsSent_thenPersonShouldBeSaved() throws Exception{
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

    @DisplayName("PUT OK: Updating an existing person")
    @Test
    void givenAnExistingPerson_whenPutRequestIsSent_thenPersonShouldBeUpdated() throws Exception{
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

    @DisplayName("PUT ERROR: error when trying to update an non-existing person")
    @Test
    void givenANonExistingPerson_whenPutRequestIsSent_thenResponseShouldBe4xx() throws Exception{
        when(personService.save(newPerson())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/person/update"))
                .andExpect(status().is4xxClientError());
    }

    @DisplayName("DELETE OK: Deleting a person from DB")
    @Test
    void givenAnExistingPerson_whenDeleteRequestIsSent_thenResponseShouldBeOk() throws Exception{
        when(personService.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(newPerson());

        mockMvc.perform(MockMvcRequestBuilders.delete("/person/delete/John&Boyd"))
                .andExpect(status().isOk());
    }

    @DisplayName("DELETE ERROR: error returned if no matching person credentials were found in DB")
    @Test
    void givenWrongPersonCredentials_whenDeleteRequestIsSent_thenResponseShouldBe4xx() throws Exception {
        when(personService.findByFirstNameAndLastName("John", "Boyd")).thenReturn(newPerson());
        mockMvc.perform(MockMvcRequestBuilders.delete("/person/delete/Jo&Boyd"))
                .andExpect(status().is4xxClientError());

    }

    @DisplayName("GET Ok: getting person information")
    @Test
    void givenPersonFirstNameAndLastName_whenGetRequestIsSent_thenResponseShouldOk() throws Exception {
        when(personService.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(newPerson());

        mockMvc.perform(get("/person/personInfo/John&Boyd")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}