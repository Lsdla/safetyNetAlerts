package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.phonealertdto.PhoneAlertFireStationDTO;
import com.safetynet.safetynetalerts.dtos.phonealertdto.PhoneAlertPersonDTO;
import com.safetynet.safetynetalerts.service.FireStationService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("controllers")
@RunWith(SpringRunner.class)
@WebMvcTest(PhoneAlertController.class)
class PhoneAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;

    @DisplayName("GET OK: retrieve phone numbers using fire station id")
    @Test
    void givenAFireStationId_whenGetMethodIsSent_AListOfPhoneNumbersShouldBeReturned() throws Exception {
        PhoneAlertFireStationDTO fireStation = new PhoneAlertFireStationDTO();
        List<PhoneAlertPersonDTO> persons = new ArrayList<>();
        PhoneAlertPersonDTO person = new PhoneAlertPersonDTO();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setPhone("123456789");
        persons.add(person);
        PhoneAlertPersonDTO person1 = new PhoneAlertPersonDTO();
        person1.setFirstName("Juda");
        person1.setLastName("Boyd");
        person1.setPhone("000099999");
        persons.add(person1);
        fireStation.setPersons(persons);

        when(fireStationService.findFireStationById(anyLong())).thenReturn(fireStation);

        mockMvc.perform(get("/phoneAlert/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.persons[0].firstName").value("John"))
                .andExpect(jsonPath("$.persons[0].lastName").value("Boyd"))
                .andExpect(jsonPath("$.persons[0].phone").value("123456789"))
                .andExpect(jsonPath("$.persons[1].firstName").value("Juda"))
                .andExpect(jsonPath("$.persons[1].lastName").value("Boyd"))
                .andExpect(jsonPath("$.persons[1].phone").value("000099999"));
    }
}