package com.safetynet.safetynetalerts.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Yahia CHERIFI
 * This class is used to contain data retrieved from person entities
 * for more information about the annotation:
 * @see lombok
 */

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class PersonDTO {

    /**
     * The person's first name.
     */
    private String firstName;

    /**
     * The person's last name.
     */
    private String lastName;

    /**
     * The person's address.
     */
    private String address;

    /**
     * The city where a person lives.
     */
    private String city;

    /**
     * The zip code of the city.
     */
    private String zip;

    /**
     * The person's phone number.
     */
    private String phone;

    /**
     * The person's email address.
     */
    private String email;

    /**
     * The person's medical record.
     */
    private MedicalRecordDTO medicalRecord;

    /**
     * The fire stations that cover a given person.
     */
    private List<FireStationDTO> fireStations;
}
