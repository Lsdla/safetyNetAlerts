package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.PersonDTO;
import com.safetynet.safetynetalerts.dtos.personInfoDto.PersonInfoDTO;
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
     * Constructor injection.
     * @param personServiceInstance
     */
    @Autowired
    public PersonController(final PersonService personServiceInstance) {
        this.personService = personServiceInstance;
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
     * @param thePerson
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
     * @param thePerson
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

        //throw an exception if no person with
        //the same first name and last name was found in db
        if (person == null) {
            LOGGER.error("Failed to delete person: '" + firstName + " "
                    + lastName + "' does not exist in our databas");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No person with these credentials '"
                            + firstName + " " + lastName + "' was found!");
        }

        personService.deleteByFirstNameAndLastName(firstName, lastName);
        LOGGER.info("Person '" + firstName + " "
                + lastName + "' deleted from database");
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
        LOGGER.debug("Get request sent from the PersonController to retrieve"
                + " information belonging to '"
                + firstName + " " + lastName + "'");
        return personService
                .findPersonsByFirstNameAndLastName(firstName, lastName);
    }

}
