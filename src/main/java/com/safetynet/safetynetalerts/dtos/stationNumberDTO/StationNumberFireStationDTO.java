package com.safetynet.safetynetalerts.dtos.stationNumberDTO;

import java.util.ArrayList;
import java.util.List;

public class StationNumberFireStationDTO {

    private List<StationNumberPersonDTO> persons;
    private int numberOfChildren = 0;
    private int numberOfAdults = 0;

    public StationNumberFireStationDTO() {
    }

    public StationNumberFireStationDTO(List<StationNumberPersonDTO> persons, int numberOfChildren, int numberOfAdults) {
        this.persons = persons;
        this.numberOfChildren = numberOfChildren;
        this.numberOfAdults = numberOfAdults;
    }

    public List<StationNumberPersonDTO> getPersons() {
        return persons;
    }

    public void setPersons(List<StationNumberPersonDTO> persons) {
        this.persons = persons;
    }

    public int getNumberOfChildren() {
        personCount();
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public int getNumberOfAdults() {
        personCount();
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
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
