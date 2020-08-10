package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dtos.MedicalRecordDTO;
import com.safetynet.safetynetalerts.domain.MedicalRecord;

import java.util.List;

/**
 * @author Yahia CHERIFI
 * This interface contains methods that provide
 * the logic to operated on the data sent to and from
 * the controllers and the repository layer
 */
public interface MedicalRecordService {

    /**
     * Save a new medical record.
     * @param medicalRecord the medical record to save
     * @return the saved medical record
     */
    MedicalRecord save(MedicalRecord medicalRecord);

    /**
     * Delete a medical record by the owner's first and last names.
     * @param firstName the medical record owner's first name
     * @param lastName the medical record owner's last name
     */
    void deleteByFirstNameAndLastName(String firstName, String lastName);

    /**
     *  Retrieve a medical record by owner's first nad last names.
     * @param firstName the medical record owner's first name
     * @param lastName the medical record owner's last name
     * @return the found medical record
     */
    MedicalRecord findByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Retrieve all the medical records from db.
     * @return a list of MedicalRecordDTO
     */
    List<MedicalRecordDTO> findAll();
}
