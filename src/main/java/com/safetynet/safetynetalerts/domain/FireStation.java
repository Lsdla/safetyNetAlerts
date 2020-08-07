package com.safetynet.safetynetalerts.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fire_station")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
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

    //a convenience method for adding persons to fireStation

    public void addPerson (Person person) {
        if (persons == null) {
            persons = new ArrayList<>();
        }

        persons.add(person);
    }
}
