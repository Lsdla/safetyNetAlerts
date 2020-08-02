package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.convertor.FireStationConverter;
import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.repository.FireStationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FireStationServiceImplTest {

    private FireStationServiceImpl fireStationServiceImpl;

    @Mock
    private FireStationRepository fireStationRepository;

    @Mock
    private FireStationConverter fireStationConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        fireStationServiceImpl = new FireStationServiceImpl(fireStationRepository, fireStationConverter);
    }

    @AfterEach
    void tearDown() {
        fireStationServiceImpl = null;
    }

    @Test
    void save_shouldCallAppropriateMethodInFireStationRepository() {
        FireStation fireStation = new FireStation();

        when(fireStationRepository.save(fireStation)).thenReturn(fireStation);

        fireStationServiceImpl.save(fireStation);

        verify(fireStationRepository, times(1)).save(fireStation);
    }

    @Test
    void findById_shouldCallAppropriateMethodInFireStationRepository() {
        fireStationServiceImpl.findById(anyLong());

        verify(fireStationRepository, times(1)).findById(anyLong());
    }

    @Test
    void deleteById_shouldCallAppropriateMethodInFireStationRepository() {
        fireStationServiceImpl.deleteById(anyLong());

        verify(fireStationRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void findAll_shouldCallAppropriateMethod_andReturnAllFireStations() {
        List<FireStation> fireStations = new ArrayList<>();
        when(fireStationRepository.findAll()).thenReturn(fireStations);

        fireStationServiceImpl.findAll();

        verify(fireStationRepository, times(1)).findAll();
    }

    @Test
    void findFireStationsById_shouldCallTheAppropriateMethodInFireStationRepository() {
        List<FireStation> fireStations = new ArrayList<>();

        when(fireStationRepository.findFireStationsById(anyList())).thenReturn(fireStations);

        fireStationServiceImpl.findFireStationsById(anyList());

        verify(fireStationRepository, times(1)).findFireStationsById(anyList());
        assertEquals(0, fireStationServiceImpl.findFireStationsById(anyList()).size());
    }

    @Test
    void urlStationDTO_shouldCallTheAppropriateMethodInFireStationRepository() {
        FireStation fireStation = new FireStation();

        when(fireStationRepository.getOne(anyLong())).thenReturn(fireStation);
        fireStationServiceImpl.urlStationDTO(anyLong());

        verify(fireStationRepository, times(1)).getOne(anyLong());
    }
}