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

    CommunityEmailDTO personToEmailDTOConverter(Person person);
    List<CommunityEmailDTO> personsToEmailDTOsConverter(List<Person> personList);

    PersonInfoDTO personToPersonInfoDTOConverter(Person person);
    List<PersonInfoDTO> personsToPersonInfoDTOsConverter(List<Person> personList);

    PhoneAlertPersonDTO personToPhoneInfoDTO(Person person);
    List<PhoneAlertPersonDTO> personToPhoneInfosDTO(List<Person> personList);

    PersonFireDTO personToPersonFireDTOConverter(Person person);
    List<PersonFireDTO> personsToPersonFireDTOsConverter(List<Person> personList);

    FloodPersonDTO personToFloodPersonDTOConverter(Person person);
    List<FloodPersonDTO> personsToFloodPersonDTOsConverter(List<Person> personList);

    ChildDTO personToChildDTOConverter(Person person);
    List<ChildDTO> personsToChildDTOsConverter(List<Person> personList);
    List<List<ChildDTO>> childrenListToListConverter(List<List<Person>> persons);

    StationNumberPersonDTO personToStationNumberPersonDTOConverter(Person person);
    List<StationNumberPersonDTO> personsToStationNumberPersonDTOsConverter(List<Person> personList);
}
