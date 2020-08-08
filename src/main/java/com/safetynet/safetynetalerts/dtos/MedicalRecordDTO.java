package com.safetynet.safetynetalerts.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Yahia CHERIFI
 * This class is used to contain data retrieved from medical record entities
 * for more information about the annotation:
 * @see lombok
 */

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class MedicalRecordDTO {

    /**
     * The age of the medical record owner.
     */
    private Double age;

    /**
     * The allergies associated to to the medical record.
     */
    private List<String> allergies;

    /**
     * The medication associated to medical record.
     */
    private List<String> medications;
}
