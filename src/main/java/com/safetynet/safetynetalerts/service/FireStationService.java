package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.DTOs.FireStationDTO;
import com.safetynet.safetynetalerts.DTOs.FloodFireStationDTO;
import com.safetynet.safetynetalerts.DTOs.UrlStationDTO;
import com.safetynet.safetynetalerts.DTOs.phoneAlertDTO.PhoneAlertFireStationDTO;
import com.safetynet.safetynetalerts.domain.FireStation;

import java.util.List;
import java.util.Optional;

public interface FireStationService {

    FireStation save(FireStation fireStation);

    Optional<FireStation> findById(Long id);

    void deleteById(Long id);

    List<FireStationDTO> findAll();

    List<FloodFireStationDTO> findFireStationsById(List<Long> id);

    UrlStationDTO urlStationDTO(Long id);

    PhoneAlertFireStationDTO findFireStationById(Long id);
}
