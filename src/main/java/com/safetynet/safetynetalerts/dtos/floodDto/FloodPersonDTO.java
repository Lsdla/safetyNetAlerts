package com.safetynet.safetynetalerts.dtos.floodDto;

import com.safetynet.safetynetalerts.dtos.MedicalRecordDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class FloodPersonDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private MedicalRecordDTO medicalRecord;
}
