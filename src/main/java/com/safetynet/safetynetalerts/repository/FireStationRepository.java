package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.domain.FireStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FireStationRepository extends JpaRepository<FireStation, Long> {

    @Query(value = "SELECT f FROM FireStation f where f.id in (:id) ")
    List<FireStation> findFireStationsById(List<Long> id);
}
