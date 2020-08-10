package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.childdto.ChildDTO;
import com.safetynet.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yahia CHERIFI
 * This controller is responsible for
 * retrieving children who live in a given address
 */

@RestController
@RequestMapping("/childAlert")
public class ChildAlertController {

    /**
     * Person service.
     * @see PersonService
     */
    private PersonService personService;

    /**
     * ChildAlertController logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(ChildAlertController.class);

    /**
     * Constructor injection.
     * injecting personService to the ChildAlertController
     * @param service PersonService
     */
    @Autowired
    public ChildAlertController(final PersonService service) {
        this.personService = service;
    }

    /**
     * Get request.
     * this method retrieves children who live at a given address
     * their household members will be retrieved also
     * @param address the provided address
     * @return a list that contains a list of childDtos
     * and a list of their household members
     */
    @GetMapping
    public List<List<ChildDTO>> getChildrenByAddress(
            @RequestParam final String address) {
        LOGGER.debug("Get request sent from"
                + " the ChildAlertController");
        return personService.findChildrenByAddress(address);
    }
}
