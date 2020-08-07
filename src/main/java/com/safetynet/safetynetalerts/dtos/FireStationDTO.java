package com.safetynet.safetynetalerts.dtos;

public class FireStationDTO {

    private String address;
    private Integer station;

    public FireStationDTO() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStation() {
        return station;
    }

    public void setStation(Integer station) {
        this.station = station;
    }
}
