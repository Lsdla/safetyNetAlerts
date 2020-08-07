package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.dtos.FireStationDTO;
import com.safetynet.safetynetalerts.dtos.fireDTO.FireStationFireDTO;
import com.safetynet.safetynetalerts.dtos.floodDto.FloodFireStationDTO;
import com.safetynet.safetynetalerts.dtos.stationNumberDTO.StationNumberFireStationDTO;
import com.safetynet.safetynetalerts.dtos.phoneAlertDTO.PhoneAlertFireStationDTO;
import com.safetynet.safetynetalerts.domain.FireStation;

import java.util.List;

public interface FireStationConverter {

    FireStationDTO fireStationToDAOConverter(FireStation fireStation);
    List<FireStationDTO> fireStationToDAOsConverter(List<FireStation> fireStations);

    PhoneAlertFireStationDTO phoneAlertStationToDTOConverter(FireStation fireStation);

    StationNumberFireStationDTO stationNumberFireStationDTO(FireStation fireStation);

    FloodFireStationDTO floodFireStationDAOConverter(FireStation fireStation);
    List<FloodFireStationDTO> floodFireStationDAOsConverter(List<FireStation> fireStations);

    FireStationFireDTO fireStationToFireStationFireDTOConverter(FireStation fireStation);
    List<FireStationFireDTO> fireStationsToFireStationFireDTOConverters(List<FireStation> fireStations);
}

