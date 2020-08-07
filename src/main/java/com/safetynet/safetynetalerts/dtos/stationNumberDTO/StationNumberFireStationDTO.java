package com.safetynet.safetynetalerts.dtos.stationNumberDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class StationNumberFireStationDTO {

    private List<StationNumberPersonDTO> persons;
    private int numberOfChildren;
    private int numberOfAdults;

    public List<StationNumberPersonDTO> getPersons() {
        return persons;
    }

    public int getNumberOfChildren() {
        personCount();
        return numberOfChildren;
    }

    public int getNumberOfAdults() {
        personCount();
        return numberOfAdults;
    }

    public void personCount() {
        List<StationNumberPersonDTO> children = new ArrayList<>();
        List<StationNumberPersonDTO> adults = new ArrayList<>();
        for (StationNumberPersonDTO person: persons) {
            if (person.getAge() <= 18 ) {
                children.add(person);
                numberOfChildren = children.size();
            } else {
                adults.add(person);
                numberOfAdults = adults.size();
            }
        }
    }
}
