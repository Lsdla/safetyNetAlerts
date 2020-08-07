package com.safetynet.safetynetalerts.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class MedicalRecordDTO {

    private Double age;
    private List<String> allergies;
    private List<String> medications;
}
