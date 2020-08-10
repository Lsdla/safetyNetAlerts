package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.communityemaildto.CommunityEmailDTO;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("controllers")
@RunWith(SpringRunner.class)
@WebMvcTest(CommunityEmailController.class)
class CommunityEmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PersonService personService;

    @DisplayName("GET OK: getting all community emails")
    @Test
    void givenCityName_whenGetRequestIsSent_ThenAllEmailsShouldBeReturned() throws Exception {
        List<CommunityEmailDTO> communityEmails = new ArrayList<>();
        CommunityEmailDTO person = new CommunityEmailDTO();
        person.setEmail("email@mail.com");
        CommunityEmailDTO person2 = new CommunityEmailDTO();
        person2.setEmail("safetyNet@mail.com");
        communityEmails.add(person);
        communityEmails.add(person2);

        when(personService.findEmailsByCity(anyString())).thenReturn(communityEmails);

        mockMvc.perform(get("/communityEmail?city=Culver")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value(person.getEmail()))
                .andExpect(jsonPath("$[1].email").value(person2.getEmail()));
    }
}