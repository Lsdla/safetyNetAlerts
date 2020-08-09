package com.safetynet.safetynetalerts.dtos.phoneAlertDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Yahia CHERIFI
 * This class is used to contain data retrieved from person entities
 * for more information about the annotation:
 * @see lombok
 */

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class PhoneAlertPersonDTO {

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
}
