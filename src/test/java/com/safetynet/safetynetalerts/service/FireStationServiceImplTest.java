package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.repository.FireStationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FireStationServiceImplTest {

    FireStationServiceImpl fireStationServiceImpl;

    @Mock
    FireStationRepository fireStationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        fireStationServiceImpl = new FireStationServiceImpl(fireStationRepository);
    }

    @AfterEach
    void tearDown() {
        fireStationServiceImpl = null;
    }

    @Test
    void save() {
        FireStation fireStation = new FireStation();

        when(fireStationRepository.save(fireStation)).thenReturn(fireStation);

        fireStationServiceImpl.save(fireStation);

        verify(fireStationRepository, times(1)).save(fireStation);
    }

    @Test
    void findById() {
        fireStationServiceImpl.findById(anyLong());

        verify(fireStationRepository, times(1)).findById(anyLong());
    }

    @Test
    void deleteById() {
        fireStationServiceImpl.deleteById(anyLong());

        verify(fireStationRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void findAll() {
        FireStation fireStation = new FireStation();
        List<FireStation> fireStations = new ArrayList<>();

        fireStations.add(fireStation);

        when(fireStationRepository.findAll()).thenReturn(fireStations);

        fireStationServiceImpl.findAll();

        verify(fireStationRepository, times(1)).findAll();
        assertEquals(fireStations.size(), 1);

    }
}