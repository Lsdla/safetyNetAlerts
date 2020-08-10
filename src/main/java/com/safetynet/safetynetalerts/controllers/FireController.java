package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.firedto.PersonFireDTO;
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
    private final PersonService personService;

    /**
     * FireController logger.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(FireController.class);

    /**
     * Constructor injection.
     * injecting personService to the FireController
     * @param service PersonService
     */
    @Autowired
    public FireController(final PersonService service) {
        this.personService = service;
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
        String secureAddressCharacters = address.replaceAll("[\n\r\t]", "_");
        if (persons.isEmpty()) {
            LOGGER.error("No people found or wrong provided address: {}",
                    secureAddressCharacters);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No people found or wrong address provided");
        }
        LOGGER.info("Persons who live in {} retrieved from database.",
                secureAddressCharacters);
        return personService.retrievePeopleByAddress(address);
    }
}
