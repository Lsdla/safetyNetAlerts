package com.safetynet.safetynetalerts.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.service.FireStationService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("controllers")
@RunWith(SpringRunner.class)
@WebMvcTest(JsonToDataBaseController.class)
class JsonToDataBaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;

    private FireStation fireStation() {
        FireStation fireStation = new FireStation();
        return fireStation;
    }

    @Test
    void givenAfireStation_whenPostRequestSentFromController_thenResponseShouldBeOk() throws Exception {
        when(fireStationService.save(any(FireStation.class))).thenReturn(fireStation());

        mockMvc.perform(MockMvcRequestBuilders.post("/jsonToDatabase/insert")
                .content(new ObjectMapper().writeValueAsString(fireStation()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}