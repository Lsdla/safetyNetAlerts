package com.safetynet.safetynetalerts.DTOs;

public class FireStationDTO {

    private String address;
    private Integer station;

    public FireStationDTO() {
    }

    public FireStationDTO(String address, Integer station) {
        this.address = address;
        this.station = station;
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
