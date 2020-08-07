package com.safetynet.safetynetalerts.dtos.stationNumberDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class StationNumberPersonDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private Double age;
}
