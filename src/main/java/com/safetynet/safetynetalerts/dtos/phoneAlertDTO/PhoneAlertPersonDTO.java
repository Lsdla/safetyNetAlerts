package com.safetynet.safetynetalerts.dtos.phoneAlertDTO;

public class PhoneAlertPersonDTO {

    private String firstName;
    private String lastName;
    private String phone;

    public PhoneAlertPersonDTO() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
