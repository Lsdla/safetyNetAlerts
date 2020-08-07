package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.fireDTO.PersonFireDTO;
import com.safetynet.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/fire")
public class FireController {

    private PersonService personService;

    private final Logger LOGGER = LogManager.getLogger(FireController.class);

    @Autowired
    public FireController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public List<PersonFireDTO> retrievePeopleByAddress(@RequestParam String address) {
        LOGGER.debug("Get request sent from FireController");
        List<PersonFireDTO> persons = personService.retrievePeopleByAddress(address);
        if (persons.size() == 0) {
            LOGGER.error("The address provided '"+ address + "' does not match any address in the database");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The address provided does not match any address");
        }
        LOGGER.info("Persons who live in '" + address + "' retrieved from database");
        return personService.retrievePeopleByAddress(address);
    }
}
