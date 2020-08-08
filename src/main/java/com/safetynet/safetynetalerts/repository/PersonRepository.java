package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Yahia CHERIFI
 * exposes basic CRUD operations on person
 * @see Query
 * @see JpaRepository
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * Retrieve a person by first name and last name.
     * Key word findBy allows searching for the desired person
     * @param firstName the person's first name
     * @param lastName the person's last name
     * @return the found person
     */
    Person findByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Delete a person.
     * Key word deleteBy allows deletion
     * @param firstName the person's first name
     * @param lastName the person's last name
     */
    void deleteByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Find a list of persons by their first names and last names.
     * @param firstName the person's first name
     * @param lastName the person's last name
     * @return a list of persons
     */
    @Query(value = "SELECT p from Person p"
            + " WHERE p.firstName = ?1 AND p.lastName = ?2")
    List<Person> findPersonsByFirstNameAndLastName(
            String firstName, String lastName);

    /**
     * Retrieve email addresses of people living in a given city.
     * @param city a city a person/persons live
     * @return a list of person's emails
     */
    @Query(value = "SELECT p FROM Person p where p.city = ?1")
    List<Person> findEmailsByCity(String city);

    /**
     * find people by address.
     * @param address the person's address
     * @return a list of people who live in a given address
     */
    List<Person> findByAddress(String address);
}
