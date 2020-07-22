package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.DTOs.PersonDTO;
import com.safetynet.safetynetalerts.convertor.PersonConverter;
import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    //inject PersonService
    private PersonService personService;

    private PersonConverter personConverter;



    @Autowired
    public PersonController(PersonService personService, PersonConverter personConverter) {
        this.personService = personService;
        this.personConverter = personConverter;
    }

    @GetMapping("/persons")
    @ResponseBody
    public List<PersonDTO> findPersons() {
        List<Person> personList = personService.findAll();
        return personConverter.personToDAOs(personList);
    }

    //add mapping for POST /person - add new person
    @PostMapping("/add")
    public Person addPerson(@RequestBody Person thePerson) {
        personService.save(thePerson);
        return thePerson;
    }

    //add mapping for PUT /person - update an existing person
    @PutMapping("/update")
    public Person updatePerson(@RequestBody Person thePerson) {
        personService.save(thePerson);
        return thePerson;
    }

    //add mapping for DELETE /person -delete a person from db
    @DeleteMapping("/delete/{firstName}&{lastName}")
    public String deletePerson(@PathVariable String firstName, @PathVariable String lastName) {
        Person person = personService.findByFirstNameAndLastName(firstName, lastName);

        //throw an exception if no person with the same first name and last name was found in db
        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No person with these credentials '" +
                    firstName + " " + lastName + "' was found!");
        }

        personService.deleteByFirstNameAndLastName(firstName, lastName);

        return ("'" + firstName + " " + lastName +"' deleted successfully");
    }

}
