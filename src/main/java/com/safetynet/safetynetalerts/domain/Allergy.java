package com.safetynet.safetynetalerts.domain;

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

import java.util.List;

@Entity
@Table(name = "allergies")
public class Allergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergy_id")
    private Integer id;

    @Column(name = "allergy_name")
    private String allergyName;

    @ManyToMany(fetch = FetchType.LAZY ,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "medical_records_allergies",
            joinColumns = @JoinColumn(name = "allergies_id"),
            inverseJoinColumns = @JoinColumn(name = "medical_record_id")
    )
    private List<MedicalRecord> medicalRecords;

    public Allergy() {
    }

    public Allergy(String allergyName) {
        this.allergyName = allergyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAllergyName() {
        return allergyName;
    }

    public void setAllergyName(String name) {
        this.allergyName = name;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    @Override
    public String toString() {
        return "Allergy{" +
                "id=" + id +
                ", allergyName='" + allergyName + '\'' +
                ", medicalRecords=" + medicalRecords +
                '}';
    }
}
