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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonConverterImpl implements PersonConverter {

    private ModelMapper mapper;

    @Autowired
    public PersonConverterImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public PersonDTO personToDAOConverter(Person person) {
        return mapper.map(person, PersonDTO.class);
    }

    @Override
    public List<PersonDTO> personToDAOsConverter(List<Person> personList) {
        return personList.stream().map(this::personToDAOConverter).collect(Collectors.toList());
    }

    @Override
    public CommunityEmailDTO personToEmailDTOConverter(Person person) {
        return mapper.map(person, CommunityEmailDTO.class);
    }

    @Override
    public List<CommunityEmailDTO> personsToEmailDTOsConverter(List<Person> personList) {
        return personList.stream().map(this::personToEmailDTOConverter).collect(Collectors.toList());
    }

    @Override
    public PersonInfoDTO personToPersonInfoDTOConverter(Person person) {
        return mapper.map(person, PersonInfoDTO.class);
    }

    @Override
    public List<PersonInfoDTO> personsToPersonInfoDTOsConverter(List<Person> personList) {
        return personList.stream().map(this::personToPersonInfoDTOConverter).collect(Collectors.toList());
    }

    @Override
    public PhoneAlertPersonDTO personToPhoneInfoDTO(Person person) {
        return mapper.map(person, PhoneAlertPersonDTO.class);
    }

    @Override
    public List<PhoneAlertPersonDTO> personToPhoneInfosDTO(List<Person> personList) {
        return personList.stream().map(this::personToPhoneInfoDTO).collect(Collectors.toList());
    }

    @Override
    public PersonFireDTO personToPersonFireDTOConverter(Person person) {
        return mapper.map(person, PersonFireDTO.class);
    }

    @Override
    public List<PersonFireDTO> personsToPersonFireDTOsConverter(List<Person> personList) {
        return personList.stream().map(this::personToPersonFireDTOConverter).collect(Collectors.toList());
    }

    @Override
    public FloodPersonDTO personToFloodPersonDTOConverter(Person person) {
        return mapper.map(person, FloodPersonDTO.class);
    }

    @Override
    public List<FloodPersonDTO> personsToFloodPersonDTOsConverter(List<Person> personList) {
        return personList.stream().map(this::personToFloodPersonDTOConverter).collect(Collectors.toList());
    }

    @Override
    public ChildDTO personToChildDTOConverter(Person person) {
        return mapper.map(person, ChildDTO.class);
    }

    @Override
    public List<ChildDTO> personsToChildDTOsConverter(List<Person> personList) {
        return personList.stream().map(this::personToChildDTOConverter).collect(Collectors.toList());
    }

    @Override
    public List<List<ChildDTO>> childrenListToListConverter(List<List<Person>> persons) {
        return persons.stream().map(this::personsToChildDTOsConverter).collect(Collectors.toList());
    }

    @Override
    public StationNumberPersonDTO personToStationNumberPersonDTOConverter(Person person) {
        return mapper.map(person, StationNumberPersonDTO.class);
    }

    @Override
    public List<StationNumberPersonDTO> personsToStationNumberPersonDTOsConverter(List<Person> personList) {
        return personList.stream().map(this::personToStationNumberPersonDTOConverter).collect(Collectors.toList());
    }
}
