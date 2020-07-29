package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.DTOs.PersonFireDTO;
import com.safetynet.safetynetalerts.convertor.PersonConverter;
import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fire")
public class FireController {

    private PersonService personService;

    private PersonConverter personConverter;

    @Autowired
    public FireController(PersonService personService, PersonConverter personConverter) {
        this.personService = personService;
        this.personConverter = personConverter;
    }

    @GetMapping("/address={address}")
    private List<PersonFireDTO> persons(@PathVariable String address) {
        List<Person> personList = personService.findByAddress(address);
        return personConverter.personToFireDTOsConverter(personList);
    }
}
