package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dtos.FireStationDTO;
import com.safetynet.safetynetalerts.dtos.flooddto.FloodFireStationDTO;
import com.safetynet.safetynetalerts.dtos.stationnumberdto.StationNumberFireStationDTO;
import com.safetynet.safetynetalerts.dtos.phonealertdto.PhoneAlertFireStationDTO;
import com.safetynet.safetynetalerts.convertor.FireStationConverter;
import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.repository.FireStationRepository;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Yahia CHERIFI
 * This class impleents FireStationService interface
 * @see FireStationService
 */
@Service
@Transactional
@NoArgsConstructor
public class FireStationServiceImpl implements FireStationService {

    /**
     * FireStationRepository to be injected.
     */
    private FireStationRepository fireStationRepository;

    /**
     * FireStationConverter to be injected.
     */
    private FireStationConverter fireStationConverter;

    /**
     * FireStationServiceImpl logger.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(FireStationServiceImpl.class);

    /**
     * Constructor injection.
     * @param fireStationRepositoryInstance fireStationRepositoryInstance
     * @param fireStationConverterInstance fireStationConverterInstance
     */
    @Autowired
    public FireStationServiceImpl(
            final FireStationRepository fireStationRepositoryInstance,
            final FireStationConverter fireStationConverterInstance) {
        this.fireStationRepository = fireStationRepositoryInstance;
        this.fireStationConverter = fireStationConverterInstance;
    }

    /**
     * Calls the repository layers and.
     * saves a new fire station/updates an existing fire station
     * @param fireStation the fire station to be saved
     * @return the fire station
     */
    @Override
    public FireStation save(final FireStation fireStation) {
        return fireStationRepository.save(fireStation);
    }

    /**
     * Calls the repository layer.
     * @param id the fire station id
     * @return the found fire stations
     */
    @Override
    public Optional<FireStation> findById(final Long id) {
        return fireStationRepository.findById(id);
    }

    /**
     * Calls the repository layer.
     * deletes a fire station by its number
     * @param id the fire station id
     */
    @Override
    public void deleteById(final Long id) {
        fireStationRepository.deleteById(id);
    }

    /**
     * Calls the repository layer.
     * Retrieves all the fire station from db
     * @return a list of fire stations
     */
    @Override
    public List<FireStationDTO> findAll() {
        List<FireStation> fireStations = fireStationRepository.findAll();
        return fireStationConverter.fireStationToDAOsConverter(fireStations);
    }

    /**
     * Calls the repository layer.
     * @param id a list of fire station ids
     * @return a list of FloodFireStationDTO
     */
    @Override
    public List<FloodFireStationDTO> findFireStationsById(final List<Long> id) {
        List<FireStation> fireStations
                = fireStationRepository.findFireStationsById(id);
        return fireStationConverter.floodFireStationDAOsConverter(fireStations);
    }

    /**
     * Calls the repository layer.
     * @param id the fire station id
     * @return StationNumberFireStationDTO
     */
    @Override
    public StationNumberFireStationDTO getOneFireStationById(final Long id) {
        FireStation fireStation = fireStationRepository.getOne(id);
        return fireStationConverter.stationNumberFireStationDTO(fireStation);
    }

    /**
     * Calls the repository layer.
     * @param id the fire station id
     * @return PhoneAlertFireStationDTO
     * @throws ResponseStatusException if no fire station was found
     */
    @Override
    public PhoneAlertFireStationDTO findFireStationById(final Long id) {
        FireStation fireStation = fireStationRepository.findFireStationById(id);
        if (fireStation == null) {
            LOGGER.error("Error occurred while trying find the provided id: {}."
                    + " No matching id in the database", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "the provided fire station id"
                            + " does not match any id in our database");
        }
        LOGGER.info("Phone numbers of the persons covered"
                + " by fire station id {} retrieved from the database.", id);
        return fireStationConverter
                .phoneAlertStationToDTOConverter(fireStation);
    }
}
