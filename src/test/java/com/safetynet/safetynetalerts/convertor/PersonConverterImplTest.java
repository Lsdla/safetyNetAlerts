package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.domain.MedicalRecord;
import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.dtos.PersonDTO;
import com.safetynet.safetynetalerts.dtos.childDTO.ChildDTO;
import com.safetynet.safetynetalerts.dtos.communityEmailDto.CommunityEmailDTO;
import com.safetynet.safetynetalerts.dtos.fireDTO.PersonFireDTO;
import com.safetynet.safetynetalerts.dtos.floodDto.FloodPersonDTO;
import com.safetynet.safetynetalerts.dtos.personInfoDto.PersonInfoDTO;
import com.safetynet.safetynetalerts.dtos.phoneAlertDTO.PhoneAlertPersonDTO;
import com.safetynet.safetynetalerts.dtos.stationNumberDTO.StationNumberPersonDTO;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Tag("converters")
class PersonConverterImplTest {

    private PersonConverterImpl personConverter;

    private Person person;
    private List<Person> personList;

    @Mock
    private ModelMapper modelMapper;

    private MedicalRecord medicalRecord = new MedicalRecord();
    private List<FireStation> fireStations = new ArrayList<>();
    private List<String> medications = new ArrayList<>();
    private List<String> allergies = new ArrayList<>();

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        personConverter = new PersonConverterImpl(modelMapper);
        personList = new ArrayList<>();
        person = new Person();
        person.setId(1L);
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("any address");
        person.setCity("city");
        person.setZip("1234");
        person.setEmail("email@mail");
        person.setPhone("123456789");
        //medical record
        medicalRecord.setAge(19.9);
        medicalRecord.setAllergies(allergies);
        medicalRecord.setMedications(medications);
        person.setMedicalRecord(medicalRecord);
        person.setFireStations(fireStations);
        personList.add(person);

    }

    @AfterEach
    void tearDown() {
        modelMapper = null;
        person = null;
        personList = null;
    }

    @DisplayName("Convert Person to PersonDTO")
    @Test
    void givenPersonEntity_whenPersonToDAOConverterIsCalled_thenPersonShouldBeConverted() {
        PersonDTO personDTO = new PersonDTO();
        personConverter.personToDAOConverter(person);
        modelMapper.map(person, personDTO);
        assertEquals(personDTO.getFirstName(), person.getFirstName());
        assertEquals(personDTO.getLastName(), person.getLastName());
        assertEquals(personDTO.getAddress(), person.getAddress());
        assertEquals(personDTO.getCity(), person.getCity());
        assertEquals(personDTO.getZip(), person.getZip());
        assertEquals(personDTO.getPhone(), person.getPhone());
        assertEquals(personDTO.getEmail(), person.getEmail());
        assertEquals(personDTO.getMedicalRecord().getAge(), person.getMedicalRecord().getAge());
        assertEquals(personDTO.getMedicalRecord().getMedications(), person.getMedicalRecord().getMedications());
        assertEquals(personDTO.getMedicalRecord().getAllergies(), person.getMedicalRecord().getAllergies());
    }

    @DisplayName("Convert a list of persons to a list of personDTOs")
    @Test
    void givenAListOfPersons_whenPersonToDAOsConverterIsCalled_thenAListOfDTOsShouldBeReturned() {
        personConverter.personToDAOsConverter(personList);
        List<PersonDTO> personDTOs = personList.stream().map(p -> personConverter.personToDAOConverter(p)).collect(Collectors.toList());
        assertEquals(personDTOs.size(), personList.size());
    }

    @DisplayName("Convert Person to CommunityEmailDTO")
    @Test
    void givenAPersonEntity_whenPersonToEmailDTOConverterIsCalled_thenPersonShouldBeConverted() {
        CommunityEmailDTO communityEmailDTO = new CommunityEmailDTO();
        personConverter.personToEmailDTOConverter(person);
        modelMapper.map(person, communityEmailDTO);
        assertEquals(communityEmailDTO.getEmail(), person.getEmail());
    }

    @DisplayName("Convert a list of persons to CommunityEmailDTOs")
    @Test
    void givenAListOfPersons_whenPersonsToEmailDTOsConverterIsCalled_thenPersonsShouldBeConvertedToEmailDTOs() {
        personConverter.personsToEmailDTOsConverter(personList);
        List<CommunityEmailDTO> communityEmailDTOS = personList.stream().map(p -> personConverter.personToEmailDTOConverter(p)).collect(Collectors.toList());
        assertEquals(personList.size(), communityEmailDTOS.size());
    }

    @DisplayName("Convert Person to PersonInfoDTO")
    @Test
    void givenAPersonEntity_whenPersonToPersonInfoDOAConverterIsCalled_thenPersonShouldBeConvertedToPersonInfoDOA() {
        PersonInfoDTO personInfoDTO = new PersonInfoDTO();
        personConverter.personToPersonInfoDTOConverter(person);
        modelMapper.map(person, personInfoDTO);
        assertEquals(personInfoDTO.getFirstName(), person.getFirstName());
        assertEquals(personInfoDTO.getLastName(), person.getLastName());
        assertEquals(personInfoDTO.getAddress(), person.getAddress());
        assertEquals(personInfoDTO.getEmail(), person.getEmail());
        assertEquals(personInfoDTO.getMedicalRecord().getAge(), person.getMedicalRecord().getAge());
        assertEquals(personInfoDTO.getMedicalRecord().getAllergies(), person.getMedicalRecord().getAllergies());
        assertEquals(personInfoDTO.getMedicalRecord().getMedications(), person.getMedicalRecord().getMedications());

    }

    @DisplayName("Convert a list of persons to a list of PersonInfoDTOs")
    @Test
    void givenAListPersons_whenPersonsToPersonInfoDOAsConverterIsCalled_thenPersonShouldBeConvertedToPersonInfoDOAs() {
        personConverter.personsToPersonInfoDTOsConverter(personList);
        List<PersonInfoDTO> personInfoDTOs = personList.stream().map(p -> personConverter.personToPersonInfoDTOConverter(p)).collect(Collectors.toList());
        assertEquals(personList.size(), personInfoDTOs.size());
    }

    @DisplayName("Convert a person to PhoneAlertDTO")
    @Test
    void givenAPersonEntity_whenPersonToPhoneInfoDTOIsCalled_thenPersonShouldBeConvertedToPhoneAlertDTO() {
        PhoneAlertPersonDTO phoneAlertPersonDTO = new PhoneAlertPersonDTO();
        personConverter.personToPhoneInfoDTO(person);
        modelMapper.map(person, phoneAlertPersonDTO);
        assertEquals(phoneAlertPersonDTO.getFirstName(), person.getFirstName());
        assertEquals(phoneAlertPersonDTO.getLastName(), person.getLastName());
        assertEquals(phoneAlertPersonDTO.getPhone(), person.getPhone());
    }

    @DisplayName("Convert a list of persons to a list of PhoneAlertDTOs")
    @Test
    void givenAListOfPersons_whenPersonToPhoneInfoDTOsIsCalled_thenPersonsShouldBeConvertedToPhoneAlertDTOs() {
        personConverter.personToPhoneInfosDTO(personList);
        List<PhoneAlertPersonDTO> poneAlertPersonDTO = personList.stream().map(p -> personConverter.personToPhoneInfoDTO(p)).collect(Collectors.toList());
        assertEquals(personList.size(), poneAlertPersonDTO.size());
    }

    @DisplayName("Convert person to PersonFireDTO")
    @Test
    void givenAPersonEntity_whenPersonToPersonFireDTOConverterIsCalled_thenPersonShouldBeConvertedToPersonFireDTO() {
        PersonFireDTO personFireDTO = new PersonFireDTO();
        personConverter.personToPersonFireDTOConverter(person);
        modelMapper.map(person, personFireDTO);
        assertEquals(personFireDTO.getFirstName(), person.getFirstName());
        assertEquals(personFireDTO.getLastName(), person.getLastName());
        assertEquals(personFireDTO.getPhone(), person.getPhone());
        assertEquals(personFireDTO.getFireStations(), person.getFireStations());
        assertEquals(personFireDTO.getMedicalRecord().getAge(), person.getMedicalRecord().getAge());
        assertEquals(personFireDTO.getMedicalRecord().getMedications(), person.getMedicalRecord().getMedications());
        assertEquals(personFireDTO.getMedicalRecord().getAllergies(), person.getMedicalRecord().getAllergies());
    }

    @DisplayName("Convert a list of persons to a list PersonFireDTOs")
    @Test
    void givenAListOfPersons_whenPersonToPersonFireDTOsConverterIsCalled_thenPersonsShouldBeConvertedToPersonFireDTOs() {
        personConverter.personsToPersonFireDTOsConverter(personList);
        List<PersonFireDTO> personFireDTOS = personList.stream().map(p -> personConverter.personToPersonFireDTOConverter(p)).collect(Collectors.toList());
        assertEquals(personList.size(), personFireDTOS.size());
    }

    //
    @DisplayName("Convert person to FloodPersonDTO")
    @Test
    void givenAPersonEntity_whenPersonToFloodPersonDTOConverterIsCalled_thenPersonShouldBeConvertedToFloodPersonDTO() {
        FloodPersonDTO floodPersonDTO = new FloodPersonDTO();
        personConverter.personToFloodPersonDTOConverter(person);
        modelMapper.map(person, floodPersonDTO);
        assertEquals(floodPersonDTO.getFirstName(), person.getFirstName());
        assertEquals(floodPersonDTO.getLastName(), person.getLastName());
        assertEquals(floodPersonDTO.getPhone(), person.getPhone());
        assertEquals(floodPersonDTO.getMedicalRecord().getAge(), person.getMedicalRecord().getAge());
        assertEquals(floodPersonDTO.getMedicalRecord().getAllergies(), person.getMedicalRecord().getAllergies());
        assertEquals(floodPersonDTO.getMedicalRecord().getMedications(), person.getMedicalRecord().getMedications());
    }

    @DisplayName("Convert a list of persons to a list FloodPersonDTOs")
    @Test
    void givenAListOfPersons_whenPersonToFloodPersonDTOsConverterIsCalled_thenPersonShouldBeConvertedToFloodPersonDTOs() {
        personConverter.personsToFloodPersonDTOsConverter(personList);
        List<FloodPersonDTO> floodPersonDTOS = personList.stream().map(p -> personConverter.personToFloodPersonDTOConverter(p)).collect(Collectors.toList());
        assertEquals(personList.size(), floodPersonDTOS.size());
    }

    @DisplayName("Convert person to ChildDTO")
    @Test
    void givenAPersonEntity_whenPersonToChildDTOConverterIsCalled_thenPersonShouldBeConvertedToChildDTO() {
        ChildDTO childDTO = new ChildDTO();
        personConverter.personToChildDTOConverter(person);
        modelMapper.map(person, childDTO);
        modelMapper.map(person, childDTO);
        assertEquals(childDTO.getFirstName(), person.getFirstName());
        assertEquals(childDTO.getLastName(), person.getLastName());
        assertEquals(childDTO.getAge(), person.getAge());
    }

    @DisplayName("Convert a list of persons to a list ChildDTOs")
    @Test
    void givenAListOfPersons_whenPersonToChildDTOsConverterIsCalled_thenPersonsShouldBeConvertedToChildDTOs() {
        personConverter.personsToChildDTOsConverter(personList);
        List<ChildDTO> childDTOS = personList.stream().map(p -> personConverter.personToChildDTOConverter(p)).collect(Collectors.toList());
        assertEquals(personList.size(), childDTOS.size());
    }

    @DisplayName("Convert a list of persons' list to a list ChildDTOs' list")
    @Test
    void givenAListOfPersonList_whenChildrenListToListConverterIsCalled_thenTheListShouldBeConvertedToListOfChildDTOs() {
        List<List<Person>> persons = new ArrayList<>();
        persons.add(personList);
        personConverter.childrenListToListConverter(persons);
        List<List<ChildDTO>> childrenList = persons.stream().map(p -> personConverter.personsToChildDTOsConverter(p)).collect(Collectors.toList());
        assertEquals(persons.size(), childrenList.size());
    }

    @DisplayName("Convert person to StationNumberPersonDTO")
    @Test
    void givenAPersonEntity_whenPersonToStationNumberPersonDTOConverterIsCalled_thenPersonShouldBeConvertedToStationNumberPersonDTO() {
        StationNumberPersonDTO stationNumberPersonDTO = new StationNumberPersonDTO();
        personConverter.personToStationNumberPersonDTOConverter(person);
        modelMapper.map(person, stationNumberPersonDTO);
        assertEquals(stationNumberPersonDTO.getFirstName(), person.getFirstName());
        assertEquals(stationNumberPersonDTO.getLastName(), person.getLastName());
        assertEquals(stationNumberPersonDTO.getAddress(), person.getAddress());
        assertEquals(stationNumberPersonDTO.getAge(), person.getAge());
        assertEquals(stationNumberPersonDTO.getPhone(), person.getPhone());
    }

    @DisplayName("Convert a list of persons to a list StationNumberPersonDTOs")
    @Test
    void givenAListOfPersons_whenPersonToStationNumberPersonDTOsConverterIsCalled_thenPersonsShouldBeConvertedToStationNumberPersonDTOs() {
        personConverter.personsToStationNumberPersonDTOs(personList);
        List<StationNumberPersonDTO> stationNumberPersonDTOS = personList.stream().map(p -> personConverter.personToStationNumberPersonDTOConverter(p)).collect(Collectors.toList());
        assertEquals(personList.size(), stationNumberPersonDTOS.size());
    }
}