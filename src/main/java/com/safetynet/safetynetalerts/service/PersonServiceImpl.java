package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dtos.PersonDTO;
import com.safetynet.safetynetalerts.dtos.fireDTO.PersonFireDTO;
import com.safetynet.safetynetalerts.dtos.personInfoDto.PersonInfoDTO;
import com.safetynet.safetynetalerts.dtos.communityEmailDto.CommunityEmailDTO;
import com.safetynet.safetynetalerts.convertor.PersonConverter;
import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    //inject the PersonRepository
    private PersonRepository personRepository;

    private PersonConverter personConverter;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, PersonConverter personConverter) {
        this.personRepository = personRepository;
        this.personConverter = personConverter;
    }

    public PersonServiceImpl() {
    }

    @Override
    public List<PersonDTO> findAll() {
        List<Person> personList = personRepository.findAll();
        return personConverter.personToDAOsConverter(personList);
    }

    @Override
    public List<PersonInfoDTO> findPersonsByFirstNameAndLastName(String firstName, String lastName) {
        List<Person> personList = personRepository.findPersonsByFirstNameAndLastName(firstName, lastName);
        return personConverter.personToPersonInfoDOAConverter(personList);
    }

    @Override
    public List<CommunityEmailDTO> findEmailsByCity(String city) {
        List<Person> personList = personRepository.findEmailsByCity(city);
        return personConverter.personEmailConverter(personList);
    }

    @Override
    public List<Person> findByAddress(String address) {
        return personRepository.findByAddress(address);
    }

    @Override
    public List<Person> findChildrenByAddress(String address) {
        return personRepository.findByAddress(address);

    }

    @Override
    public Person save(Person thePerson) {
        return personRepository.save(thePerson);
    }

    @Override
    public Person findByFirstNameAndLastName(String firstName, String lastName) {
        return personRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        personRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<PersonFireDTO> retrievePeopleByAddress(String address) {
        List<Person> personList = personRepository.findByAddress(address);
        return personConverter.personToFireDTOsConverter(personList);
    }
}
