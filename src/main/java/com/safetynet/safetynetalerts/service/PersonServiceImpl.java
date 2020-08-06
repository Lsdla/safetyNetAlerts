package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dtos.PersonDTO;
import com.safetynet.safetynetalerts.dtos.childDTO.ChildDTO;
import com.safetynet.safetynetalerts.dtos.fireDTO.PersonFireDTO;
import com.safetynet.safetynetalerts.dtos.personInfoDto.PersonInfoDTO;
import com.safetynet.safetynetalerts.dtos.communityEmailDto.CommunityEmailDTO;
import com.safetynet.safetynetalerts.convertor.PersonConverter;
import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
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
        return personConverter.personsToPersonInfoDTOsConverter(personList);
    }

    @Override
    public List<CommunityEmailDTO> findEmailsByCity(String city) {
        List<Person> personList = personRepository.findEmailsByCity(city);
        return personConverter.personsToEmailDTOsConverter(personList);
    }

    @Override
    public List<Person> findByAddress(String address) {
        return personRepository.findByAddress(address);
    }

    @Override
    public List<List<ChildDTO>> findChildrenByAddress(String address) {
        //retrieve all people who live in a given address
        List<Person> peopleByAddress = findByAddress(address);
        //a list to which children and their household members will be saved
        List<List<Person>> childrenAndHouseHoldMembers = new ArrayList<>();
        //a list to which adult household members will be saved
        List<Person> adults = new ArrayList<>();
        //a list to which children will be saved
        List<Person> children = new ArrayList<>();
        //combine both lists, children and adults, by saving them to childrenAndHouseHoldMembers list
        childrenAndHouseHoldMembers.add(children);
        childrenAndHouseHoldMembers.add(adults);
        //check if peopleByAddress have any persons whose age is 18 or less
        for (Person p : peopleByAddress) {
            if (p.getAge() <= 18) {
                children.add(p);
            } else {
                adults.add(p);
            }
        }

        //check if children list contain persons
        if (children.size() > 0) {
            //return the converted childrenAndHouseHoldMembers if there is children in the list
            return personConverter.childrenListToListConverter(childrenAndHouseHoldMembers);
        } else {
            //return empty list if no children were found
            return Collections.emptyList();
        }
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
        return personConverter.personsToPersonFireDTOsConverter(personList);
    }
}
