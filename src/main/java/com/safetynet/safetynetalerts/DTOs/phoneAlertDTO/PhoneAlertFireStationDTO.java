package com.safetynet.safetynetalerts.DTOs.phoneAlertDTO;

import java.util.List;

public class PhoneAlertFireStationDTO {

    private List<PhoneAlertPersonDTO> persons;

    public PhoneAlertFireStationDTO() {
    }

    public List<PhoneAlertPersonDTO> getPersons() {
        return persons;
    }

    public void setPersons(List<PhoneAlertPersonDTO> persons) {
        this.persons = persons;
    }
}
