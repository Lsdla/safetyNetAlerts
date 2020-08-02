package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.DTOs.*;
import com.safetynet.safetynetalerts.domain.Person;

import java.util.List;

public interface PersonConverter {

    PersonDTO personToDAOConverter(Person person);
    List<PersonDTO> personToDAOsConverter(List<Person> personList);

    CommunityEmailDTO personEmail(Person person);
    List<CommunityEmailDTO> personEmailConverter(List<Person> personList);

    PersonInfoDTO personToPersonInfoDOAConverter(Person person);
    List<PersonInfoDTO> personToPersonInfoDOAConverter(List<Person> personList);

    PersonPhoneDTO personToPhoneInfoDTO(Person person);
    List<PersonPhoneDTO> personToPhoneInfosDTO(List<Person> personList);

    PersonFireDTO personToFireDTOConverter(Person person);
    List<PersonFireDTO> personToFireDTOsConverter(List<Person> personList);

    FloodPersonDTO floodPersonDTOConverter(Person person);
    List<FloodPersonDTO> floodPersonDTOsConverter(List<Person> personList);

    ChildDTO childDTOConverter(Person person);
    List<ChildDTO> childDTOsConverter(List<Person> personList);

    UrlPersonDTO urlPersonConverter(Person person);
    List<UrlPersonDTO> urlPersonsConverter(List<Person> personList);
}
