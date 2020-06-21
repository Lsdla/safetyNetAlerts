package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.domain.FireStation;

public interface FireStationService {

    void save(FireStation fireStation);

    FireStation findByStationNumber(String station);

    void deleteByStationNumber(String station);
}
