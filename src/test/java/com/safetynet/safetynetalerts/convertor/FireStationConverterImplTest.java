package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.dtos.FireStationDTO;
import com.safetynet.safetynetalerts.dtos.firedto.FireStationFireDTO;
import com.safetynet.safetynetalerts.dtos.flooddto.FloodFireStationDTO;
import com.safetynet.safetynetalerts.dtos.phonealertdto.PhoneAlertFireStationDTO;
import com.safetynet.safetynetalerts.dtos.stationnumberdto.StationNumberFireStationDTO;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("converters")
class FireStationConverterImplTest {

    private FireStationConverterImpl fireStationConverter;

    private FireStation fireStation;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        fireStationConverter = new FireStationConverterImpl(modelMapper);

        fireStation = new FireStation();
        fireStation.setId(1L);
        fireStation.setStation(1);
        fireStation.setAddress("any address");
        List<Person> persons = new ArrayList<>();
        fireStation.setPersons(persons);

    }

    @AfterEach
    void tearDown() {
        modelMapper = null;
        fireStationConverter = null;
    }

    @DisplayName("Convert FireStation to FireStationDTO")
    @Test
    void givenAFireStationEntity_whenFireStationToDAOConverterIsCalled_thenFireStationShouldBeConvertedToDTO() {
        FireStationDTO fireStationDTO = new FireStationDTO();
        fireStationConverter.fireStationToDAOConverter(fireStation);
        modelMapper.map(fireStation, fireStationDTO);
        assertEquals(fireStationDTO.getAddress(), fireStation.getAddress());
        assertEquals(fireStationDTO.getStation(), fireStation.getStation());

    }

    @DisplayName("Convert a list of FireStations to a list of FireStationDTOs")
    @Test
    void givenAListOfFireStations_whenFireStationToDAOsConverterIsCalled_thenAListOfDTOsShouldBeReturned() {
        List<FireStation> fireStations = new ArrayList<>();
        fireStations.add(fireStation);
        fireStationConverter.fireStationToDAOsConverter(fireStations);
        List<FireStationDTO> fireStationDTOS = fireStations.stream().map(f -> fireStationConverter.fireStationToDAOConverter(f)).collect(Collectors.toList());

        assertEquals(fireStationDTOS.size(), fireStations.size());
    }

    @DisplayName("Convert FireStation to PhoneAlertFireStationDTO")
    @Test
    void givenFireStation_whenPhoneAlertStationToDTOConverterIsCalled_theFireStationShouldBeConverted() {
        PhoneAlertFireStationDTO phoneAlertFireStationDTO = new PhoneAlertFireStationDTO();
        fireStationConverter.phoneAlertStationToDTOConverter(fireStation);

        modelMapper.map(fireStation, phoneAlertFireStationDTO);
        assertEquals(phoneAlertFireStationDTO.getPersons(), fireStation.getPersons());
        assertEquals(phoneAlertFireStationDTO.getPersons().size(), fireStation.getPersons().size());
    }

    @DisplayName("Convert FireStation to StationNumberFireStationDTO")
    @Test
    void givenFireStationEntity_whenStationNumberFireStationDTOIsCalled_theFireStationShouldBeConverted() {
        StationNumberFireStationDTO stationNumberFireStationDTO = new StationNumberFireStationDTO();

        fireStationConverter.stationNumberFireStationDTO(fireStation);
        modelMapper.map(fireStation, stationNumberFireStationDTO);
        assertEquals(stationNumberFireStationDTO.getPersons(), fireStation.getPersons());
        assertEquals(stationNumberFireStationDTO.getPersons().size(), fireStation.getPersons().size());
    }

    @DisplayName("Convert FireStation to FloodFireStationDTO")
    @Test
    void givenFireStationEntity_whenFloodFireStationDAOConverterIsCalled_theFireStationShouldBeConverted() {
        FloodFireStationDTO floodFireStationDTO = new FloodFireStationDTO();
        fireStationConverter.floodFireStationDAOConverter(fireStation);
        modelMapper.map(fireStation, floodFireStationDTO);
        assertEquals(floodFireStationDTO.getId(), fireStation.getId());
        assertEquals(floodFireStationDTO.getAddress(), fireStation.getAddress());
        assertEquals(floodFireStationDTO.getPersons(), fireStation.getPersons());
        assertEquals(floodFireStationDTO.getPersons().size(), fireStation.getPersons().size());
    }

    @DisplayName("Convert a list of FireStations to a list of FloodFireStationDTOs")
    @Test
    void givenAListOfFireStation_whenFloodFireStationDAOsConverterIsCalled_theListShouldBeConverted() {
        List<FireStation> fireStations = new ArrayList<>();
        fireStations.add(fireStation);
        fireStationConverter.floodFireStationDAOsConverter(fireStations);
        List<FloodFireStationDTO> fireStationDTOS = fireStations.stream().map(f -> fireStationConverter.floodFireStationDAOConverter(f)).collect(Collectors.toList());
        assertEquals(fireStationDTOS.size(), fireStations.size());
    }

    @DisplayName("Convert FireStation to FireStationFireDTO")
    @Test
    void givenFireStation_whenFireStationToFireStationFireDTOConverterIsCalled_theFireStationShouldBeConverted() {
        FireStationFireDTO fireStationFireDTO = new FireStationFireDTO();
        fireStationConverter.fireStationToFireStationFireDTOConverter(fireStation);
        modelMapper.map(fireStation, fireStationFireDTO);
        assertEquals(fireStationFireDTO.getStation(), fireStation.getStation());
    }

    @DisplayName("Convert a list of FireStations to a list of FireStationFireDTOs")
    @Test
    void givenAListOfFireStations_whenFireStationsToFireStationFireDTOConvertersIsCalled_thenListShouldBeConverted() {
        List<FireStation> fireStations = new ArrayList<>();
        fireStations.add(fireStation);
        fireStationConverter.fireStationsToFireStationFireDTOConverters(fireStations);
        List<FireStationFireDTO> fireStationFireDTOS = fireStations.stream().map(f -> fireStationConverter.fireStationToFireStationFireDTOConverter(f)).collect(Collectors.toList());
        assertEquals(fireStationFireDTOS.size(), fireStations.size());

    }
}