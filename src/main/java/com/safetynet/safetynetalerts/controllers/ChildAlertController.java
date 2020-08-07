package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.childDTO.ChildDTO;
import com.safetynet.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/childAlert")
public class ChildAlertController {

    private PersonService personService;

    private final Logger LOGGER = LogManager.getLogger(ChildAlertController.class);

    @Autowired
    public ChildAlertController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<List<ChildDTO>> getChildrenByAddress(@RequestParam String address){
        LOGGER.debug("Get request sent from the ChildAlertController");
        return personService.findChildrenByAddress(address);
    }
}
