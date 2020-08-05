package com.safetynet.safetynetalerts.dtos.childDTO;

public class ChildDTO {

    private String firstName;
    private String lastName;
    private Double age;

    public ChildDTO() {
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

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }
}
