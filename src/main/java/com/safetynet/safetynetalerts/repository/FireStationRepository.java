package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.domain.FireStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Yahia CHERIFI
 * exposes basic CRUD operations on fireStation
 * @see Query
 * @see JpaRepository
 */
public interface FireStationRepository
        extends JpaRepository<FireStation, Long> {

    /**
     * Retrieve a list of fire station by their ids.
     * @param id a list of ids.
     * @return a list of fire stations
     */
    @Query(value = "SELECT f FROM FireStation f where f.id in (:id) ")
    List<FireStation> findFireStationsById(List<Long> id);

    /**
     * Retrieves The fire station and some information related
     * to the persons covered by a given fire station.
     * @param id the fire station's id
     * @return the fire station and the desired person's information
     */
    @Query(value = "select f from FireStation f inner join f.persons p"
            + " where f.id = ?1")
    FireStation findFireStationById(Long id);
}
