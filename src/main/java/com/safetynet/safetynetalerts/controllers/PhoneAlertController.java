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

/**
 * @author Yahia CHERIFI
 * this controller is reponsible for
 * retrieving phone numbers of the persons
 * covered by a given fire station
 */

@RestController
@RequestMapping("/phoneAlert")
public class PhoneAlertController {

    /**
     * fireStationService.
     * @see FireStationService
     */
    private FireStationService fireStationService;

    /**
     * PhoneAlertController logger.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(PhoneAlertController.class);

    /**
     * Constructor injection.
     * @param fireStationServiceInstance
     */
    @Autowired
    public PhoneAlertController(
            final FireStationService fireStationServiceInstance) {
        this.fireStationService = fireStationServiceInstance;
    }

    /**
     * Get request.
     * retrieves phone numbers of people covered
     * by a given fire station
     * @param id fire station's id
     * @return PhoneAlertFireStationDTO.
     */
    @GetMapping("/{id}")
    public PhoneAlertFireStationDTO retrievePhoneNumbers(
            @PathVariable final Long id) {
        LOGGER.debug("Get request sent from the PhoneAlertController");
        return fireStationService.findFireStationById(id);
    }
}
