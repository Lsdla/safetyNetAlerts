package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.DTOs.floodDto.FloodFireStationDTO;
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
    public List<FloodFireStationDTO> getFireStationsAndCoveredPeople(@RequestParam List<Long> id) throws Exception {
        if (id.isEmpty()) {
            throw new IllegalArgumentException("You must provide at least one id to retrieve the desired data");
        }
        return fireStationService.findFireStationsById(id);
    }
}
