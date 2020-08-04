package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.domain.FireStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FireStationRepository extends JpaRepository<FireStation, Long> {

    @Query(value = "SELECT f FROM FireStation f where f.id in (:id) ")
    List<FireStation> findFireStationsById(List<Long> id);

    @Query(value ="select f from FireStation f inner join f.persons p where f.id = ?1")
    FireStation findFireStationById(Long id);
}
