package com.safetynet.safetynetalerts.dtos.childDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class ChildDTO {

    private String firstName;
    private String lastName;
    private Double age;
}
