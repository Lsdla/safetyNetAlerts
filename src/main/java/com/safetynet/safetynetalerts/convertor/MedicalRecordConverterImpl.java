package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.DTOs.MedicalRecordDTO;
import com.safetynet.safetynetalerts.domain.MedicalRecord;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicalRecordConverterImpl implements MedicalRecordConverter{

    private ModelMapper mapper;

    @Autowired
    public MedicalRecordConverterImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public MedicalRecordDTO medicalRecordToDAOConverter(MedicalRecord medicalRecord) {
        return mapper.map(medicalRecord, MedicalRecordDTO.class);
    }

    @Override
    public List<MedicalRecordDTO> medicalRecordToDAOsConverter(List<MedicalRecord> medicalRecords) {
        return medicalRecords.stream().map(x -> medicalRecordToDAOConverter(x)).collect(Collectors.toList());
    }
}
