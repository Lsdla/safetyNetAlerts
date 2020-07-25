package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.DTOs.FloodFireStationDTO;
import com.safetynet.safetynetalerts.DTOs.FloodPersonDTO;
import com.safetynet.safetynetalerts.convertor.FireStationConverter;
import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flood")
public class FloodController {

    private FireStationService fireStationService;
    private FireStationConverter fireStationConverter;

    @Autowired
    public FloodController(FireStationService fireStationService, FireStationConverter fireStationConverter) {
        this.fireStationService = fireStationService;
        this.fireStationConverter = fireStationConverter;
    }

    @GetMapping("/stations")
    @ResponseBody
    public List<FloodFireStationDTO> getFireStationAndCoveredPeople(@RequestParam List<Long> id) {
        List<FireStation> fireStations = fireStationService.findFireStationsById(id);
        return fireStationConverter.floodFireStationDAOsConverter(fireStations);
    }
}
