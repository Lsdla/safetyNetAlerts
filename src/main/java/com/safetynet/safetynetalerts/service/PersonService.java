package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dtos.childDTO.ChildDTO;
import com.safetynet.safetynetalerts.dtos.communityEmailDto.CommunityEmailDTO;
import com.safetynet.safetynetalerts.dtos.PersonDTO;
import com.safetynet.safetynetalerts.dtos.fireDTO.PersonFireDTO;
import com.safetynet.safetynetalerts.dtos.personInfoDto.PersonInfoDTO;
import com.safetynet.safetynetalerts.domain.Person;

import java.util.List;

/**
 * @author Yahia CHERIFI
 * This interface contains methods that provide
 * the logic to operated on the data sent to and from
 * the controllers and the repository layer
 */
public interface PersonService {

    /**
     * Saves a new person to db.
     * @param thePerson the person to be saved
     * @return the saved person
     */
    Person save(Person thePerson);

    /**
     * Retrieves a person from db by his/her first and last names.
     * @param firstName the person's first name
     * @param lastName the person's last name
     * @return the found person
     */
    Person findByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Delete a person by his/her first and last name.
     * @param firstName the person's first name
     * @param lastName the person's last name
     */
    void deleteByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Retrieve all persons from db.
     * @return a list of PersonDTO
     */
    List<PersonDTO> findAll();

    /**
     * Retrieve a list of persons who have the same first and last names.
     * @param firstName the person's first name
     * @param lastName the person's last name
     * @return a list of PersonInfoDTOs
     */
    List<PersonInfoDTO> findPersonsByFirstNameAndLastName(
            String firstName, String lastName);

    /**
     * Retrieve email addresses of people living.
     * in a given city
     * @param city the city where people live
     * @return a list of CommunityEmailDTO
     */
    List<CommunityEmailDTO> findEmailsByCity(String city);

    /**
     * Retrieve people by their address.
     * @param address the address where people live
     * @return a list of persons
     */
    List<Person> findByAddress(String address);

    /**
     * Retrieves children who live in a given address.
     * @param address the address
     * @return a list of children list and household list (ChildDTO objects)
     */
    List<List<ChildDTO>> findChildrenByAddress(String address);

    /**
     * Retrieve a list of persons who live in a given address.
     * @param address the address
     * @return a list of PersonFireDTO
     */
    List<PersonFireDTO> retrievePeopleByAddress(String address);
}
