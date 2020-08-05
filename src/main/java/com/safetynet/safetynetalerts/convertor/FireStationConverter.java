package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.dtos.FireStationDTO;
import com.safetynet.safetynetalerts.dtos.floodDto.FloodFireStationDTO;
import com.safetynet.safetynetalerts.dtos.stationNumberDTO.StationNumberFireStation;
import com.safetynet.safetynetalerts.dtos.phoneAlertDTO.PhoneAlertFireStationDTO;
import com.safetynet.safetynetalerts.domain.FireStation;

import java.util.List;

public interface FireStationConverter {

    FireStationDTO fireStationToDAOConverter(FireStation fireStation);
    List<FireStationDTO> fireStationToDAOsConverter(List<FireStation> fireStations);

    PhoneAlertFireStationDTO stationToDTOConverter(FireStation fireStation);

    StationNumberFireStation urlFireStationToDAOConverter(FireStation fireStation);

    FloodFireStationDTO floodFireStationDAOConverter(FireStation fireStation);
    List<FloodFireStationDTO> floodFireStationDAOsConverter(List<FireStation> fireStations);
}

