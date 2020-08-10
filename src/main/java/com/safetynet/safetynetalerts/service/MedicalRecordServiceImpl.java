package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dtos.MedicalRecordDTO;
import com.safetynet.safetynetalerts.convertor.MedicalRecordConverter;
import com.safetynet.safetynetalerts.domain.MedicalRecord;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepository;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Yahia CHERIFI
 * This class impleents FireStationService interface
 * @see MedicalRecordService
 */
@Service
@Transactional
@NoArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    /**
     * MedicalRecordRepository to be injected.
     */
    private MedicalRecordRepository medicalRecordRepository;

    /**
     * MedicalRecordConverter to be injected.
     */
    private MedicalRecordConverter medicalRecordConverter;

    /**
     * MedicalRecordServiceImpl logger.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(MedicalRecordServiceImpl.class);

    /**
     * Used in logging messages.
     * Data provided by users will be changed
     * some characters will omitted for security purposes:
     * Logging injection
     */
    private static final String DANGEROUS_CHARACTERS =  "[\n\r\t]";

    /**
     * DANGEROUS_CHARACTERS will be replaced by REPLACEMENT_CHARACTER.
     */
    private static final String REPLACEMENT_CHARACTER = "_";

    /**
     * Constructor injection.
     * @param medicalRecordRepositoryInstance medicalRecordRepositoryInstance
     * @param medicalRecordConverterInstance medicalRecordRepositoryInstance
     */
    @Autowired
    public MedicalRecordServiceImpl(
            final MedicalRecordRepository medicalRecordRepositoryInstance,
            final MedicalRecordConverter medicalRecordConverterInstance) {
        this.medicalRecordRepository = medicalRecordRepositoryInstance;
        this.medicalRecordConverter = medicalRecordConverterInstance;
    }

    /**
     * Calls the repository layer.
     * @return a list of MedicalRecordDTO
     */
    @Override
    public List<MedicalRecordDTO> findAll() {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        return medicalRecordConverter
                .medicalRecordToDAOsConverter(medicalRecords);
    }

    /**
     * Calls the repository layer.
     * @param medicalRecord the medical record to save
     * @return the saved medical record
     */
    @Override
    public MedicalRecord save(final MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    /**
     * Calls the repository layer.
     * @param firstName the medical record owner's first name
     * @param lastName the medical record owner's last name
     */
    @Override
    public void deleteByFirstNameAndLastName(
            final String firstName, final String lastName) {
        MedicalRecord medicalRecord = medicalRecordRepository
                .findByFirstNameAndLastName(firstName, lastName);
        String secureFirstNameCharacters = firstName
                .replaceAll(DANGEROUS_CHARACTERS, REPLACEMENT_CHARACTER);
        String secureLastNameCharacters = lastName
                .replaceAll(DANGEROUS_CHARACTERS, REPLACEMENT_CHARACTER);
        if (medicalRecord == null) {

            LOGGER.error(
                    "Error occurred while trying to delete the"
                            + " medical record that belongs to {} {}."
                            + " No matching medical record found",
                            secureFirstNameCharacters,
                            secureLastNameCharacters);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No medical record that belongs to '"
                            + secureFirstNameCharacters
                            + " " + secureLastNameCharacters + "' was found");
        } else {
            LOGGER.info(
                    "{} {}'s medical record deleted from the database.",
                    secureFirstNameCharacters, secureLastNameCharacters);
            medicalRecordRepository
                    .deleteByFirstNameAndLastName(firstName, lastName);
        }
    }

    /**
     * Find a medical record by the owner's first and last names.
     * @param firstName the medical record owner's first name
     * @param lastName the medical record owner's last name
     * @return the medical record
     */
    @Override
    public MedicalRecord findByFirstNameAndLastName(
            final String firstName, final String lastName) {
        return medicalRecordRepository
                .findByFirstNameAndLastName(firstName, lastName);
    }
}
