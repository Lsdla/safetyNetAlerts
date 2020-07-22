package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByFirstNameAndLastName(String firstName, String lastName);

    void deleteByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "SELECT p FROM Person p where p.city = ?1")
    List<Person> findEmailsByCity(String city);
}
