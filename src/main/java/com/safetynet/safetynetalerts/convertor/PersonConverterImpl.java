package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.DTOs.*;
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
    public PersonPhoneDTO personToPhoneInfoDTO(Person person) {
        return mapper.map(person, PersonPhoneDTO.class);
    }

    @Override
    public List<PersonPhoneDTO> personToPhoneInfosDTO(List<Person> personList) {
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
}
