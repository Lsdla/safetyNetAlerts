package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dtos.FireStationDTO;
import com.safetynet.safetynetalerts.dtos.flooddto.FloodFireStationDTO;
import com.safetynet.safetynetalerts.dtos.stationnumberdto.StationNumberFireStationDTO;
import com.safetynet.safetynetalerts.dtos.phonealertdto.PhoneAlertFireStationDTO;
import com.safetynet.safetynetalerts.domain.FireStation;

import java.util.List;
import java.util.Optional;

/**
 * @author Yahia CHERIFI
 * This interface contains methods that provide
 * the logic to operated on the data sent to and from
 * the controllers and the repository layer
 */
public interface FireStationService {

    /**
     * Saves a new fire station to db.
     * @param fireStation the fire station to be saved
     * @return the saved fire station
     */
    FireStation save(FireStation fireStation);

    /**
     * Retrieve a fire station by its id.
     * @param id the fire station id
     * @return the found fire station
     */
    Optional<FireStation> findById(Long id);

    /**
     * Delete a fire station by id.
     * @param id the fire station id.
     */
    void deleteById(Long id);

    /**
     * Retrieve all fire stations from db.
     * @return a list of fire FireStationDTO
     */
    List<FireStationDTO> findAll();

    /**
     * Retrieve Fire stations by a list of ids.
     * @param id a list of fire station ids
     * @return a list of FloodFireStationDTO
     */
    List<FloodFireStationDTO> findFireStationsById(List<Long> id);

    /**
     * Retrieve a fire station by its id.
     * @param id the fire station id
     * @return a StationNumberFireStationDTO
     */
    StationNumberFireStationDTO getOneFireStationById(Long id);

    /**
     * Retrieve a fire station by its id.
     * @param id the fire station id
     * @return a PhoneAlertFireStationDTO
     */
    PhoneAlertFireStationDTO findFireStationById(Long id);
}
