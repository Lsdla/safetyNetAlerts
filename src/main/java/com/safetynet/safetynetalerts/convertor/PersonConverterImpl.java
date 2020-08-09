package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.dtos.PersonDTO;
import com.safetynet.safetynetalerts.dtos.childDTO.ChildDTO;
import com.safetynet.safetynetalerts.dtos.communityEmailDto.CommunityEmailDTO;
import com.safetynet.safetynetalerts.dtos.fireDTO.PersonFireDTO;
import com.safetynet.safetynetalerts.dtos.floodDto.FloodPersonDTO;
import com.safetynet.safetynetalerts.dtos.personInfoDto.PersonInfoDTO;
import com.safetynet.safetynetalerts.dtos.phoneAlertDTO.PhoneAlertPersonDTO;
import com.safetynet.safetynetalerts.dtos.stationNumberDTO.StationNumberPersonDTO;
import com.safetynet.safetynetalerts.domain.Person;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yahia CHERIFI
 * PersonConverter implementation
 * @see PersonConverter
 */
@Component
public class PersonConverterImpl implements PersonConverter {

    /**
     * ModelMapper to be injected.
     * used to map objects to each other
     */
    private ModelMapper mapper;

    /**
     * Constructor injection.
     * @param modelMapper model mapper
     */
    @Autowired
    public PersonConverterImpl(final ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    /**
     * @see PersonConverter
     * @param person entity
     * @return PersonDTO
     */
    @Override
    public PersonDTO personToDAOConverter(final Person person) {
        return mapper.map(person, PersonDTO.class);
    }

    /**
     * @see PersonConverter
     * @param personList a list of person entities
     * @return a list of PersonDTOs
     */
    @Override
    public List<PersonDTO> personToDAOsConverter(
            final List<Person> personList) {
        return personList.stream()
                .map(this::personToDAOConverter).collect(Collectors.toList());
    }

    /**
     * @see PersonConverter
     * @param person entity
     * @return CommunityEmailDTO
     */
    @Override
    public CommunityEmailDTO personToEmailDTOConverter(final Person person) {
        return mapper.map(person, CommunityEmailDTO.class);
    }

    /**
     * @see PersonConverter
     * @param personList a list of person entities
     * @return a list of CommunityEmailDTOs
     */
    @Override
    public List<CommunityEmailDTO> personsToEmailDTOsConverter(
            final List<Person> personList) {
        return personList.stream()
                .map(this::personToEmailDTOConverter)
                .collect(Collectors.toList());
    }

    /**
     * @see PersonConverter
     * @param person entity
     * @return PersonInfoDTO
     */
    @Override
    public PersonInfoDTO personToPersonInfoDTOConverter(final Person person) {
        return mapper.map(person, PersonInfoDTO.class);
    }

    /**
     * @see PersonConverter
     * @param personList a list of person entities
     * @return a list of PersonInfoDTOs
     */
    @Override
    public List<PersonInfoDTO> personsToPersonInfoDTOsConverter(
            final List<Person> personList) {
        return personList.stream()
                .map(this::personToPersonInfoDTOConverter)
                .collect(Collectors.toList());
    }

    /**
     * @see PersonConverter
     * @param person entity
     * @return PhoneAlertPersonDTO
     */
    @Override
    public PhoneAlertPersonDTO personToPhoneInfoDTO(final Person person) {
        return mapper.map(person, PhoneAlertPersonDTO.class);
    }

    /**
     * @see PersonConverter
     * @param personList a list of person entities
     * @return a ist of PhoneAlertPersonDTOs
     */
    @Override
    public List<PhoneAlertPersonDTO> personToPhoneInfosDTO(
            final List<Person> personList) {
        return personList.stream()
                .map(this::personToPhoneInfoDTO)
                .collect(Collectors.toList());
    }

    /**
     * @see PersonConverter
     * @param person entity
     * @return PersonFireDTO
     */
    @Override
    public PersonFireDTO personToPersonFireDTOConverter(final Person person) {
        return mapper.map(person, PersonFireDTO.class);
    }

    /**
     * @see PersonConverter
     * @param personList a list of person entities
     * @return a list of PersonFireDTOs
     */
    @Override
    public List<PersonFireDTO> personsToPersonFireDTOsConverter(
            final List<Person> personList) {
        return personList.stream()
                .map(this::personToPersonFireDTOConverter)
                .collect(Collectors.toList());
    }

    /**
     * @see PersonConverter
     * @param person entity
     * @return FloodPersonDTO
     */
    @Override
    public FloodPersonDTO personToFloodPersonDTOConverter(final Person person) {
        return mapper.map(person, FloodPersonDTO.class);
    }

    /**
     * @see PersonConverter
     * @param personList a list of person entities
     * @return a list of FloodPersonDTOs
     */
    @Override
    public List<FloodPersonDTO> personsToFloodPersonDTOsConverter(
            final List<Person> personList) {
        return personList.stream()
                .map(this::personToFloodPersonDTOConverter)
                .collect(Collectors.toList());
    }

    /**
     * @see PersonConverter
     * @param person entity
     * @return ChildDTO
     */
    @Override
    public ChildDTO personToChildDTOConverter(final Person person) {
        return mapper.map(person, ChildDTO.class);
    }

    /**
     * @see PersonConverter
     * @param personList a list of person entities
     * @return a list of ChildDTOs
     */
    @Override
    public List<ChildDTO> personsToChildDTOsConverter(
            final List<Person> personList) {
        return personList.stream()
                .map(this::personToChildDTOConverter)
                .collect(Collectors.toList());
    }

    /**
     * @see PersonConverter
     * @param persons a list of persons' lists
     * @return a list of ChildDTOs list
     */
    @Override
    public List<List<ChildDTO>> childrenListToListConverter(
            final List<List<Person>> persons) {
        return persons.stream()
                .map(this::personsToChildDTOsConverter)
                .collect(Collectors.toList());
    }

    /**
     * @see PersonConverter
     * @param person entity
     * @return StationNumberPersonDTO
     */
    @Override
    public StationNumberPersonDTO personToStationNumberPersonDTOConverter(
            final Person person) {
        return mapper.map(person, StationNumberPersonDTO.class);
    }

    /**
     * @see PersonConverter
     * @param personList a person entities list
     * @return a list of StationNumberPersonDTOs
     */
    @Override
    public List<StationNumberPersonDTO> personsToStationNumberPersonDTOs(
            final List<Person> personList) {
        return personList.stream()
                .map(this::personToStationNumberPersonDTOConverter)
                .collect(Collectors.toList());
    }
}
