package com.safetynet.safetynetalerts.dtos.phoneAlertDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class PhoneAlertFireStationDTO {

    private List<PhoneAlertPersonDTO> persons;
}
