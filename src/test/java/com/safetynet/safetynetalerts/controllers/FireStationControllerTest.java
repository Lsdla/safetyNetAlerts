package com.safetynet.safetynetalerts.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.service.FireStationService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FireStationController.class)
class FireStationControllerTest {

    FireStationController fireStationController;

    @MockBean
    FireStationService fireStationService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        fireStationController = new FireStationController(fireStationService);
    }

    @AfterEach
    void tearDown() {
        fireStationController = null;
    }

    @Test
    void findFireStations() throws Exception {
        List<FireStation> fireStations = new ArrayList<>();
        FireStation fireStation = new FireStation();

        fireStation.setId(1L);
        fireStation.setAddress("an address");
        fireStation.setStation(3);
        fireStations.add(fireStation);

        when(fireStationService.findAll()).thenReturn(fireStations);

        mockMvc.perform(get("/fireStation/fireStations")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].address").value("an address"))
                .andExpect(jsonPath("$[0].station").value(3));
    }

    @Test
    void addFireStation() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setId(1L);
        fireStation.setAddress("an address");
        fireStation.setStation(3);
        when(fireStationService.save(any(FireStation.class))).thenReturn(fireStation);


        mockMvc.perform(MockMvcRequestBuilders.post("/fireStation/add")
                .content(new ObjectMapper().writeValueAsString(fireStation))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.address").value("an address"))
                .andExpect(jsonPath("$.station").value(3));
    }

    @Test
    void updateFireStation() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setId(1L);
        fireStation.setAddress("an address");
        fireStation.setStation(3);
        when(fireStationService.save(any(FireStation.class))).thenReturn(fireStation);


        mockMvc.perform(MockMvcRequestBuilders.put("/fireStation/update")
                .content(new ObjectMapper().writeValueAsString(fireStation))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.address").value("an address"))
                .andExpect(jsonPath("$.station").value(3));
    }

    @Test
    void deleteFireStation() throws Exception{
        FireStation fireStation = new FireStation();

        when(fireStationService.findById(anyLong())).thenReturn(Optional.of(fireStation));

        mockMvc.perform(MockMvcRequestBuilders.delete("/fireStation/delete/1"))
                .andExpect(status().isOk());
    }
}