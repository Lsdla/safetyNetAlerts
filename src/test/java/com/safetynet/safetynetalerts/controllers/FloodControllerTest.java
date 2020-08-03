package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.DTOs.FloodFireStationDTO;
import com.safetynet.safetynetalerts.DTOs.FloodPersonDTO;
import com.safetynet.safetynetalerts.DTOs.MedicalRecordDTO;
import com.safetynet.safetynetalerts.service.FireStationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FloodController.class)
class FloodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FireStationService fireStationService;


    @Test
    void givenAListOfFireStationId_whenGetMethodIsSent_thenPeopleCoveredShouldBeReturned() throws Exception {
        List<FloodFireStationDTO> fireStations = new ArrayList<>();
        List<FloodPersonDTO> persons = new ArrayList<>();
        FloodPersonDTO person = new FloodPersonDTO();
        MedicalRecordDTO medicalRecord = new MedicalRecordDTO();
        medicalRecord.setAge(90.0);
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setPhone("123456789");
        person.setMedicalRecord(medicalRecord);


        FloodFireStationDTO fireStation = new FloodFireStationDTO();
        fireStation.setAddress("any address");
        fireStation.setPersons(persons);

        fireStations.add(fireStation);

        when(fireStationService.findFireStationsById(anyList())).thenReturn(fireStations);

        mockMvc.perform(get("/flood/stations?id=1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}