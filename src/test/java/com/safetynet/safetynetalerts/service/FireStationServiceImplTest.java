package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.convertor.FireStationConverter;
import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.repository.FireStationRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@Tag("Service")
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

    @DisplayName("Save calls fireStationRepository.save")
    @Test
    void save_shouldCallAppropriateMethodInFireStationRepository() {
        FireStation fireStation = new FireStation();

        when(fireStationRepository.save(fireStation)).thenReturn(fireStation);

        fireStationServiceImpl.save(fireStation);

        verify(fireStationRepository, times(1)).save(fireStation);
    }

    @DisplayName("findById calls fireStationRepository.findById")
    @Test
    void findById_shouldCallAppropriateMethodInFireStationRepository() {
        fireStationServiceImpl.findById(anyLong());

        verify(fireStationRepository, times(1)).findById(anyLong());
    }

    @DisplayName("deleteById calls fireStationRepository.deleteById")
    @Test
    void deleteById_shouldCallAppropriateMethodInFireStationRepository() {
        fireStationServiceImpl.deleteById(anyLong());

        verify(fireStationRepository, times(1)).deleteById(anyLong());
    }

    @DisplayName("findAll calls fireStationRepository.findAll")
    @Test
    void findAll_shouldCallAppropriateMethod_andReturnAllFireStations() {
        List<FireStation> fireStations = new ArrayList<>();
        when(fireStationRepository.findAll()).thenReturn(fireStations);

        fireStationServiceImpl.findAll();

        verify(fireStationRepository, times(1)).findAll();
    }

    @DisplayName("findFireStationsById accepts a list of ids")
    @Test
    void findFireStationsById_shouldCallTheAppropriateMethodInFireStationRepository() {
        List<FireStation> fireStations = new ArrayList<>();

        when(fireStationRepository.findFireStationsById(anyList())).thenReturn(fireStations);

        fireStationServiceImpl.findFireStationsById(anyList());

        verify(fireStationRepository, times(1)).findFireStationsById(anyList());
        assertEquals(0, fireStationServiceImpl.findFireStationsById(anyList()).size());
    }

    @DisplayName("getOneFireStationById calls fireStationRepository.getOne")
    @Test
    void getOneFireStationById_shouldCallTheAppropriateMethodInFireStationRepository() {
        FireStation fireStation = new FireStation();

        when(fireStationRepository.getOne(anyLong())).thenReturn(fireStation);
        fireStationServiceImpl.getOneFireStationById(anyLong());

        verify(fireStationRepository, times(1)).getOne(anyLong());
    }

    @DisplayName("findFireStationById calls fireStationRepository.findFireStationById")
    @Test
    void findFireStationById_shouldCallTheAppropriateMethodIFireStationRepository() {
        FireStation fireStation = new FireStation();

        when(fireStationRepository.findFireStationById(anyLong())).thenReturn(fireStation);

        fireStationServiceImpl.findFireStationById(anyLong());
        verify(fireStationRepository, times(1)).findFireStationById(anyLong());
    }

    @DisplayName("findFireStationById throws exception when id is null")
    @Test
    void findFireStationById_shouldThrowExceptionWhenFireStationIsNull() {
        assertThrows(ResponseStatusException.class, () -> fireStationServiceImpl.findFireStationById(null));
    }
}