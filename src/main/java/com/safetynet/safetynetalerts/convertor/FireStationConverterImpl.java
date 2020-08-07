package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.dtos.FireStationDTO;
import com.safetynet.safetynetalerts.dtos.fireDTO.FireStationFireDTO;
import com.safetynet.safetynetalerts.dtos.floodDto.FloodFireStationDTO;
import com.safetynet.safetynetalerts.dtos.stationNumberDTO.StationNumberFireStationDTO;
import com.safetynet.safetynetalerts.dtos.phoneAlertDTO.PhoneAlertFireStationDTO;
import com.safetynet.safetynetalerts.domain.FireStation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FireStationConverterImpl implements FireStationConverter {

    private ModelMapper mapper;

    @Autowired
    public FireStationConverterImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public FireStationDTO fireStationToDAOConverter(FireStation fireStation) {
        return mapper.map(fireStation, FireStationDTO.class);
    }

    @Override
    public List<FireStationDTO> fireStationToDAOsConverter(List<FireStation> fireStations) {
        return fireStations.stream().map(this::fireStationToDAOConverter).collect(Collectors.toList());
    }

    @Override
    public PhoneAlertFireStationDTO phoneAlertStationToDTOConverter(FireStation fireStation) {
        return mapper.map(fireStation, PhoneAlertFireStationDTO.class);
    }

    @Override
    public StationNumberFireStationDTO stationNumberFireStationDTO(FireStation fireStation) {
        return mapper.map(fireStation, StationNumberFireStationDTO.class);
    }

    @Override
    public FloodFireStationDTO floodFireStationDAOConverter(FireStation fireStation) {
        return mapper.map(fireStation, FloodFireStationDTO.class);
    }

    @Override
    public List<FloodFireStationDTO> floodFireStationDAOsConverter(List<FireStation> fireStations) {
        return fireStations.stream().map(this::floodFireStationDAOConverter).collect(Collectors.toList());
    }

    @Override
    public FireStationFireDTO fireStationToFireStationFireDTOConverter(FireStation fireStation) {
        return mapper.map(fireStation, FireStationFireDTO.class);
    }

    @Override
    public List<FireStationFireDTO> fireStationsToFireStationFireDTOConverters(List<FireStation> fireStations) {
        return fireStations.stream().map(this::fireStationToFireStationFireDTOConverter).collect(Collectors.toList());
    }
}
