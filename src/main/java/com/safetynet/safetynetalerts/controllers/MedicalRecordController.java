package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.domain.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<MedicalRecord> findAllMedicalRecords() {
        return medicalRecordService.findAll();
    }

    //add mapping for POST /medicalRecord/add
    @PostMapping("/add")
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        //in case of passing an id in the json.. we have to set it to 0
        //mainly used to force saving a new item instead of updating
        //an existing item that has the id entered in the json
        medicalRecord.setId(0L);
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
            throw new RuntimeException("No medical record that belongs to '" +
                    firstName + " " + lastName + "' was found");
        }

        medicalRecordService.deleteByFirstNameAndLastName(firstName, lastName);

        return ("Medical records for '" + firstName + " " + lastName + "' were deleted successfully");
    }
}
