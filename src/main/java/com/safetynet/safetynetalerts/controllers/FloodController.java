package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.floodDto.FloodFireStationDTO;
import com.safetynet.safetynetalerts.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flood")
public class FloodController {

    private FireStationService fireStationService;

    private final Logger LOGGER = LogManager.getLogger(FloodController.class);

    @Autowired
    public FloodController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @GetMapping("/stations")
    public List<FloodFireStationDTO> getFireStationsAndCoveredPeople(@RequestParam List<Long> id) {
        LOGGER.debug("Get request sent from FloodController to retrieve fire stations and the covered people");
        if (id.isEmpty()) {
            LOGGER.error("Failed to find fire stations: no id provided");
            throw new IllegalArgumentException("You must provide at least one id to retrieve the desired data");
        }
        LOGGER.info("Person covered by " + id.toString() + " were retrieved from database");
        return fireStationService.findFireStationsById(id);
    }
}
