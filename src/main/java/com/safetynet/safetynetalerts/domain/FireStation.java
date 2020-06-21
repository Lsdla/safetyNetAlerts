package com.safetynet.safetynetalerts.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fire_stations")
public class FireStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "station")
    private String station;

    @OneToMany(mappedBy = "fireStation",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                       CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "person_id")
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

    //convenience method for bi-directional relationship
    public void addPerson(Person thePerson) {
        if (persons == null) {
            persons = new ArrayList<>();
        }
        persons.add(thePerson);
        thePerson.setFireStation(this);
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
