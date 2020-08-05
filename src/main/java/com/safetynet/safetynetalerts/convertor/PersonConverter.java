package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.dtos.*;
import com.safetynet.safetynetalerts.dtos.childDTO.ChildDTO;
import com.safetynet.safetynetalerts.dtos.communityEmailDto.CommunityEmailDTO;
import com.safetynet.safetynetalerts.dtos.fireDTO.PersonFireDTO;
import com.safetynet.safetynetalerts.dtos.floodDto.FloodPersonDTO;
import com.safetynet.safetynetalerts.dtos.personInfoDto.PersonInfoDTO;
import com.safetynet.safetynetalerts.dtos.phoneAlertDTO.PhoneAlertPersonDTO;
import com.safetynet.safetynetalerts.dtos.stationNumberDTO.StationNumberPersonDTO;
import com.safetynet.safetynetalerts.domain.Person;

import java.util.List;

public interface PersonConverter {

    PersonDTO personToDAOConverter(Person person);
    List<PersonDTO> personToDAOsConverter(List<Person> personList);

    CommunityEmailDTO personEmail(Person person);
    List<CommunityEmailDTO> personEmailConverter(List<Person> personList);

    PersonInfoDTO personToPersonInfoDOAConverter(Person person);
    List<PersonInfoDTO> personToPersonInfoDOAConverter(List<Person> personList);

    PhoneAlertPersonDTO personToPhoneInfoDTO(Person person);
    List<PhoneAlertPersonDTO> personToPhoneInfosDTO(List<Person> personList);

    PersonFireDTO personToFireDTOConverter(Person person);
    List<PersonFireDTO> personToFireDTOsConverter(List<Person> personList);

    FloodPersonDTO floodPersonDTOConverter(Person person);
    List<FloodPersonDTO> floodPersonDTOsConverter(List<Person> personList);

    ChildDTO childDTOConverter(Person person);
    List<ChildDTO> childDTOsConverter(List<Person> personList);

    StationNumberPersonDTO urlPersonConverter(Person person);
    List<StationNumberPersonDTO> urlPersonsConverter(List<Person> personList);
}
