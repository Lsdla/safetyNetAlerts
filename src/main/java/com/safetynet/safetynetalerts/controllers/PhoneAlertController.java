package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.DTOs.StationDTO;
import com.safetynet.safetynetalerts.convertor.FireStationConverter;
import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/phoneAlert")
public class PhoneAlertController {

    private FireStationService fireStationService;

    private FireStationConverter fireStationConverter;

    @Autowired
    public PhoneAlertController(FireStationService fireStationService, FireStationConverter fireStationConverter) {
        this.fireStationService = fireStationService;
        this.fireStationConverter = fireStationConverter;
    }

    @GetMapping("/{id}")
    public StationDTO retrievePhoneNumbers(@PathVariable Long id) {
        Optional<FireStation> fireStation = fireStationService.findById(id);
        return fireStationConverter.stationToDTOConverter(fireStation);
    }
}
