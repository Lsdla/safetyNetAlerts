package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.MedicalRecordDTO;
import com.safetynet.safetynetalerts.dtos.firedto.PersonFireDTO;
import com.safetynet.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("controllers")
@RunWith(SpringRunner.class)
@WebMvcTest(FireController.class)
class FireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @DisplayName("GET OK: fire controller returns a list persons who live in a give address")
    @Test
    void givenAListOfPersons_whenGetMethodIsSentByFireController_thenTheListShouldBeReturned() throws Exception {
        List<PersonFireDTO> persons = new ArrayList<>();
        PersonFireDTO person = new PersonFireDTO();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setPhone("123456789");

        MedicalRecordDTO medicalRecord = new MedicalRecordDTO();
        medicalRecord.setAge(109.0);
        List<String> allergies = new ArrayList<>();
        allergies.add("allergy");
        medicalRecord.setAllergies(allergies);
        List<String> medications = new ArrayList<>();
        medications.add("medication");
        medicalRecord.setMedications(medications);
        person.setMedicalRecord(medicalRecord);
        persons.add(person);

        when(personService.retrievePeopleByAddress(anyString())).thenReturn(persons);

        mockMvc.perform(get("/fire?address=address")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$.[0]firstName").value(person.getFirstName()))
                .andExpect(jsonPath("$.[0]lastName").value(person.getLastName()))
                .andExpect(jsonPath("$.[0]phone").value(person.getPhone()))
                .andExpect(jsonPath("$.[0]medicalRecord.age").value(person.getMedicalRecord().getAge()))
                .andExpect(jsonPath("$.[0]medicalRecord.allergies").value(person.getMedicalRecord().getAllergies()))
                .andExpect(jsonPath("$.[0]medicalRecord.medications").value(person.getMedicalRecord().getMedications()))
                .andExpect(jsonPath("$.[0]fireStations").value(person.getFireStations()));
    }

    @DisplayName("GET ERROR: error returned if the provided address does not eexist")
    @Test
    void givenANonExistingAddress_whenGetMethodIsSent_thenError4xxShouldBeReturned() throws Exception {
        List<PersonFireDTO> emptyList = new ArrayList<>();
        when(personService.retrievePeopleByAddress("address")).thenReturn(emptyList);

        mockMvc.perform(get("/fire?address=address")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andDo(print());


    }
}