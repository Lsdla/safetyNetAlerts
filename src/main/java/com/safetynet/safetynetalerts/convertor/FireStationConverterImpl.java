package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.DTOs.FireStationDTO;
import com.safetynet.safetynetalerts.DTOs.FloodFireStationDTO;
import com.safetynet.safetynetalerts.DTOs.StationDTO;
import com.safetynet.safetynetalerts.DTOs.UrlStationDTO;
import com.safetynet.safetynetalerts.domain.FireStation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
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
    public StationDTO stationToDTOConverter(Optional<FireStation> fireStation) {
        return mapper.map(fireStation, StationDTO.class);
    }

    @Override
    public UrlStationDTO urlFireStationToDAOConverter(FireStation fireStation) {
        return mapper.map(fireStation, UrlStationDTO.class);
    }

    @Override
    public FloodFireStationDTO floodFireStationDAOConverter(FireStation fireStation) {
        return mapper.map(fireStation, FloodFireStationDTO.class);
    }

    @Override
    public List<FloodFireStationDTO> floodFireStationDAOsConverter(List<FireStation> fireStations) {
        return fireStations.stream().map(this::floodFireStationDAOConverter).collect(Collectors.toList());
    }
}
