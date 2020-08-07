package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.MedicalRecordDTO;
import com.safetynet.safetynetalerts.domain.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    //inject medicalRecordService
    private MedicalRecordService medicalRecordService;

    private final Logger LOGGER = LogManager.getLogger(MedicalRecordController.class);

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping("/records")
    public List<MedicalRecordDTO> findAllMedicalRecords() {
        LOGGER.debug("Get request sent from the MedicalRecordController");
        return medicalRecordService.findAll();
    }

    //add mapping for POST /medicalRecord/add
    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        LOGGER.debug("Post request sent from the MedicalRecordController");
        return medicalRecordService.save(medicalRecord);
    }

    //add mapping for PUT /medicalRecord/update
    @PutMapping("/update")
    public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        LOGGER.debug("Put request sent from the MedicalRecordController");
        return medicalRecordService.save(medicalRecord);
    }

    //add mapping for DELETE /medicalRecord/delete
    @DeleteMapping("/delete/{firstName}&{lastName}")
    public String deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName) {
        LOGGER.debug("Delete request sent from the MedicalRecordController");
        medicalRecordService.deleteByFirstNameAndLastName(firstName, lastName);
        return ("Medical records for '" + firstName + " " + lastName + "' were deleted successfully");
    }
}
