package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.DTOs.FireStationDTO;
import com.safetynet.safetynetalerts.DTOs.StationDTO;
import com.safetynet.safetynetalerts.domain.FireStation;

import java.util.List;
import java.util.Optional;

public interface FireStationConverter {

    FireStationDTO fireStationToDAOConverter(FireStation fireStation);
    List<FireStationDTO> fireStationToDAOsConverter(List<FireStation> fireStations);

    StationDTO stationToDTOConverter(Optional<FireStation> fireStation);
}

