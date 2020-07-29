package com.safetynet.safetynetalerts.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fire_station")
public class FireStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fire_station_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "station")
    private Integer station;

    @ManyToMany(fetch = FetchType.LAZY ,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "fire_station_person",
            joinColumns = @JoinColumn(name = "fire_station_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )

    private List<Person> persons;

    public FireStation() {
    }

    public FireStation(String address, Integer station) {
        this.address = address;
        this.station = station;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    //a convenience method for adding persons to fireStation

    public void addPerson (Person person) {
        if (persons == null) {
            persons = new ArrayList<>();
        }

        persons.add(person);
    }

    @Override
    public String toString() {
        return "FireStation{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", station='" + station + '\'' +
                ", persons=" + persons +
                '}';
    }
}
