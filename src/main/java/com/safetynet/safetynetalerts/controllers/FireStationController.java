package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<FireStation> findFireStations() {
        return fireStationService.findAll();
    }
    //add mapping for POST /add -add a new fire station
    @PostMapping("/add")
    public FireStation addFireStation(@RequestBody FireStation fireStation) {

        //in case of passing an id in the json.. we have to set it to 0
        //mainly used to force saving a new item instead of updating
        //an existing item that has the id entered in the json
        fireStation.setId(0L);

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
    @DeleteMapping("/delete/{station}")
    public String deleteFireStation(@PathVariable String station) {
        //check if the fire station exists in db
        FireStation fireStation = fireStationService.findByStation(station);

        //throw an exception if no fire station that has the inserted station number was found
        if (fireStation == null) {
            throw new RuntimeException("No fire station that has n° " + station + " was found");
        }

        //delete the found station
        fireStationService.deleteByStation(station);

        //return a simple message
        return ("Station n° " + station +" deleted successfully");
    }

}
