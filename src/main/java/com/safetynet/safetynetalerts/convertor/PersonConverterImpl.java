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
    public CommunityEmailDTO personEmail(Person person) {
        return mapper.map(person, CommunityEmailDTO.class);
    }

    @Override
    public List<CommunityEmailDTO> personEmailConverter(List<Person> personList) {
        return personList.stream().map(this::personEmail).collect(Collectors.toList());
    }

    @Override
    public PersonInfoDTO personToPersonInfoDOAConverter(Person person) {
        return mapper.map(person, PersonInfoDTO.class);
    }

    @Override
    public List<PersonInfoDTO> personToPersonInfoDOAConverter(List<Person> personList) {
        return personList.stream().map(this::personToPersonInfoDOAConverter).collect(Collectors.toList());
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
    public PersonFireDTO personToFireDTOConverter(Person person) {
        return mapper.map(person, PersonFireDTO.class);
    }

    @Override
    public List<PersonFireDTO> personToFireDTOsConverter(List<Person> personList) {
        return personList.stream().map(this::personToFireDTOConverter).collect(Collectors.toList());
    }

    @Override
    public FloodPersonDTO floodPersonDTOConverter(Person person) {
        return mapper.map(person, FloodPersonDTO.class);
    }

    @Override
    public List<FloodPersonDTO> floodPersonDTOsConverter(List<Person> personList) {
        return personList.stream().map(this::floodPersonDTOConverter).collect(Collectors.toList());
    }

    @Override
    public ChildDTO childDTOConverter(Person person) {
        return mapper.map(person, ChildDTO.class);
    }

    @Override
    public List<ChildDTO> childDTOsConverter(List<Person> personList) {
        return personList.stream().map(this::childDTOConverter).collect(Collectors.toList());
    }

    @Override
    public StationNumberPersonDTO urlPersonConverter(Person person) {
        return mapper.map(person, StationNumberPersonDTO.class);
    }

    @Override
    public List<StationNumberPersonDTO> urlPersonsConverter(List<Person> personList) {
        return personList.stream().map(this::urlPersonConverter).collect(Collectors.toList());
    }
}
