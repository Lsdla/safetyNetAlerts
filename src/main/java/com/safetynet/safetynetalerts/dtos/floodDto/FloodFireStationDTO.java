package com.safetynet.safetynetalerts.dtos.floodDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class FloodFireStationDTO {

    private Long id;
    private String address;
    private List<FloodPersonDTO> persons;
}
