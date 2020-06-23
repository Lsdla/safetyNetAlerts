package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.domain.FireStation;

import java.util.List;

public interface FireStationService {

    void save(FireStation fireStation);

    FireStation findByStation(String station);

    void deleteByStation(String station);

    List<FireStation> findAll();
}
