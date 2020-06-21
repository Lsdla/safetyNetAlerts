package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    //inject PersonService
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //add mapping for POST /person - add new person
    @GetMapping("/add")
    public Person addPerson(@RequestBody Person thePerson) {
        //in case of passing an id in the json.. we have to set it to 0
        //mainly used to force saving a new item instead of updating
        //an existing item that has the id entered in the json
        thePerson.setId(0L);

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
    @DeleteMapping(value = "/delete/{firstName}&{lastName}")
    public String deletePerson(@PathVariable String firstName, @PathVariable String lastName) {
        Person person = personService.findByFirstNameAndLastName(firstName, lastName);

        //throw an exception if no person with the same first name and last name was found in db
        if (person == null) {
            throw new RuntimeException("No person with these credentials " +
                    "'" +  firstName + " " + lastName + "'" + " was found!");
        }

        personService.deleteByFirstNameAndLastName(firstName, lastName);

        return ("'" + firstName + " " + lastName +"'" + " deleted successfully");
    }

}
