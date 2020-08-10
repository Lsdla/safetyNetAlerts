package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.dtos.FireStationDTO;
import com.safetynet.safetynetalerts.dtos.firedto.FireStationFireDTO;
import com.safetynet.safetynetalerts.dtos.flooddto.FloodFireStationDTO;
import com.safetynet.safetynetalerts.dtos.stationnumberdto.StationNumberFireStationDTO;
import com.safetynet.safetynetalerts.dtos.phonealertdto.PhoneAlertFireStationDTO;
import com.safetynet.safetynetalerts.domain.FireStation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yahia CHERIFI
 * Implementation of the FireStationConverter interface
 * @see FireStationConverter
 */

@Component
public class FireStationConverterImpl implements FireStationConverter {

    /**
     * modelMapper to be injected.
     * used to map objects to each other
     */
    private final ModelMapper mapper;

    /**
     * Constructor injection.
     * @param modelMapper modelMapper
     */
    @Autowired
    public FireStationConverterImpl(final ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    /**
     * @see FireStationConverter
     * @param fireStation entity
     * @return FireStationDTO
     */
    @Override
    public FireStationDTO fireStationToDAOConverter(
            final FireStation fireStation) {
        return mapper.map(fireStation, FireStationDTO.class);
    }

    /**
     * @see FireStationConverter
     * @param fireStations list of fire station entities
     * @return a list of FireStationDTO
     */
    @Override
    public List<FireStationDTO> fireStationToDAOsConverter(
            final List<FireStation> fireStations) {
        return fireStations.stream().map(this::fireStationToDAOConverter)
                .collect(Collectors.toList());
    }

    /**
     * @see FireStationConverter
     * @param fireStation entity
     * @return PhoneAlertFireStationDTO
     */
    @Override
    public PhoneAlertFireStationDTO phoneAlertStationToDTOConverter(
            final FireStation fireStation) {
        return mapper.map(fireStation, PhoneAlertFireStationDTO.class);
    }

    /**
     * @see FireStationConverter
     * @param fireStation entity
     * @return StationNumberFireStationDTO
     */
    @Override
    public StationNumberFireStationDTO stationNumberFireStationDTO(
            final FireStation fireStation) {
        return mapper.map(fireStation, StationNumberFireStationDTO.class);
    }

    /**
     * @see FireStationConverter
     * @param fireStation entity
     * @return FloodFireStationDTO
     */
    @Override
    public FloodFireStationDTO floodFireStationDAOConverter(
            final FireStation fireStation) {
        return mapper.map(fireStation, FloodFireStationDTO.class);
    }

    /**
     * @see FireStationConverter
     * @param fireStations a list of fire station entities
     * @return a list of FloodFireStationDTO
     */
    @Override
    public List<FloodFireStationDTO> floodFireStationDAOsConverter(
            final List<FireStation> fireStations) {
        return fireStations.stream()
                .map(this::floodFireStationDAOConverter)
                .collect(Collectors.toList());
    }

    /**
     * @see FireStationConverter
     * @param fireStation entity
     * @return FireStationFireDTO
     */
    @Override
    public FireStationFireDTO fireStationToFireStationFireDTOConverter(
            final FireStation fireStation) {
        return mapper.map(fireStation, FireStationFireDTO.class);
    }

    /**
     * @see FireStationConverter
     * @param fireStations a list of fire station entities
     * @return a list of FireStationFireDTO
     */
    @Override
    public List<FireStationFireDTO> fireStationsToFireStationFireDTOConverters(
            final List<FireStation> fireStations) {
        return fireStations.stream()
                .map(this::fireStationToFireStationFireDTOConverter)
                .collect(Collectors.toList());
    }
}
