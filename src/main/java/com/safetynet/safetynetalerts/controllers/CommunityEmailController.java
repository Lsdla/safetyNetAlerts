package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.communityEmailDto.CommunityEmailDTO;
import com.safetynet.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/communityEmail")
public class CommunityEmailController {

    private PersonService personService;

    private final Logger LOGGER = LogManager.getLogger(CommunityEmailController.class);

    @Autowired
    public CommunityEmailController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    @ResponseBody
    public List<CommunityEmailDTO> getEmails(@RequestParam String city) {
        LOGGER.debug("Get request sent from the CommunityEmailController");
        return personService.findEmailsByCity(city);
    }
}
