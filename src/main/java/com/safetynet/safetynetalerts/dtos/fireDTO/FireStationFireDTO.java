package com.safetynet.safetynetalerts.dtos.fireDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Yahia CHERIFI
 * This class is used to contain data retrieved from fire station entities
 * it is used mainly for storing the station number
 * for more information about the annotation:
 * @see lombok
 */

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class FireStationFireDTO {

    /**
     * The fire station number.
     */
    private Integer station;
}
