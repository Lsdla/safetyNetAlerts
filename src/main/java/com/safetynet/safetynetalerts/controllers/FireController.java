package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.DTOs.fireDTO.PersonFireDTO;
import com.safetynet.safetynetalerts.service.PersonService;
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

    @Autowired
    public FireController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public List<PersonFireDTO> retrievePeopleByAddress(@RequestParam String address) {
        List<PersonFireDTO> per = personService.retrievePeopleByAddress(address);
        if (per.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The address provided does not match any address");
        }
        return personService.retrievePeopleByAddress(address);
    }
}
