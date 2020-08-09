package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.domain.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yahia CHERIFI
 * exposes basic CRUD operations on medical record
 * @see JpaRepository
 */
public interface MedicalRecordRepository
        extends JpaRepository<MedicalRecord, Long> {

    /**
     * Delete a medical record.
     * Key word deleteBy allows deletion
     * @param firstName the first name of the medical record owner
     * @param lastName the last name of the medical record owner
     */
    void deleteByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Find a medical record.
     * Key word findBy allows searching for the desired medical record
     * @param firstName the first name of the medical record owner
     * @param lastName the last name of the medical record owner
     * @return the found medical record
     */
    MedicalRecord findByFirstNameAndLastName(String firstName, String lastName);
}
