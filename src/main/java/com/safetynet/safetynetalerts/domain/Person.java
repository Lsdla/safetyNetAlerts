package com.safetynet.safetynetalerts.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "person")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "person_id", updatable = false)
    private Long id;

    @NotNull(message = "First name required")
    @Column(name = "first_name", updatable = false)
    private String firstName;

    @NotNull(message = "Last name required")
    @Column(name = "last_name", updatable = false)
    private String lastName;

    @NotNull(message = "address required")
    @Column(name = "address")
    private String address;

    @NotNull(message = "City required")
    @Column(name = "city")
    private String city;

    @NotNull(message = "Zip required")
    @Column(name = "zip")
    private String zip;

    @NotNull(message = "Phone number required")
    @Column(name = "phone")
    private String phone;

    @NotNull(message = "Email address required")
    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;

    @ManyToMany(fetch = FetchType.LAZY ,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "fire_station_person",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "fire_station_id")
    )
    private List<FireStation> fireStations;


    @Transient
    @JsonIgnore
    private Double age;

    //a convenience method for adding persons to fireStation
    public void addFireStation(FireStation fireStation) {
        if (fireStations == null) {
            fireStations = new ArrayList<>();
        }

        fireStations.add(fireStation);
    }

    public Double getAge() {
      return medicalRecord.getAge();
   }
}
