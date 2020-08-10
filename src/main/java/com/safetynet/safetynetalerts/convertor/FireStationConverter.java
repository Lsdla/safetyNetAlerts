package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.dtos.FireStationDTO;
import com.safetynet.safetynetalerts.dtos.firedto.FireStationFireDTO;
import com.safetynet.safetynetalerts.dtos.flooddto.FloodFireStationDTO;
import com.safetynet.safetynetalerts.dtos.stationnumberdto.StationNumberFireStationDTO;
import com.safetynet.safetynetalerts.dtos.phonealertdto.PhoneAlertFireStationDTO;
import com.safetynet.safetynetalerts.domain.FireStation;

import java.util.List;

/**
 * @author Yahia CHERIFI
 * FireStation converter interface
 * provides method that allow conversion from
 * FireStation entity to DTO
 */
public interface FireStationConverter {

    /**
     * converts from FireStation entity to FireStationDTO.
     * @param fireStation entity
     * @return FireStationDTO
     */
    FireStationDTO fireStationToDAOConverter(FireStation fireStation);

    /**
     * converts a list of FireStation entities to a list of FireStationDTOs.
     * @param fireStations list of fire station entities
     * @return a list of FireStationDTOs
     */
    List<FireStationDTO> fireStationToDAOsConverter(
            List<FireStation> fireStations);

    /**
     * converts from FireStation entity to PhoneAlertFireStationDTO.
     * @param fireStation entity
     * @return PhoneAlertFireStationDTO
     */
    PhoneAlertFireStationDTO phoneAlertStationToDTOConverter(
            FireStation fireStation);

    /**
     * converts from FireStation entity to StationNumberFireStationDTO.
     * @param fireStation entity
     * @return StationNumberFireStationDTO
     */
    StationNumberFireStationDTO stationNumberFireStationDTO(
            FireStation fireStation);

    /**
     * converts from FireStation entity to FloodFireStationDTO.
     * @param fireStation entity
     * @return FloodFireStationDTO
     */
    FloodFireStationDTO floodFireStationDAOConverter(FireStation fireStation);

    /**
     * converts a list of FireStation entities
     * to a list of FloodFireStationDTOs.
     * @param fireStations a list of fire station entities
     * @return a list of FloodFireStationDTOs
     */
    List<FloodFireStationDTO> floodFireStationDAOsConverter(
            List<FireStation> fireStations);

    /**
     * converts from FireStation entity to FireStationFireDTO.
     * @param fireStation entity
     * @return FireStationFireDTO
     */
    FireStationFireDTO fireStationToFireStationFireDTOConverter(
            FireStation fireStation);

    /**
     * converts a list of FireStation entities
     * to a list of FireStationFireDTO.
     * @param fireStations a list of fire station entities
     * @return a list of FireStationFireDTO
     */
    List<FireStationFireDTO> fireStationsToFireStationFireDTOConverters(
            List<FireStation> fireStations);
}

