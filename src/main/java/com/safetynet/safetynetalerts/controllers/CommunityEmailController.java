package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.DTOs.communityEmailDto.CommunityEmailDTO;
import com.safetynet.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/communityEmail")
public class CommunityEmailController {

    private PersonService personService;

    @Autowired
    public CommunityEmailController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    @ResponseBody
    public List<CommunityEmailDTO> getEmails(@RequestParam String city) {
            return personService.findEmailsByCity(city);
    }
}
