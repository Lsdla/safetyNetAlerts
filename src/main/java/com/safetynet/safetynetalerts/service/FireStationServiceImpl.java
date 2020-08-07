package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dtos.FireStationDTO;
import com.safetynet.safetynetalerts.dtos.floodDto.FloodFireStationDTO;
import com.safetynet.safetynetalerts.dtos.stationNumberDTO.StationNumberFireStationDTO;
import com.safetynet.safetynetalerts.dtos.phoneAlertDTO.PhoneAlertFireStationDTO;
import com.safetynet.safetynetalerts.convertor.FireStationConverter;
import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.repository.FireStationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FireStationServiceImpl implements FireStationService {

    //inject FireStationRepository
    private FireStationRepository fireStationRepository;
    private FireStationConverter fireStationConverter;

    private final Logger LOGGER = LogManager.getLogger(FireStationServiceImpl.class);

    @Autowired
    public FireStationServiceImpl(FireStationRepository fireStationRepository, FireStationConverter fireStationConverter) {
        this.fireStationRepository = fireStationRepository;
        this.fireStationConverter = fireStationConverter;
    }

    //save a new fire station/update an existing fire station
    @Override
    public FireStation save(FireStation fireStation) {
        return fireStationRepository.save(fireStation);
    }

    //find a fire station by its number
    @Override
    public Optional<FireStation> findById(Long id) {
        return fireStationRepository.findById(id);
    }

    //delete a fire station by its number
    @Override
    public void deleteById(Long id) {
        fireStationRepository.deleteById(id);
    }

    @Override
    public List<FireStationDTO> findAll() {
        List<FireStation> fireStations = fireStationRepository.findAll();
        return fireStationConverter.fireStationToDAOsConverter(fireStations);
    }

    @Override
    public List<FloodFireStationDTO> findFireStationsById(List<Long> id) {
        List<FireStation> fireStations = fireStationRepository.findFireStationsById(id);
        return fireStationConverter.floodFireStationDAOsConverter(fireStations);
    }

    @Override
    public StationNumberFireStationDTO getOneFireStationById(Long id) {
        FireStation fireStation = fireStationRepository.getOne(id);
        return fireStationConverter.stationNumberFireStationDTO(fireStation);
    }

    @Override
    public PhoneAlertFireStationDTO findFireStationById(Long id) {
        FireStation fireStation = fireStationRepository.findFireStationById(id);
        if (fireStation == null) {
            LOGGER.error("Error occurred while trying find the provided id " + id + " : no matching id in database");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "the provided fire station id does not match any id in our database");
        }
        LOGGER.info("Phone numbers of the persons covered by fire station " + id +" retrieved from database");
        return fireStationConverter.phoneAlertStationToDTOConverter(fireStation);
    }
}
