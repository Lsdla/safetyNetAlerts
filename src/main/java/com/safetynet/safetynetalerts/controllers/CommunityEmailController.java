package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.communityemaildto.CommunityEmailDTO;
import com.safetynet.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Yahia CHERIFI
 * This controller is responsible for
 * retrieving email addresses of people who live in a given city
 */
@RestController
@RequestMapping("/communityEmail")
public class CommunityEmailController {

    /**
     * person service.
     * @see PersonService
     */
    private final PersonService personService;

    /**
     * CommunityEmailController logger.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(CommunityEmailController.class);

    /**
     * Constructor injection.
     * injecting personService to the ChildAlertController
     * @param service PersonService
     */
    @Autowired
    public CommunityEmailController(final PersonService service) {
        this.personService = service;
    }

    /**
     * Get request.
     * this method retrieves all the email
     * addresses of people living in a given city
     * @param city the provided city
     * @return a list of EmailDTO
     */
    @GetMapping()
    @ResponseBody
    public List<CommunityEmailDTO> getEmails(
            @RequestParam final String city) {
        LOGGER.debug("Get request sent from the CommunityEmailController");
        return personService.findEmailsByCity(city);
    }
}
