package com.safetynet.safetynetalerts.dtos.floodDto;

import java.util.List;

public class FloodFireStationDTO {

    private Long id;
    private String address;
    private List<FloodPersonDTO> persons;

    public FloodFireStationDTO() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
