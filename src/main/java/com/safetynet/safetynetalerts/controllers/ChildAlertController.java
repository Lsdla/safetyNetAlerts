package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dtos.childDTO.ChildDTO;
import com.safetynet.safetynetalerts.convertor.PersonConverter;
import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/childAlert")
public class ChildAlertController {

    private PersonService personService;
    private PersonConverter personConverter;

    @Autowired
    public ChildAlertController(PersonService personService, PersonConverter personConverter) {
        this.personService = personService;
        this.personConverter = personConverter;
    }

    @GetMapping
    public List<ChildDTO> getChildrenByAddress(@RequestParam String address){
        List<Person> peopleByAddress = personService.findByAddress(address);
        List<Person> children = new ArrayList<>();
        for (Person person: peopleByAddress) {
            if (person.getAge() <= 18) {
                children.add(person);
            }
        }

        return personConverter.childDTOsConverter(children);
    }
}
