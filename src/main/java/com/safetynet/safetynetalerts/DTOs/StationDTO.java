package com.safetynet.safetynetalerts.DTOs;

import java.util.List;

public class StationDTO {

    private List<PersonPhoneDTO> phoneNumbersList;

    public StationDTO() {
    }

    public StationDTO(List<PersonPhoneDTO> phoneNumbersList) {
        this.phoneNumbersList = phoneNumbersList;
    }

    public List<PersonPhoneDTO> getPhoneNumbersList() {
        return phoneNumbersList;
    }

    public void setPhoneNumbersList(List<PersonPhoneDTO> phoneNumbersList) {
        this.phoneNumbersList = phoneNumbersList;
    }
}
