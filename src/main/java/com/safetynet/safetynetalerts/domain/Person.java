package com.safetynet.safetynetalerts.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yahia CHERIFI
 * This class groups a person's data
 * for more information about the annotations:
 * for more information about the annotations:
 * @see javax.persistence
 * @see lombok
 */

@Entity
@Table(name = "person")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class Person {

    /**
     * A person's id.
     * a unique identifier of a person in db
     * it is automatically generated
     * Column designs the column name in database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "person_id", updatable = false)
    private Long id;

    /**
     * The person's first name.
     * it cannot be null
     * Column designs the column name in database
     */
    @NotNull(message = "First name required")
    @Column(name = "first_name", updatable = false)
    private String firstName;

    /**
     * The person's last name.
     * it cannot be null
     * Column designs the column name in database
     */
    @NotNull(message = "Last name required")
    @Column(name = "last_name", updatable = false)
    private String lastName;

    /**
     * The person's address.
     * it cannot be null
     * Column designs the column name in database
     */
    @NotNull(message = "address required")
    @Column(name = "address")
    private String address;

    /**
     * The city where a person lives.
     * it cannot be null
     * Column designs the column name in database
     */
    @NotNull(message = "City required")
    @Column(name = "city")
    private String city;

    /**
     * The zip code of the city.
     * it cannot be null
     * Column designs the column name in database
     */
    @NotNull(message = "Zip required")
    @Column(name = "zip")
    private String zip;

    /**
     * The phone number of a person.
     * it cannot not be null
     * Column designs the column name in database
     */
    @NotNull(message = "Phone number required")
    @Column(name = "phone")
    private String phone;

    /**
     * The email address of a person.
     * it cannot be null
     * Column designs the column name in database
     */
    @NotNull(message = "Email address required")
    @Column(name = "email")
    private String email;

    /**
     * The medical record associated to a person.
     * OneToOne designs the relationship between person and medical record
     * JoinColumn refers to a column that stores
     * the medical record foreign key in the person table,
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;

    /**
     * The fire station/stations that can cover a given person.
     * ManyToMany designs the relationship between person and fireStation
     * JoinTable designs the join table
     */
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "fire_station_person",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "fire_station_id")
    )
    private List<FireStation> fireStations;

    /**
     * The person's age.
     * Transient to avoid persisting it to db
     * it is calculated when needed
     */
    @Transient
    @JsonIgnore
    private Double age;

    /**
     * a convenience method for adding a fire station to the fireStations list.
     * @param fireStation the fire station to add
     */
    public void addFireStation(final FireStation fireStation) {
        if (fireStations == null) {
            fireStations = new ArrayList<>();
        }

        fireStations.add(fireStation);
    }

    /**
     * getter for the person's age.
     * @return age: calculated in medical record
     */
    public Double getAge() {
      return medicalRecord.getAge();
   }
}
