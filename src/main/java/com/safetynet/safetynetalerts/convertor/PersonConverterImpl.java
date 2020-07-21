package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.DTOs.PersonDTO;
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
    public List<PersonDTO> personToDAOs(List<Person> personList) {
        return personList.stream().map(this::personToDAOConverter).collect(Collectors.toList());
    }
}
