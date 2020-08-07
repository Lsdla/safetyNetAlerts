package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.convertor.MedicalRecordConverter;
import com.safetynet.safetynetalerts.domain.MedicalRecord;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@Tag("Service")
class MedicalRecordServiceImplTest {

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

    @DisplayName("findAll calls medicalRecordRepository.findAll")
    @Test
    void findAll_shouldCallAppropriateMethodInMedicalRecordRepository() {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        when(medicalRecordRepository.findAll()).thenReturn(medicalRecords);

        medicalRecordServiceImpl.findAll();

        verify(medicalRecordRepository, times(1)).findAll();


        assertEquals(0, medicalRecordRepository.findAll().size());
    }

    @DisplayName("save calls medicalRecordRepository.save")
    @Test
    void save_shouldCallMedicalRecordRepository() {
        MedicalRecord medicalRecord= new MedicalRecord();
        when(medicalRecordRepository.save(any(MedicalRecord.class))).thenReturn(medicalRecord);
        medicalRecordServiceImpl.save(medicalRecord);
        verify(medicalRecordRepository, times(1)).save(medicalRecord);
    }

    @DisplayName("findByFirstNameAndLastName calls medicalRecordRepository.findByFirstNameAndLastName")
    @Test
    void findByFirstNameAndLastName_shouldReturnCorrectValue() {
        medicalRecordServiceImpl.findByFirstNameAndLastName(anyString(), anyString());
        verify(medicalRecordRepository, times(1)).findByFirstNameAndLastName(anyString(), anyString());

    }

    @DisplayName("deleteByFirstNameAndLastName calls repository.deleteByFirstNameAndLastName")
    @Test
    void deleteByFirstNameAndLastName_shouldCallAppropriateMethodInMedicalRecordRepository() {
        MedicalRecord medicalRecord = new MedicalRecord();

        when(medicalRecordRepository.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecord);

        medicalRecordServiceImpl.deleteByFirstNameAndLastName(anyString(), anyString());

        verify(medicalRecordRepository, times(1)).deleteByFirstNameAndLastName(anyString(), anyString());
    }

    @DisplayName("deleteByFirstNameAndLastName should throw an exception")
    @Test
    void deleteByFirstNameAndLastName_shouldThrowExceptionWhenFirstNameAndLastNameAreNull() {
        assertThrows(ResponseStatusException.class, () -> medicalRecordServiceImpl.deleteByFirstNameAndLastName(null, null));
    }
}