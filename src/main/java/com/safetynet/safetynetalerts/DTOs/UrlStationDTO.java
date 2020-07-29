package com.safetynet.safetynetalerts.DTOs;

import java.util.List;

public class UrlStationDTO {

    private List<UrlPersonDTO> personList;
    private int numberOfChildren;
    private int numberOfAdults;

    public UrlStationDTO() {
    }

    public UrlStationDTO(List<UrlPersonDTO> urlPersonDTOList, int numberOfChildren, int numberOfAdults) {
        this.personList = urlPersonDTOList;
        this.numberOfChildren = numberOfChildren;
        this.numberOfAdults = numberOfAdults;
    }

    public List<UrlPersonDTO> getPersonList() {
        return personList;
    }

    public void setPersonList(List<UrlPersonDTO> personList) {
        this.personList = personList;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        for (UrlPersonDTO person: personList) {
            if (person.getAge() <= 18) {
                this.numberOfChildren += 1;
            }
        }
        this.numberOfChildren = numberOfChildren;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        for (UrlPersonDTO person: personList) {
            if (person.getAge() <= 18) {
                this.numberOfAdults += 1;
            }
        }
        this.numberOfAdults = numberOfAdults;
    }
}
