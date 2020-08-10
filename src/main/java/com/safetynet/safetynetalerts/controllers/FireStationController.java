package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.dtos.FireStationDTO;
import com.safetynet.safetynetalerts.dtos.stationnumberdto.StationNumberFireStationDTO;
import com.safetynet.safetynetalerts.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * @author Yahia CHERIFI
 * This controller is responsible for
 * CRUD operations on FireStation
 */

@RestController
@RequestMapping("/fireStation")
public class FireStationController {

    /**
     * fireStationService.
     * @see FireStationService
     */
    private FireStationService fireStationService;

    /**
     * FireStationController logger.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(FireStationController.class);

    /**
     * Constructor injection.
     * @param service FireStationService
     */
    @Autowired
    public FireStationController(
            final FireStationService service) {
        this.fireStationService = service;
    }

    /**
     * Get request.
     * Retrieves all the fire stations
     * @return a list of FireStationDTO
     */
    @GetMapping("/fireStations")
    public List<FireStationDTO> findFireStations() {
        LOGGER.debug("Get request sent from the FireStationController");
        return fireStationService.findAll();
    }

    /**
     * Post request.
     * used to add new fire stations
     * @param fireStation fire station to add
     * @return the posted fire station
     */
    @PostMapping("/add")
    public FireStation addFireStation(
            @RequestBody final FireStation fireStation) {
        LOGGER.debug("Post request sent from FireStationController");
        //save the new created fire station
        fireStationService.save(fireStation);

        //return the fire station
        return fireStation;
    }

    /**
     * Put request.
     * used to update existing fire stations
     * @param fireStation fire station to update
     * @return the updated fire station
     */
    //add mapping for PUT /update -update an existing fire station
    @PutMapping("/update")
    public FireStation updateFireStation(
            @RequestBody final FireStation fireStation) {
        LOGGER.debug("Put request sent from FireStationController");
        fireStationService.save(fireStation);

        return fireStation;
    }

    /**
     * Delete request.
     * @param id the fire station's id
     * @return a message that indicates success
     * or an exception if no fire station was deleted
     */
    //add mapping for DELETE /delete -remove an existing fire station from db
    @DeleteMapping("/delete/{id}")
    public String deleteFireStation(
            @PathVariable final Long id) {
        LOGGER.debug(
                "Delete request sent from FireStationController");
        //check if the fire station exists in db
        LOGGER.debug("Searching for fire station {} id", id);
        Optional<FireStation> fireStation = fireStationService.findById(id);

        //delete the fire station if it exists
        if (fireStation.isPresent()) {
            fireStationService.deleteById(id);
            LOGGER.info("Fire station deleted from database");
            return ("Fire station deleted successfully");
        } else {
            //return a not found status if no fire station is found
            LOGGER.error(
                    "No matching fire station id : {} was found in database",
                    id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No fire station matching id " + id + " was found");
        }
    }

    /**
     * Get request.
     * retrieves people covered by a given fire station
     * @param id fire station id
     * @return all persons covered by the fire station
     */
    @GetMapping("/stationNumber")
    public StationNumberFireStationDTO getPersonsCoveredByFireStation(
            @RequestParam final Long id) {
        LOGGER.debug("Get request sent from FireStationController "
                + "to retrieve all persons covered"
                + " by the fire station where id =  {} ", id);
        return fireStationService.getOneFireStationById(id);
    }

}
