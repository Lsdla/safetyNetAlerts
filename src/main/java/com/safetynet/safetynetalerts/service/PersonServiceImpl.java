package com.safetynet.safetynetalerts.service;

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
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> findPersonsByFirstNameAndLastName(String firstName, String lastName) {
        return personRepository.findPersonsByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Person> findEmailsByCity(String city) {
        return personRepository.findEmailsByCity(city);
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


}
