package com.safetynet.safetynetalerts.DTOs;

import java.util.List;

public class FloodFireStationDTO {

    private String address;
    private List<FloodPersonDTO> persons;

    public FloodFireStationDTO() {
    }

    public FloodFireStationDTO(String address, List<FloodPersonDTO> persons) {
        this.address = address;
        this.persons = persons;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<FloodPersonDTO> getPersons() {
        return persons;
    }

    public void setPersons(List<FloodPersonDTO> persons) {
        this.persons = persons;
    }
}
