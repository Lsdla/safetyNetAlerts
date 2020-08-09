package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dtos.PersonDTO;
import com.safetynet.safetynetalerts.dtos.childDTO.ChildDTO;
import com.safetynet.safetynetalerts.dtos.fireDTO.PersonFireDTO;
import com.safetynet.safetynetalerts.dtos.personInfoDto.PersonInfoDTO;
import com.safetynet.safetynetalerts.dtos.communityEmailDto.CommunityEmailDTO;
import com.safetynet.safetynetalerts.convertor.PersonConverter;
import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.repository.PersonRepository;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yahia CHERIFI
 * This class implements PersonService interface
 * @see PersonService
 */
@Service
@Transactional
@NoArgsConstructor
public class PersonServiceImpl implements PersonService {

    /**
     * PersonRepository to be injected.
     */
    private PersonRepository personRepository;

    /**
     * PersonConverter to be injected.
     */
    private PersonConverter personConverter;

    /**
     * PersonServiceImpl logger.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(PersonServiceImpl.class);

    /**
     * Constructor injection.
     * @param repository PersonRepository
     * @param converter PersonConverter
     */
    @Autowired
    public PersonServiceImpl(
            final PersonRepository repository,
            final PersonConverter converter) {
        this.personRepository = repository;
        this.personConverter = converter;
    }

    /**
     * Calls the repository layer.
     * @return a list of PersonDTO
     */
    @Override
    public List<PersonDTO> findAll() {
        List<Person> personList = personRepository.findAll();
        return personConverter.personToDAOsConverter(personList);
    }

    /**
     * Calls the repository layer.
     * @param firstName the person's first name
     * @param lastName the person's last name
     * @return a list of PersonInfoDTOs
     * @throws ResponseStatusException if no person returned from db
     */
    @Override
    public List<PersonInfoDTO> findPersonsByFirstNameAndLastName(
            final String firstName, final String lastName) {
        List<Person> personList = personRepository
                .findPersonsByFirstNameAndLastName(firstName, lastName);
        if (personList.size() == 0) {
            LOGGER.error("No matching " + firstName
                    + " " + lastName + " were found in database");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No persons were found");
        }
        LOGGER.info("Person(s) that have these first names and last names: '"
                + firstName + " " + lastName
                + "' were retrieved from database");
        return personConverter.personsToPersonInfoDTOsConverter(personList);
    }

    /**
     * Calls the repository layer.
     * @param city the city where people live
     * @return a list of CommunityEmailDTOs
     */
    @Override
    public List<CommunityEmailDTO> findEmailsByCity(final String city) {
        List<Person> personList = personRepository.findEmailsByCity(city);
        return personConverter.personsToEmailDTOsConverter(personList);
    }

    /**
     * Calls the repository layer.
     * @param address the address where people live
     * @return a list of persons who live in a give address
     */
    @Override
    public List<Person> findByAddress(final String address) {
        return personRepository.findByAddress(address);
    }

    /**
     * Calls the repository layer.
     * @param address the address
     * @return a list of ChildDTO list.
     */
    @Override
    public List<List<ChildDTO>> findChildrenByAddress(final String address) {
        final int childMaxAge = 18;
        //retrieve all people who live in a given address
        List<Person> peopleByAddress = findByAddress(address);

        //a list to which children and their household members will be saved
        List<List<Person>> childrenAndHouseHoldMembers = new ArrayList<>();

        //a list to which adult household members will be saved
        List<Person> adults = new ArrayList<>();

        //a list to which children will be saved
        List<Person> children = new ArrayList<>();

        //combine both lists, children and adults,
        // by saving them to childrenAndHouseHoldMembers list
        childrenAndHouseHoldMembers.add(children);
        childrenAndHouseHoldMembers.add(adults);

        LOGGER.debug("Searching for children in this address: " + address);
        //check if peopleByAddress have any persons whose age is 18 or less
        for (Person p : peopleByAddress) {
            if (p.getAge() <= childMaxAge) {
                children.add(p);
            } else {
                adults.add(p);
            }
        }

        //check if children list contain persons
        if (children.size() > 0) {
            LOGGER.info("Children who live at the "
                    + address + " were retrieved from database");
            //return the converted childrenAndHouseHoldMembers
            // if there is children in the list
            return personConverter
                    .childrenListToListConverter(childrenAndHouseHoldMembers);
        } else {
            LOGGER.info("There are no children who live at the " + address);
            //return empty list if no children were found
            return Collections.emptyList();
        }
    }

    /**
     * Calls the repository layer.
     * @param thePerson the person to be saved
     * @return the saved person
     */
    @Override
    public Person save(final Person thePerson) {
        return personRepository.save(thePerson);
    }

    /**
     * Calls the repository layer.
     * @param firstName the person's first name
     * @param lastName the person's last name
     * @return the found person
     */
    @Override
    public Person findByFirstNameAndLastName(
            final String firstName, final String lastName) {
        return personRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    /**
     * Calls the repository layer.
     * @param firstName the person's first name
     * @param lastName the person's last name
     */
    @Override
    public void deleteByFirstNameAndLastName(
            final String firstName, final String lastName) {
        personRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }

    /**
     * Calls the repository layer.
     * @param address the address
     * @return a list of PersonFireDTOs
     */
    @Override
    public List<PersonFireDTO> retrievePeopleByAddress(final String address) {
        List<Person> personList = personRepository.findByAddress(address);
        return personConverter.personsToPersonFireDTOsConverter(personList);
    }
}
