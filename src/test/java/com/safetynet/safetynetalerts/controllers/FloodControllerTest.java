package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.floodDto.FloodFireStationDTO;
import com.safetynet.safetynetalerts.dtos.floodDto.FloodPersonDTO;
import com.safetynet.safetynetalerts.service.FireStationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("controllers")
@RunWith(SpringRunner.class)
@WebMvcTest(FloodController.class)
class FloodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;

    @DisplayName("GET OK: People covered by a fire station are returned")
    @Test
    void givenAListOfFireStationId_whenGetMethodIsSentByFloodController_thenPeopleCoveredShouldBeReturned() throws Exception {
        List<FloodFireStationDTO> fireStations = new ArrayList<>();
        //first fire station and its persons
        FloodFireStationDTO fireStation = new FloodFireStationDTO();
        fireStation.setId(1L);
        fireStation.setAddress("any address");
        List<FloodPersonDTO> persons = new ArrayList<>();
        FloodPersonDTO person = new FloodPersonDTO();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setPhone("12345678");
        person.setMedicalRecord(null);
        persons.add(person);
        fireStation.setPersons(persons);
        //second fire station and its persons
        FloodFireStationDTO fireStation1 = new FloodFireStationDTO();
        fireStation1.setId(2L);
        fireStation1.setAddress("another address");
        List<FloodPersonDTO> persons1 = new ArrayList<>();
        FloodPersonDTO person1 = new FloodPersonDTO();
        person1.setFirstName("Johan");
        person1.setLastName("Byd");
        person1.setPhone("987654321");
        person1.setMedicalRecord(null);
        persons1.add(person1);
        fireStation1.setPersons(persons1);

        //add the fire stations the to fire station list

        fireStations.add(fireStation);
        fireStations.add(fireStation1);

        //list of fire stations ids
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        when(fireStationService.findFireStationsById(ids)).thenReturn(fireStations);

        mockMvc.perform(get("/flood/stations?id=1&id=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(fireStation.getId()))
                .andExpect(jsonPath("$[0].address").value(fireStation.getAddress()))
                .andExpect(jsonPath("$[0].persons.[0]firstName").value(person.getFirstName()))
                .andExpect(jsonPath("$[0].persons.[0]lastName").value(person.getLastName()))
                .andExpect(jsonPath("$[0].persons.[0]phone").value(person.getPhone()))
                .andExpect(jsonPath("$[0].persons.[0]medicalRecord").value(person.getMedicalRecord()))
                .andExpect(jsonPath("$[1].id").value(fireStation1.getId()))
                .andExpect(jsonPath("$[1].address").value(fireStation1.getAddress()))
                .andExpect(jsonPath("$[1].persons.[0]firstName").value(person1.getFirstName()))
                .andExpect(jsonPath("$[1].persons.[0]lastName").value(person1.getLastName()))
                .andExpect(jsonPath("$[1].persons.[0]phone").value(person1.getPhone()))
                .andExpect(jsonPath("$[1].persons.[0]medicalRecord").value(person1.getMedicalRecord()));
    }

    @DisplayName("GET ERROR: error returned if no id passed as a parameter")
    @Test
    void givenAnEmptyListOfFireStationIds_whenGetMethodIsSent_thenExceptionShouldBeThrown() {
        FloodController floodController = new FloodController(fireStationService);
        List<Long> emptyList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> floodController.getFireStationsAndCoveredPeople(emptyList));
    }


}