package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.DTOs.MedicalRecordDTO;
import com.safetynet.safetynetalerts.convertor.MedicalRecordConverter;
import com.safetynet.safetynetalerts.domain.MedicalRecord;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MedicalRecordServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    private MedicalRecordServiceImpl medicalRecordServiceImpl;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Mock
    private MedicalRecordConverter medicalRecordConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        medicalRecordServiceImpl = new MedicalRecordServiceImpl
                (medicalRecordRepository, medicalRecordConverter);
    }

    @AfterEach
    void tearDown() {
        medicalRecordServiceImpl = null;
    }

    @DisplayName("Getting all the medical records and converting it to dto")
    @Test
    void findAll_shouldReturnAListOfMedicalRecordsDTO() {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        when(medicalRecordRepository.findAll()).thenReturn(medicalRecords);

        medicalRecordServiceImpl.findAll();

        verify(medicalRecordRepository, times(1)).findAll();


        assertEquals(0, medicalRecordRepository.findAll().size());
    }

    @Test
    void save_shouldCallMedicalRecordRepository() {
        MedicalRecord medicalRecord= new MedicalRecord();
        when(medicalRecordRepository.save(any(MedicalRecord.class))).thenReturn(medicalRecord);
        medicalRecordServiceImpl.save(medicalRecord);
        verify(medicalRecordRepository, times(1)).save(medicalRecord);
    }

    @Test
    void findByFirstNameAndLastName() {
        medicalRecordServiceImpl.findByFirstNameAndLastName(anyString(), anyString());
        verify(medicalRecordRepository, times(1)).findByFirstNameAndLastName(anyString(), anyString());

    }

    @Test
    void deleteByFirstNameAndLastName() {
        MedicalRecord medicalRecord = new MedicalRecord();

        when(medicalRecordRepository.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecord);

        medicalRecordServiceImpl.deleteByFirstNameAndLastName(anyString(), anyString());

        verify(medicalRecordRepository, times(1)).deleteByFirstNameAndLastName(anyString(), anyString());
    }
}