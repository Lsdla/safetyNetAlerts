package com.safetynet.safetynetalerts.dtos.personInfoDto;

import com.safetynet.safetynetalerts.dtos.MedicalRecordDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class PersonInfoDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private MedicalRecordDTO medicalRecord;
}
