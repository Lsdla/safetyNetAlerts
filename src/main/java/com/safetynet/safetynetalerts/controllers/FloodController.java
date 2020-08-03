package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.DTOs.FloodFireStationDTO;
import com.safetynet.safetynetalerts.convertor.FireStationConverter;
import com.safetynet.safetynetalerts.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flood")
public class FloodController {

    private FireStationService fireStationService;

    @Autowired
    public FloodController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @GetMapping("/stations")
    public List<FloodFireStationDTO> getFireStationsAndCoveredPeople(@RequestParam List<Long> id) {
        return fireStationService.findFireStationsById(id);
    }
}
