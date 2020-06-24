package com.safetynet.safetynetalerts.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fire_stations")
public class FireStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fire_station_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "station")
    private String station;

    @ManyToMany(fetch = FetchType.LAZY ,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "fire_stations_persons",
            joinColumns = @JoinColumn(name = "fire_station_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> persons;

    public FireStation() {
    }

    public FireStation(String address, String station) {
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

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
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
