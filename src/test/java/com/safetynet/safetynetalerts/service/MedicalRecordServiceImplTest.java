package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.domain.MedicalRecord;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MedicalRecordServiceImplTest {

    MedicalRecordServiceImpl medicalRecordServiceImpl;

    @Mock
    MedicalRecordRepository medicalRecordRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        medicalRecordServiceImpl = new MedicalRecordServiceImpl(medicalRecordRepository);
    }

    @AfterEach
    void tearDown() {
        medicalRecordServiceImpl = null;
    }

    @Test
    void findAll() {
        MedicalRecord medicalRecord = new MedicalRecord();
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(medicalRecord);
        when(medicalRecordRepository.findAll()).thenReturn(medicalRecords);
        medicalRecordServiceImpl.findAll();

        verify(medicalRecordRepository, times(1)).findAll();
        assertEquals(medicalRecords.size(), 1);
    }

    @Test
    void save() {
        MedicalRecord medicalRecord= new MedicalRecord();
        when(medicalRecordRepository.save(medicalRecord)).thenReturn(medicalRecord);
        medicalRecordServiceImpl.save(medicalRecord);
        verify(medicalRecordRepository, times(1)).save(medicalRecord);
    }

    @Test
    void deleteByFirstNameAndLastName() {
        medicalRecordServiceImpl.deleteByFirstNameAndLastName(anyString(), anyString());
        verify(medicalRecordRepository, times(1)).deleteByFirstNameAndLastName(anyString(), anyString());
    }

    @Test
    void findByFirstNameAndLastName() {
        medicalRecordServiceImpl.findByFirstNameAndLastName(anyString(), anyString());
        verify(medicalRecordRepository, times(1)).findByFirstNameAndLastName(anyString(), anyString());

    }
}