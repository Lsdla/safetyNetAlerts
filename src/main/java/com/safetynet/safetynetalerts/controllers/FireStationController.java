package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.FireStationDTO;
import com.safetynet.safetynetalerts.dtos.stationNumberDTO.StationNumberFireStationDTO;
import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.service.FireStationService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fireStation")
public class FireStationController {

    //inject fireStationService
    private FireStationService fireStationService;

    private final Logger LOGGER = LogManager.getLogger(FireStationController.class);

    @Autowired
    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @GetMapping("/fireStations")
    public List<FireStationDTO> findFireStations() {
        LOGGER.debug("Get request sent from the FireStationController");
        return fireStationService.findAll();
    }
    //add mapping for POST /add -add a new fire station
    @PostMapping("/add")
    public FireStation addFireStation(@RequestBody FireStation fireStation) {
        LOGGER.debug("Post request sent from FireStationController");
        //save the new created fire station
        fireStationService.save(fireStation);

        //return the fire station
        return fireStation;
    }

    //add mapping for PUT /update -update an existing fire station
    @PutMapping("/update")
    public FireStation updateFireStation(@RequestBody FireStation fireStation) {
        LOGGER.debug("Put request sent from FireStationController");
        fireStationService.save(fireStation);

        return fireStation;
    }

    //add mapping for DELETE /delete -remove an existing fire station from db
    @DeleteMapping("/delete/{id}")
    public String deleteFireStation(@PathVariable Long id) {
        LOGGER.debug("Delete request sent from FireStationController");
        //check if the fire station exists in db
        LOGGER.debug("Searching for the fire station that has " + id + " as an id");
        Optional<FireStation> fireStation = fireStationService.findById(id);

        //delete the fire station if it exists
        if (fireStation.isPresent()) {
            fireStationService.deleteById(id);
            LOGGER.info("Fire station deleted from database");
            return ("Fire station deleted successfully");
        } else {
            //return a not found status if no fire station is found
            LOGGER.error("Failed to delete fire station " + id + ": no matching fire station id in database");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No fire station that has " + id + " was found");
        }
    }

    @GetMapping("/stationNumber")
    public StationNumberFireStationDTO getPersonsCoveredByFireStation(@RequestParam Long id) {
        LOGGER.debug("Get request sent from FireStationController to retrieve all persons covered by the fire station " + id);
        return fireStationService.getOneFireStationById(id);
    }

}
