package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.DTOs.MedicalRecordDTO;
import com.safetynet.safetynetalerts.convertor.MedicalRecordConverter;
import com.safetynet.safetynetalerts.domain.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    //inject medicalRecordService
    private MedicalRecordService medicalRecordService;

    private MedicalRecordConverter medicalRecordConverter;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService, MedicalRecordConverter medicalRecordConverter) {
        this.medicalRecordService = medicalRecordService;
        this.medicalRecordConverter = medicalRecordConverter;
    }

    @GetMapping("/records")
    public List<MedicalRecordDTO> findAllMedicalRecords() {
        List<MedicalRecord> medicalRecords = medicalRecordService.findAll();
        return medicalRecordConverter.medicalRecordToDAOsConverter(medicalRecords);
    }

    //add mapping for POST /medicalRecord/add
    @PostMapping("/add")
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.save(medicalRecord);
        return medicalRecord;
    }

    //add mapping for PUT /medicalRecord/update
    @PutMapping("/update")
    public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.save(medicalRecord);
        return medicalRecord;
    }

    //add mapping for DELETE /medicalRecord/delete
    @DeleteMapping("/delete/{firstName}&{lastName}")
    public String deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName) {

        MedicalRecord medicalRecord = medicalRecordService.findByFirstNameAndLastName(firstName, lastName);

        if (medicalRecord == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No medical record that belongs to '" +
                    firstName + " " + lastName + "' was found");
        }

        medicalRecordService.deleteByFirstNameAndLastName(firstName, lastName);

        return ("Medical records for '" + firstName + " " + lastName + "' were deleted successfully");
    }
}
