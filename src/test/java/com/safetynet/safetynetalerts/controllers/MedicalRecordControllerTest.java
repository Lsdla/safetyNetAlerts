package com.safetynet.safetynetalerts.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.domain.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(MedicalRecordController.class)
class MedicalRecordControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    MedicalRecordService medicalRecordService;

    private MedicalRecord newRecord() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = sdf.parse("21/12/2012");
        List<String> allergies = new ArrayList<>();
        List<String> medications = new ArrayList<>();
        allergies.add("nillacilan");
        medications.add("aznol:350mg");
        MedicalRecord record = new MedicalRecord();
        record.setId(1L);
        record.setFirstName("John");
        record.setLastName("Boyd");
        record.setBirthDate(birthDate);
        record.setAllergies(allergies);
        record.setMedications(medications);
        return record;
    }

    @Test
    void findAllMedicalRecords() throws Exception {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(newRecord());

        when(medicalRecordService.findAll()).thenReturn(medicalRecords);

        mockMvc.perform(get("/medicalRecord/records")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Boyd"))
                .andExpect(jsonPath("$[0].birthDate").value("2012-12-20T23:00:00.000+00:00"))
                .andExpect(jsonPath("$[0].allergies").value("nillacilan"))
                .andExpect(jsonPath("$[0].medications").value("aznol:350mg"));
    }

    @Test
    void addMedicalRecord() throws Exception{
        when(medicalRecordService.save(any(MedicalRecord.class))).thenReturn(newRecord());
        mockMvc.perform(MockMvcRequestBuilders.post("/medicalRecord/add")
                .content(new ObjectMapper().writeValueAsString(newRecord()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Boyd"))
                .andExpect(jsonPath("$.birthDate").value("2012-12-20T23:00:00.000+00:00"))
                .andExpect(jsonPath("$.allergies").value("nillacilan"))
                .andExpect(jsonPath("$.medications").value("aznol:350mg"));
    }

    @Test
    void updateMedicalRecord() throws Exception{
        when(medicalRecordService.save(any(MedicalRecord.class))).thenReturn(newRecord());
        mockMvc.perform(MockMvcRequestBuilders.put("/medicalRecord/update")
                .content(new ObjectMapper().writeValueAsString(newRecord()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Boyd"))
                .andExpect(jsonPath("$.birthDate").value("2012-12-20T23:00:00.000+00:00"))
                .andExpect(jsonPath("$.allergies").value("nillacilan"))
                .andExpect(jsonPath("$.medications").value("aznol:350mg"));
    }

    @Test
    void deleteMedicalRecord() throws Exception {
        MedicalRecord record = new MedicalRecord();

        when(medicalRecordService.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(record);

        mockMvc.perform(MockMvcRequestBuilders.delete("/medicalRecord/delete/John&Boyd"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMedicalRecordThrowsException() throws Exception {
        when(medicalRecordService.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.delete("/medicalRecord/delete/John&Boyd"))
                .andExpect(status().is4xxClientError());
    }
}