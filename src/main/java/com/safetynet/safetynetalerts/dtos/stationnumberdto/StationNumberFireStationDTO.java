package com.safetynet.safetynetalerts.dtos.stationnumberdto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
public class StationNumberFireStationDTO {

    /**
     * A list of the persons covered by the fire station.
     */
    private List<StationNumberPersonDTO> persons;

    /**
     * The number of children covered by the fire station.
     */
    private int numberOfChildren;

    /**
     * The number of adults covered by the fire station.
     */
    private int numberOfAdults;

    /**
     * Getter of the numberOfChildren.
     * @return the exact number of children covered by the fire station
     */
    public int getNumberOfChildren() {
        personCount();
        return numberOfChildren;
    }

    /**
     * Getter of the numberOfAdults.
     * @return the exact number of adult covered by the fire station
     */
    public int getNumberOfAdults() {
        personCount();
        return numberOfAdults;
    }

    /**
     * Filters adults and children.
     * stores them in different lists.
     * count the number of each elements in each list
     * and attribute that number either to numberOfAdults
     * or numberOfChildren
     */
    public void personCount() {
        final int childMaxAge = 18;
        List<StationNumberPersonDTO> children = new ArrayList<>();
        List<StationNumberPersonDTO> adults = new ArrayList<>();
        for (StationNumberPersonDTO person: persons) {
            if (person.getAge() <= childMaxAge) {
                children.add(person);
                numberOfChildren = children.size();
            } else {
                adults.add(person);
                numberOfAdults = adults.size();
            }
        }
    }
}
