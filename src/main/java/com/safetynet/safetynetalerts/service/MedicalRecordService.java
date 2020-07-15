package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.domain.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {

    MedicalRecord save(MedicalRecord medicalRecord);

    void deleteByFirstNameAndLastName(String firstName, String lastName);

    MedicalRecord findByFirstNameAndLastName(String firstName, String lastName);

    List<MedicalRecord> findAll();
}
