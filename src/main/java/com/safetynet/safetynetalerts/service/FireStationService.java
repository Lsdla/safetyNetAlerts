package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dtos.FireStationDTO;
import com.safetynet.safetynetalerts.dtos.floodDto.FloodFireStationDTO;
import com.safetynet.safetynetalerts.dtos.stationNumberDTO.StationNumberFireStationDTO;
import com.safetynet.safetynetalerts.dtos.phoneAlertDTO.PhoneAlertFireStationDTO;
import com.safetynet.safetynetalerts.domain.FireStation;

import java.util.List;
import java.util.Optional;

public interface FireStationService {

    FireStation save(FireStation fireStation);

    Optional<FireStation> findById(Long id);

    void deleteById(Long id);

    List<FireStationDTO> findAll();

    List<FloodFireStationDTO> findFireStationsById(List<Long> id);

    StationNumberFireStationDTO getOneFireStationById(Long id);

    PhoneAlertFireStationDTO findFireStationById(Long id);
}
