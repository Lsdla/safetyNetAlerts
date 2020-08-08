package com.safetynet.safetynetalerts.dtos.phoneAlertDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Yahia CHERIFI
 * This class is used to contain data retrieved from fire station entities
 * for more information about the annotation:
 * @see lombok
 */

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class PhoneAlertFireStationDTO {

    /**
     * A list of the person's covered by the fire station.
     */
    private List<PhoneAlertPersonDTO> persons;
}
