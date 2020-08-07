package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.phoneAlertDTO.PhoneAlertFireStationDTO;
import com.safetynet.safetynetalerts.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phoneAlert")
public class PhoneAlertController {

    private FireStationService fireStationService;

    private final Logger LOGGER= LogManager.getLogger(PhoneAlertController.class);

    @Autowired
    public PhoneAlertController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @GetMapping("/{id}")
    public PhoneAlertFireStationDTO retrievePhoneNumbers(@PathVariable Long id) {
        LOGGER.debug("Get request sent from the PhoneAlertController");
        return fireStationService.findFireStationById(id);
    }
}
