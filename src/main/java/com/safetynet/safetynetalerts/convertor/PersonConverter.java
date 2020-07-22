package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.DTOs.PersonDTO;
import com.safetynet.safetynetalerts.DTOs.CommunityEmailDTO;
import com.safetynet.safetynetalerts.DTOs.PersonInfoDTO;
import com.safetynet.safetynetalerts.domain.Person;

import java.util.List;

public interface PersonConverter {

    PersonDTO personToDAOConverter(Person person);
    List<PersonDTO> personToDAOsConverter(List<Person> personList);

    CommunityEmailDTO personEmail(Person person);
    List<CommunityEmailDTO> personEmailConverter(List<Person> personList);

    PersonInfoDTO personToPersonInfoDOAConverter(Person person);
    List<PersonInfoDTO> personToPersonInfoDOAConverter(List<Person> personList);
}
