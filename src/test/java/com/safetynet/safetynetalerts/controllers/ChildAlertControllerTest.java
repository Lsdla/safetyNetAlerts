package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.childdto.ChildDTO;
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
@WebMvcTest(ChildAlertController.class)
class ChildAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @DisplayName("GET OK: retrieve children and household members")
    @Test
    void givenAnAddress_whenGetMethodIsSent_thenChildrenAndHouseHoldMembersShouldBeReturned() throws Exception {
        List<List<ChildDTO>> childrenAndHouseHold = new ArrayList<>();
        List<ChildDTO> children = new ArrayList<>();
        ChildDTO child = new ChildDTO();
        child.setFirstName("John");
        child.setLastName("Boyd");
        child.setAge(11.1);
        children.add(child);
        childrenAndHouseHold.add(children);
        when(personService.findChildrenByAddress(anyString())).thenReturn(childrenAndHouseHold);

        mockMvc.perform(get("/childAlert?address=any address")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0][0].firstName").value(child.getFirstName()))
                .andExpect(jsonPath("$[0][0].lastName").value(child.getLastName()))
                .andExpect(jsonPath("$[0][0].age").value(child.getAge()));
    }

    @DisplayName("GET ERROR: wrong url returns a 4xx error")
    @Test
    void givenAWrongURL_whenGetMethodIsSent_thenError4xxShouldBeReturned() throws Exception {
        List<List<ChildDTO>> childrenAndHouseHold = new ArrayList<>();
        when(personService.findChildrenByAddress(anyString())).thenReturn(childrenAndHouseHold);

        mockMvc.perform(get("/wrongURL?address=any address"))
                .andExpect(status().is4xxClientError());
    }
}