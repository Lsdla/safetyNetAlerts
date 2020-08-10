package com.safetynet.safetynetalerts.dtos.firedto;

import com.safetynet.safetynetalerts.dtos.MedicalRecordDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Yahia CHERIFI
 * This class is used to contain data retrieved from person entities
 * it is used mainly for storing person related data
 * for more information about the annotation:
 * @see lombok
 */

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class PersonFireDTO {

    /**
     * The person's first name.
     */
    private String firstName;

    /**
     * The person's last name.
     */
    private String lastName;

    /**
     * The person's phone number.
     */
    private String phone;

    /**
     * The person's medical record.
     */
    private MedicalRecordDTO medicalRecord;

    /**
     * A list of the fire stations that cover a person.
     */
    private List<FireStationFireDTO> fireStations;
}
