package com.safetynet.safetynetalerts.dtos.phoneAlertDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class PhoneAlertPersonDTO {

    private String firstName;
    private String lastName;
    private String phone;
}
