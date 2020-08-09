package com.safetynet.safetynetalerts.dtos.floodDto;

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
public class FloodFireStationDTO {

    /**
     * The fire station id.
     */
    private Long id;

    /**
     * The fire station address.
     */
    private String address;

    /**
     * The list of persons covered by the fire station.
     */
    private List<FloodPersonDTO> persons;
}
