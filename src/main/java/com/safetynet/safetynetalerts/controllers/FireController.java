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

/**
 * @author Yahia CHERIFI
 * This controller is responsible for
 * retrieving people who live at a give address
 */

@RestController
@RequestMapping("/fire")
public class FireController {

    /**
     * person service.
     * @see PersonService
     */
    private PersonService personService;

    /**
     * FireController logger.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(FireController.class);

    /**
     * Constructor injection.
     * injecting personService to the FireController
     * @param personServiceInstance
     */
    @Autowired
    public FireController(final PersonService personServiceInstance) {
        this.personService = personServiceInstance;
    }

    /**
     * Get request.
     * this method retrieves all people who live in a given address
     * @param address the provided address
     * @return a list of PersonFireDTO
     */
    @GetMapping()
    public List<PersonFireDTO> retrievePeopleByAddress(
            @RequestParam final String address) {
        LOGGER.debug("Get request sent from FireController");
        List<PersonFireDTO> persons = personService
                .retrievePeopleByAddress(address);
        if (persons.size() == 0) {
            LOGGER.error("The address provided '" + address
                    + "' does not match any address in the database");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The address provided does not match any address");
        }
        LOGGER.info("Persons who live in '" + address
                + "' retrieved from database");
        return personService.retrievePeopleByAddress(address);
    }
}
