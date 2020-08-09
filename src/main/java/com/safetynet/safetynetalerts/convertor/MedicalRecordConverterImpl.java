package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.dtos.MedicalRecordDTO;
import com.safetynet.safetynetalerts.domain.MedicalRecord;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yahia CHERIFI
 * MedicalRecordConverter implementation
 * @see MedicalRecordConverter
 */
@Component
public class MedicalRecordConverterImpl implements MedicalRecordConverter {

    /**
     * modelMapper to be injected.
     * used to map objects to each other
     */
    private ModelMapper mapper;

    /**
     * Constructor injection.
     * @param modelMapper modelMapper
     */
    @Autowired
    public MedicalRecordConverterImpl(final ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    /**
     * @see MedicalRecordConverter
     * @param medicalRecord entity
     * @return MedicalRecordDTO
     */
    @Override
    public MedicalRecordDTO medicalRecordToDAOConverter(
            final MedicalRecord medicalRecord) {
        return mapper.map(medicalRecord, MedicalRecordDTO.class);
    }

    /**
     * @see MedicalRecordConverter
     * @param medicalRecords a list of medicalRecord entities
     * @return a list of MedicalRecordDTOs
     */
    @Override
    public List<MedicalRecordDTO> medicalRecordToDAOsConverter(
            final List<MedicalRecord> medicalRecords) {
        return medicalRecords.stream()
                .map(this::medicalRecordToDAOConverter)
                .collect(Collectors.toList());
    }
}
