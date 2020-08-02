package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.DTOs.FireStationDTO;
import com.safetynet.safetynetalerts.DTOs.StationDTO;
import com.safetynet.safetynetalerts.DTOs.UrlStationDTO;
import com.safetynet.safetynetalerts.convertor.FireStationConverter;
import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.service.FireStationService;

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

    @Autowired
    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @GetMapping("/fireStations")
    public List<FireStationDTO> findFireStations() {
         return fireStationService.findAll();
    }
    //add mapping for POST /add -add a new fire station
    @PostMapping("/add")
    public FireStation addFireStation(@RequestBody FireStation fireStation) {

        //save the new created fire station
        fireStationService.save(fireStation);

        //return the fire station
        return fireStation;
    }

    //add mapping for PUT /update -update an existing fire station
    @PutMapping("/update")
    public FireStation updateFireStation(@RequestBody FireStation fireStation) {

        fireStationService.save(fireStation);

        return fireStation;
    }

    //add mapping for DELETE /delete -remove an existing fire station from db
    @DeleteMapping("/delete/{id}")
    public String deleteFireStation(@PathVariable Long id) {

        //check if the fire station exists in db
        Optional<FireStation> fireStation = fireStationService.findById(id);

        //delete the fire station if it exists
        if (fireStation.isPresent()) {
            fireStationService.deleteById(id);
            return ("Fire station deleted successfully");
        } else {
            //return a not found status if no fire station is found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No fire station that has " + id + " was found");
        }
    }

    @GetMapping("/stationNumber")
    public UrlStationDTO getPersonsCoveredByFireStation(@RequestParam Long id) {
        return fireStationService.urlStationDTO(id);
    }

}
