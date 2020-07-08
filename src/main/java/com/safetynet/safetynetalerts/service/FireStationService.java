package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.domain.FireStation;

import java.util.List;
import java.util.Optional;

public interface FireStationService {

    void save(FireStation fireStation);

    Optional<FireStation> findById(Long id);

    void deleteById(Long id);

    List<FireStation> findAll();
}
