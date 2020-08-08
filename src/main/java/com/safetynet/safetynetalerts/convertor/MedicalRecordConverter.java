package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.dtos.MedicalRecordDTO;
import com.safetynet.safetynetalerts.domain.MedicalRecord;

import java.util.List;

/**
 * @author Yahia CHERIFI
 * MedicalRecordConverter interface
 * provides method that allow conversion from
 * MedicalRecord entity to DTO
 */
public interface MedicalRecordConverter {

    /**
     * converts from medicalRecord entity to MedicalRecordDTO.
     * @param medicalRecord entity
     * @return MedicalRecordDTO
     */
    MedicalRecordDTO medicalRecordToDAOConverter(MedicalRecord medicalRecord);

    /**
     * converts a list of medicalRecords entity to a list of MedicalRecordDTOs.
     * @param medicalRecords a list of medicalRecord entities
     * @return a list of MedicalRecordDTOs
     */
    List<MedicalRecordDTO> medicalRecordToDAOsConverter(
            List<MedicalRecord> medicalRecords);
 }
