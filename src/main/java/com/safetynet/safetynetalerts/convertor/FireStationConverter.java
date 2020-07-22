package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.DTOs.FireStationDTO;
import com.safetynet.safetynetalerts.domain.FireStation;

import java.util.List;

public interface FireStationConverter {

    FireStationDTO fireStationToDAOConverter(FireStation fireStation);
    List<FireStationDTO> fireStationToDAOsConverter(List<FireStation> fireStations);
}
