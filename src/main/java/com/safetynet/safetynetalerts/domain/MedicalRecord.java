package com.safetynet.safetynetalerts.domain;

import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medical_record")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class MedicalRecord {

    @Id
    @Column(name = "medical_record_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Formula(value = "(CAST((DATEDIFF(NOW(), birth_date) / 365.25) AS DECIMAL(10,1)))")
    private Double age;


    @ElementCollection
    @CollectionTable(name = "medical_record_allergies", joinColumns = @JoinColumn(name = "medical_record_id"))
    @Column(name = "allergies")
    private List<String> allergies;

    @ElementCollection
    @CollectionTable(name = "medical_record_medications", joinColumns = @JoinColumn(name = "medical_record_id"))
    @Column(name = "medications")
    private List<String> medications;

    //a convenience method for adding allergies
    public void addAllergy(String allergy) {
        if (allergies == null) {
            allergies = new ArrayList<>();
        }
        allergies.add(allergy);
    }

    //a convenience method for adding medications
    public void addMedication(String medication) {
        if (medications == null) {
            medications = new ArrayList<>();
        }
        medications.add(medication);
    }
}
