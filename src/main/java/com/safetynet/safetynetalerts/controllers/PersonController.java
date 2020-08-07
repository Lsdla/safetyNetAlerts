package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.PersonDTO;
import com.safetynet.safetynetalerts.dtos.personInfoDto.PersonInfoDTO;
import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final Logger LOGGER = LogManager.getLogger(PersonController.class);

    //inject PersonService
    private PersonService personService;


    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    @ResponseBody
    public List<PersonDTO> findPersons() {
        LOGGER.debug("Get request sent from the PersonController to retrieve all persons from database");
        return personService.findAll();
    }

    //add mapping for POST /person - add new person
    @PostMapping("/add")
    public Person addPerson(@RequestBody Person thePerson) {
        LOGGER.debug("Post request sent from the PersonController");
        personService.save(thePerson);
        return thePerson;
    }

    //add mapping for PUT /person - update an existing person
    @PutMapping("/update")
    public Person updatePerson(@RequestBody Person thePerson) {
        LOGGER.debug("Put request sent from the PersonController");
        personService.save(thePerson);
        return thePerson;
    }

    //add mapping for DELETE /person -delete a person from db
    @DeleteMapping("/delete/{firstName}&{lastName}")
    public String deletePerson(@PathVariable String firstName, @PathVariable String lastName) {
        LOGGER.debug("Delete request sent from the PersonController");
        Person person = personService.findByFirstNameAndLastName(firstName, lastName);

        //throw an exception if no person with the same first name and last name was found in db
        if (person == null) {
            LOGGER.error("Failed to delete person: '" + firstName + " " + lastName + "' does not exist in our databas" );
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No person with these credentials '" +
                    firstName + " " + lastName + "' was found!");
        }

        personService.deleteByFirstNameAndLastName(firstName, lastName);
        LOGGER.info("Person '" + firstName + " " + lastName + "' deleted from database");
        return ("'" + firstName + " " + lastName +"' deleted successfully");
    }

    @GetMapping("/personInfo/{firstName}&{lastName}")
    public List<PersonInfoDTO> getPersonInfo(@PathVariable String firstName, @PathVariable String lastName) {
        LOGGER.debug("Get request sent from the PersonController to retrieve information belonging to '" + firstName + " " + lastName +"'");
        return personService.findPersonsByFirstNameAndLastName(firstName, lastName);
    }

}
