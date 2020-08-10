package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.PersonDTO;
import com.safetynet.safetynetalerts.dtos.personinfodto.PersonInfoDTO;
import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author Yahia CHERIFI
 * this controller is responsible for
 * CRUD operation on Person
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    /**
     * PersonController logger.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(PersonController.class);

    /**
     * personService.
     * @see PersonService
     */
    private PersonService personService;

    /**
     * Used in logging messages.
     * Data provided by users will be changed
     * some characters will omitted for security purposes:
     * Logging injection
     */
    private static final String DANGEROUS_CHARACTERS =  "[\n\r\t]";

    /**
     * DANGEROUS_CHARACTERS will be replaced by REPLACEMENT_CHARACTER.
     */
    private static final String REPLACEMENT_CHARACTER = "_";

    /**
     * Constructor injection.
     * @param service personService
     */
    @Autowired
    public PersonController(final PersonService service) {
        this.personService = service;
    }

    /**
     * Get request.
     * retrieves all persons from database
     * @return a list of PersonDTO
     */
    @GetMapping("/persons")
    @ResponseBody
    public List<PersonDTO> findPersons() {
        LOGGER.debug("Get request sent from the"
                + "PersonController to retrieve all persons from database");
        return personService.findAll();
    }

    /**
     * Post request.
     * used to save a new person to database
     * @param thePerson person to save
     * @return the saved person
     */
    @PostMapping("/add")
    public Person addPerson(@RequestBody final Person thePerson) {
        LOGGER.debug("Post request sent from the PersonController");
        personService.save(thePerson);
        return thePerson;
    }

    /**
     * Put request.
     * @param thePerson person to update
     * @return the updated person
     */
    @PutMapping("/update")
    public Person updatePerson(@RequestBody final Person thePerson) {
        LOGGER.debug("Put request sent from the PersonController");
        personService.save(thePerson);
        return thePerson;
    }

    /**
     * Delete request.
     * @param firstName a person's first name
     * @param lastName a person's last name
     * @return a message if the person had been deleted
     * throw an exception if deletion failed
     */
    @DeleteMapping("/delete/{firstName}&{lastName}")
    public String deletePerson(@PathVariable final String firstName,
                               @PathVariable final String lastName) {
        LOGGER.debug("Delete request sent from the PersonController");
        Person person = personService
                .findByFirstNameAndLastName(firstName, lastName);

        String secureFirstNameCharacters = firstName
                .replaceAll(DANGEROUS_CHARACTERS, REPLACEMENT_CHARACTER);
        String secureLastNameCharacters = lastName
                .replaceAll(DANGEROUS_CHARACTERS, REPLACEMENT_CHARACTER);

        //throw an exception if no person with
        //the same first name and last name was found in db
        if (person == null) {
            LOGGER.error("Failed to delete person"
                            + " no matching {} {} found",
                    secureFirstNameCharacters, secureLastNameCharacters);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No person with these credentials '"
                            + secureFirstNameCharacters + " "
                            + secureLastNameCharacters + "' was found!");
        }

        personService.deleteByFirstNameAndLastName(firstName, lastName);
        LOGGER.info("{} {} "
                        + "deleted from database.",
                secureFirstNameCharacters, secureLastNameCharacters);
        return ("'" + firstName + " " + lastName + "' deleted successfully");
    }

    /**
     * Get request.
     * retrieves information belonging to the person
     * @param firstName the person's first name
     * @param lastName the person's last name
     * @return a list of PersonInfoDTO
     */
    @GetMapping("/personInfo/{firstName}&{lastName}")
    public List<PersonInfoDTO> getPersonInfo(
            @PathVariable final String firstName,
            @PathVariable final String lastName) {
        String secureFirstNameCharacters = firstName
                .replaceAll(DANGEROUS_CHARACTERS, REPLACEMENT_CHARACTER);
        String secureLastNameCharacters = lastName
                .replaceAll(DANGEROUS_CHARACTERS, REPLACEMENT_CHARACTER);
        LOGGER.debug("Get request sent from the PersonController to retrieve"
                + " information belonging to {} {}",
                secureFirstNameCharacters, secureLastNameCharacters);
        return personService
                .findPersonsByFirstNameAndLastName(firstName, lastName);
    }

}
