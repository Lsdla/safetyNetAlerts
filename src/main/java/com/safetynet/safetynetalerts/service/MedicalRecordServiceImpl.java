package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dtos.MedicalRecordDTO;
import com.safetynet.safetynetalerts.convertor.MedicalRecordConverter;
import com.safetynet.safetynetalerts.domain.MedicalRecord;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MedicalRecordServiceImpl implements MedicalRecordService {

    //inject MedicalRecordRepository
    private MedicalRecordRepository medicalRecordRepository;
    private MedicalRecordConverter medicalRecordConverter;

    @Autowired
    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository, MedicalRecordConverter medicalRecordConverter) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.medicalRecordConverter = medicalRecordConverter;
    }

    @Override
    public List<MedicalRecordDTO> findAll() {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        return medicalRecordConverter.medicalRecordToDAOsConverter(medicalRecords);
    }

    @Override
    public MedicalRecord save(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);

        if (medicalRecord == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No medical record that belongs to '" +
                    firstName + " " + lastName + "' was found");
        } else {
            medicalRecordRepository.deleteByFirstNameAndLastName(firstName, lastName);
        }
    }

    @Override
    public MedicalRecord findByFirstNameAndLastName(String firstName, String lastName) {
        return medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
