package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.domain.MedicalRecord;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MedicalRecordServiceImpl implements MedicalRecordService {

    //inject MedicalRecordRepository
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public List<MedicalRecord> findAll() {
        return medicalRecordRepository.findAll();
    }

    @Override
    public void save(MedicalRecord medicalRecord) {
        medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        medicalRecordRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public MedicalRecord findByFirstNameAndLastName(String firstName, String lastName) {
        return medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
