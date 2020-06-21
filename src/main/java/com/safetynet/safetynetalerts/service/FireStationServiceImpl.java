package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FireStationServiceImpl implements FireStationService {

    //inject FireStationRepository
    private FireStationRepository fireStationRepository;

    @Autowired
    public FireStationServiceImpl(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }

    //save a new fire station/update an existing fire station
    @Override
    public void save(FireStation fireStation) {
        fireStationRepository.save(fireStation);
    }

    //find a fire station by its number
    @Override
    public FireStation findByStationNumber(String station) {
        return fireStationRepository.findByStationNumber(station);
    }

    //delete a fire station by its number
    @Override
    public void deleteByStationNumber(String station) {
        fireStationRepository.deleteByStationNumber(station);
    }
}
