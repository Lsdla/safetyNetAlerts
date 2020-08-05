package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.MedicalRecordDTO;
import com.safetynet.safetynetalerts.domain.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    //inject medicalRecordService
    private MedicalRecordService medicalRecordService;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping("/records")
    public List<MedicalRecordDTO> findAllMedicalRecords() {
        return medicalRecordService.findAll();
    }

    //add mapping for POST /medicalRecord/add
    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return medicalRecordService.save(medicalRecord);
    }

    //add mapping for PUT /medicalRecord/update
    @PutMapping("/update")
    public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return medicalRecordService.save(medicalRecord);
    }

    //add mapping for DELETE /medicalRecord/delete
    @DeleteMapping("/delete/{firstName}&{lastName}")
    public String deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName) {
        medicalRecordService.deleteByFirstNameAndLastName(firstName, lastName);
        return ("Medical records for '" + firstName + " " + lastName + "' were deleted successfully");
    }
}
