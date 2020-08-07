package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.domain.MedicalRecord;
import com.safetynet.safetynetalerts.dtos.MedicalRecordDTO;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("converters")
class MedicalRecordConverterImplTest {

    private MedicalRecordConverterImpl medicalRecordConverter;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        medicalRecordConverter = new MedicalRecordConverterImpl(modelMapper);
    }

    @AfterEach
    void tearDown() {
        modelMapper = null;
        medicalRecordConverter = null;
    }

    @DisplayName("Convert MedicalRecord to MedicalRecordDTO")
    @Test
    void givenMedicalRecord_whenMedicalRecordToDAOConverterIsCalled_thenMedicalRecordDTOShouldBeReturned() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAge(11.6);
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();

        medicalRecordConverter.medicalRecordToDAOConverter(medicalRecord);
        modelMapper.map(medicalRecord, medicalRecordDTO);
        assertEquals(medicalRecord.getAge(), medicalRecordDTO.getAge());
        assertEquals(medicalRecord.getAllergies(), medicalRecordDTO.getAllergies());
        assertEquals(medicalRecord.getMedications(), medicalRecordDTO.getMedications());
    }

    @DisplayName("Convert a list of MedicalRecord to a list of MedicalRecordDTOs")
    @Test
    void givenMedicalRecordList_whenMedicalRecordToDAOsConverterIsCalled_thenAListOfMedicalRecordDTOsShouldBeReturned() {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecords.add(medicalRecord);

        medicalRecordConverter.medicalRecordToDAOsConverter(medicalRecords);
        List<MedicalRecordDTO> medicalRecordDTOS = medicalRecords.stream().map(m -> medicalRecordConverter.medicalRecordToDAOConverter(m)).collect(Collectors.toList());

        assertEquals(medicalRecords.size(), medicalRecordDTOS.size());
    }
}