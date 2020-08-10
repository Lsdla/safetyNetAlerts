package com.safetynet.safetynetalerts.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.dtos.MedicalRecordDTO;
import com.safetynet.safetynetalerts.domain.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("controllers")
@RunWith(SpringRunner.class)
@WebMvcTest(MedicalRecordController.class)
class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;

    private MedicalRecord createRecord() {
        MedicalRecord medicalRecord = new MedicalRecord();
        //LocalDate birthDate = LocalDate.of(2012, 12, 21);
        medicalRecord.setId(1L);
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");
        //medicalRecord.setBirthDate(birthDate);
        List<String> allergies = new ArrayList<>();
        allergies.add("allergy");
        medicalRecord.setAllergies(allergies);
        List<String> medications = new ArrayList<>();
        medications.add("medication");
        medicalRecord.setMedications(medications);
        return medicalRecord;
    }

    @DisplayName("GET: getting all the medical records(dto format)")
    @Test
    void givenAListOfMedicalRecordDTOs_whenGetRequestIsSent_thenTheListShouldBeReturned() throws Exception {
        List<MedicalRecordDTO> medicalRecords = new ArrayList<>();
        MedicalRecordDTO dto = new MedicalRecordDTO();
        List<String> allergies = new ArrayList<>();
        allergies.add("allergy");
        List<String> medications = new ArrayList<>();
        medications.add("medication");
        dto.setAge(12.0);
        dto.setMedications(medications);
        dto.setAllergies(allergies);
        medicalRecords.add(dto);

        when(medicalRecordService.findAll()).thenReturn(medicalRecords);
        this.mockMvc.perform(get("/medicalRecord/records"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].age").value(12.0))
                .andExpect(jsonPath("$[0].allergies").value("allergy"))
                .andExpect(jsonPath("$[0].medications").value("medication"));
    }

    @DisplayName("POST: saving a new medical record")
    @Test
    void givenANewMedicalRecord_whenPostRequestIsSent_thenRecordShouldBeSaved() throws Exception{
        when(medicalRecordService.save(any(MedicalRecord.class))).thenReturn(createRecord());
        mockMvc.perform(MockMvcRequestBuilders.post("/medicalRecord/add")
                .content(new ObjectMapper().writeValueAsString(createRecord()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Boyd"));
                //.andExpect(jsonPath("$.birthDate").value("2012-12-21"));
    }

    @DisplayName("PUT: Updating an existing medical record")
    @Test
    void givenAnExistingMedicalRecord_whenPutRequestIsSent_thenRecordShouldBeUpdated() throws Exception{
        when(medicalRecordService.save(any(MedicalRecord.class))).thenReturn(createRecord());
        mockMvc.perform(MockMvcRequestBuilders.put("/medicalRecord/update")
                .content(new ObjectMapper().writeValueAsString(createRecord()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Boyd"))
                //.andExpect(jsonPath("$.birthDate").value("2012-21-12"))
                .andExpect(jsonPath("$.allergies").value("allergy"))
                .andExpect(jsonPath("$.medications").value("medication"));
    }

    @DisplayName("DELETE: Deleting a medical record")
    @Test
    void givenAnExistingMedicalRecord_whenDeleteRequestIsSent_thenResponseShouldBeOk() throws Exception {
                when(medicalRecordService.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(createRecord());

        mockMvc.perform(MockMvcRequestBuilders.delete("/medicalRecord/delete/John&Boyd"))
                .andExpect(status().isOk());
    }
}