package com.safetynet.safetynetalerts.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;
import org.hibernate.annotations.Formula;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yahia CHERIFI
 * This class groupes medical record data
 * for more information about the annotations:
 * @see javax.persistence
 * @see lombok
 */

@Entity
@Table(name = "medical_record")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class MedicalRecord {

    /**
     * The medical record id.
     * a unique identifier of the record in database
     * automatically generated
     * Column annotation designs the column name in database
     */
    @Id
    @Column(name = "medical_record_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The first name of the medical record owner.
     * Column annotation designs the column name in database
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name of the medical record owner.
     * Column annotation designs the column name in database
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * The birthdate of the medical record owner.
     * Column annotation designs the column name in database
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "birth_date")
    private LocalDate birthdate;

    /**
     * The age of the medical record owner.
     * Formula annotation allows the calculation of a person's age
     * without persisting to database.
     * age is calculated from the birthdate.
     */
    @Formula(value = "(CAST((DATEDIFF(NOW(), birth_date) / 365.25)"
            + " AS DECIMAL(10,1)))")
    private Double age;

    /**
     * A List of strings that groups the person's allergies.
     * ElementCollection annotation allows saving a collection of strings in db
     * CollectionTable annotation creates a new table for allergies in db.
     */
    @ElementCollection
    @CollectionTable(name = "medical_record_allergies",
            joinColumns = @JoinColumn(name = "medical_record_id"))
    @Column(name = "allergies")
    private List<String> allergies;

    /**
     * A List of strings that groups the person's allergies.
     */
    @ElementCollection
    @CollectionTable(name = "medical_record_medications",
            joinColumns = @JoinColumn(name = "medical_record_id"))
    @Column(name = "medications")
    private List<String> medications;

    /**
     * Add a new allergy to the allergies list.
     * @param allergy the allergy to add
     */
    //a convenience method for adding allergies
    public void addAllergy(final String allergy) {
        if (allergies == null) {
            allergies = new ArrayList<>();
        }
        allergies.add(allergy);
    }

    /**
     * add a new medication to the medications list.
     * @param medication the medication to add
     */
    //a convenience method for adding medications
    public void addMedication(final String medication) {
        if (medications == null) {
            medications = new ArrayList<>();
        }
        medications.add(medication);
    }
}
