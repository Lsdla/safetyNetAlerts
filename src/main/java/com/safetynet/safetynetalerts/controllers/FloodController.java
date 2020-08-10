package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.flooddto.FloodFireStationDTO;
import com.safetynet.safetynetalerts.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/**
 * @author Yahia CHERIFI
 * This controller is responsible for
 * retrieving all people couvered by one or many fire stations
 */
@RestController
@RequestMapping("/flood")
public class FloodController {

    /**
     * fireStationService.
     * @see FireStationService
     */
    private FireStationService fireStationService;

    /**
     * FloodController logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(FloodController.class);

    /**
     * Constructor injection.
     * @param service fireStationService
     */
    @Autowired
    public FloodController(
            final FireStationService service) {
        this.fireStationService = service;
    }

    /**
     * Get request.
     * retrieves all people covered by the fire station/stations
     * people are grouped by address
     * @param id a list of fire stations ids
     * @return a list of FloodFireStationDTO
     */
    @GetMapping("/stations")
    public List<FloodFireStationDTO> getFireStationsAndCoveredPeople(
            @RequestParam final List<Long> id) {
        LOGGER.debug("Get request sent from FloodController"
                + " to retrieve fire stations and the covered people");
        if (id.isEmpty()) {
            LOGGER.error("Failed to find fire stations: no id provided");
            throw new IllegalArgumentException("You must provide at"
                    + " least one id to retrieve the desired data");
        }
        String idsList = id.toString();
        LOGGER.info("Person covered by these fire stations "
                + "{} retrieved from database.", idsList);
        return fireStationService.findFireStationsById(id);
    }
}
