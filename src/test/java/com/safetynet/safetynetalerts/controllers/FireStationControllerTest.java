package com.safetynet.safetynetalerts.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.dtos.FireStationDTO;
import com.safetynet.safetynetalerts.dtos.stationNumberDTO.StationNumberPersonDTO;
import com.safetynet.safetynetalerts.dtos.stationNumberDTO.StationNumberFireStationDTO;
import com.safetynet.safetynetalerts.convertor.FireStationConverter;
import com.safetynet.safetynetalerts.domain.FireStation;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("controllers")
@RunWith(SpringRunner.class)
@WebMvcTest(FireStationController.class)
class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FireStationService fireStationService;

    @MockBean
    FireStationConverter fireStationConverter;

    private FireStation createFireStation() {
        FireStation fireStation = new FireStation();
        fireStation.setId(1L);
        fireStation.setAddress("any address");
        fireStation.setStation(2);

        return fireStation;
    }

    @DisplayName("GET OK: getting all the fire stations(dto format)")
    @Test
    void givenAListOfFireStationDTOs_whenGetRequestIsSent_thenTheListShouldBeReturned() throws Exception {
        List<FireStationDTO> dtos = new ArrayList<>();
        FireStationDTO dto = new FireStationDTO();
        dto.setAddress("any address");
        dto.setStation(1);
        dtos.add(dto);

        when(fireStationService.findAll()).thenReturn(dtos);
        this.mockMvc.perform(get("/fireStation/fireStations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address").value("any address"))
                .andExpect(jsonPath("$.[0].station").value(1));
    }

    @DisplayName("POST: OK saving a new fire station")
    @Test
    void givenANewFireStation_whenPostRequestIsSent_thenStationShouldBeSaved() throws Exception {
        when(fireStationService.save(any(FireStation.class))).thenReturn(createFireStation());
        mockMvc.perform(MockMvcRequestBuilders.post("/fireStation/add")
                .content(new ObjectMapper().writeValueAsString(createFireStation()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.address").value("any address"))
                .andExpect(jsonPath("$.station").value(2));
    }

    @DisplayName("PUT OK: Updating an existing fire station")
    @Test
    void givenAnExistingFireStation_whenPutRequestIsSent_thenStationShouldBeUpdated() throws Exception {
        when(fireStationService.save(any(FireStation.class))).thenReturn(createFireStation());
        mockMvc.perform(MockMvcRequestBuilders.put("/fireStation/update")
                .content(new ObjectMapper().writeValueAsString(createFireStation()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.address").value("any address"))
                .andExpect(jsonPath("$.station").value(2));
    }

    @DisplayName("DELETE OK: Deleting a fire station")
    @Test
    void givenAnExistingFireStation_whenDeleteRequestIsSent_thenResponseShouldBeOk() throws Exception {
        Optional<FireStation> fireStation = Optional.of(new FireStation());
        when(fireStationService.findById(anyLong())).thenReturn(fireStation);

        mockMvc.perform(delete("/fireStation/delete/1"))
                .andExpect(status().isOk());
    }

    @DisplayName("DELETE ERROR: error returned if no matching fire station id was found DB")
    @Test
    void givenAWrongFireStationId_whenDeleteRequestIsSent_thenResponseShouldBe4xx() throws Exception {
       Optional<FireStation> fireStation = Optional.of(new FireStation());
        when(fireStationService.findById(1L)).thenReturn(fireStation);

        mockMvc.perform(delete("/fireStation/delete/4"))
                .andExpect(status().is4xxClientError());
    }

    @DisplayName("GET OK: People covered by a fire station")
    @Test
    void givenFireStationId_whenGetMethodIsSent_thenPeopleCoveredBeStationShouldBeReturned() throws Exception {
        StationNumberFireStationDTO station = new StationNumberFireStationDTO();
        List<StationNumberPersonDTO> persons = new ArrayList<>();
        StationNumberPersonDTO person = new StationNumberPersonDTO();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("any address");
        person.setPhone("123456789");
        person.setAge(20.0);
        persons.add(person);
        station.setPersons(persons);
        station.setNumberOfAdults(1);
        station.setNumberOfChildren(0);
        when(fireStationService.urlStationDTO(anyLong())).thenReturn(station);
        mockMvc.perform(get("/fireStation/stationNumber?id=1")
                .content(new ObjectMapper().writeValueAsString(station))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.persons").isArray())
                .andExpect(jsonPath("$.persons[0].firstName").value("John"))
                .andExpect(jsonPath("$.persons[0].lastName").value("Boyd"))
                .andExpect(jsonPath("$.persons[0].address").value("any address"))
                .andExpect(jsonPath("$.persons[0].phone").value("123456789"))
                .andExpect(jsonPath("$.numberOfAdults").value(1))
                .andExpect(jsonPath("$.numberOfChildren").value(0));
    }
}