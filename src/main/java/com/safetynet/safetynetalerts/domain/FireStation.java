package com.safetynet.safetynetalerts.domain;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yahia CHERIFI
 * This class groupes fire station data
 * for more information about the annotations:
 * @see javax.persistence
 * @see lombok
 */

@Entity
@Table(name = "fire_station")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class FireStation {

    /**
     * The fire station id.
     * a unique identifier of the fire station in database
     * it is generated and incremented automatically
     * Column designs the column name in database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fire_station_id", nullable = false, updatable = false)
    private Long id;

    /**
     * The fire station's address.
     * Column designs the column name in database
     */
    @Column(name = "address")
    private String address;

    /**
     * the station number.
     * Column designs the column name in database
     */
    @Column(name = "station")
    private Integer station;

    /**
     * A list of the person's covered by the fire station.
     * ManyToMany annotation designs the relationship
     * between FireStation and Person
     * JoinTable refers to the join table in database
     */
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "fire_station_person",
            joinColumns = @JoinColumn(name = "fire_station_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )

    private List<Person> persons;

    /**
     * a convenience method for adding persons to fireStation.
     * @param person to be added
     */
    public void addPerson(final Person person) {
        if (persons == null) {
            persons = new ArrayList<>();
        }

        persons.add(person);
    }
}
