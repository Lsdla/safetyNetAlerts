package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.DTOs.MedicalRecordDTO;
import com.safetynet.safetynetalerts.domain.MedicalRecord;

import java.util.List;

public interface MedicalRecordConverter {

    MedicalRecordDTO medicalRecordToDAOConverter(MedicalRecord medicalRecord);
    List<MedicalRecordDTO> medicalRecordToDAOsConverter(List<MedicalRecord> medicalRecords);
 }
