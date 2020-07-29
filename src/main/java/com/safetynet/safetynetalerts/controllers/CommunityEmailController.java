package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.DTOs.CommunityEmailDTO;
import com.safetynet.safetynetalerts.convertor.PersonConverter;
import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/communityEmail")
public class CommunityEmailController {

    private PersonService personService;

    private PersonConverter personConverter;

    @Autowired
    public CommunityEmailController(PersonService personService, PersonConverter personConverter) {
        this.personService = personService;
        this.personConverter = personConverter;
    }

    @GetMapping("/{city}")
    @ResponseBody
    public List<CommunityEmailDTO> getEmails(@PathVariable String city) {
        List<Person> personList = personService.findEmailsByCity(city);
        return personConverter.personEmailConverter(personList);
    }
}
