package com.safetynet.safetynetalerts.service;

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

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public void save(Person thePerson) {
        personRepository.save(thePerson);
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
