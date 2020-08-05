package com.safetynet.safetynetalerts.DTOs.stationNumberDTO;

public class UrlPersonDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private Double age;

    public UrlPersonDTO() {
    }

    public UrlPersonDTO(String firstName, String lastName,
                        String address, String phone, Double age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.age = age;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }
}
