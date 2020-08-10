package com.safetynet.safetynetalerts.convertor;

import com.safetynet.safetynetalerts.dtos.PersonDTO;
import com.safetynet.safetynetalerts.dtos.childdto.ChildDTO;
import com.safetynet.safetynetalerts.dtos.communityemaildto.CommunityEmailDTO;
import com.safetynet.safetynetalerts.dtos.firedto.PersonFireDTO;
import com.safetynet.safetynetalerts.dtos.flooddto.FloodPersonDTO;
import com.safetynet.safetynetalerts.dtos.personinfodto.PersonInfoDTO;
import com.safetynet.safetynetalerts.dtos.phonealertdto.PhoneAlertPersonDTO;
import com.safetynet.safetynetalerts.dtos.stationnumberdto.StationNumberPersonDTO;
import com.safetynet.safetynetalerts.domain.Person;

import java.util.List;

/**
 * @author Yahia CHERIFI
 * MedicalRecordConverter interface
 * provides method that allow conversion from
 * a person entity to DTO
 */
public interface PersonConverter {

    /**
     * convert from Person entity to PersonDTO.
     * @param person entity
     * @return PersonDTO
     */
    PersonDTO personToDAOConverter(Person person);

    /**
     * convert a list of Person entities to to a list of PersonDTOs.
     * @param personList a list of person entities
     * @return a list of PersonDTOs.
     */
    List<PersonDTO> personToDAOsConverter(List<Person> personList);

    /**
     * convert from Person entity to CommunityEmailDTO.
     * @param person entity
     * @return CommunityEmailDTO
     */
    CommunityEmailDTO personToEmailDTOConverter(Person person);

    /**
     * convert a list of person entities to a list of CommunityEmailDTOs.
     * @param personList a list of person entities
     * @return a list of CommunityEmailDTOs
     */
    List<CommunityEmailDTO> personsToEmailDTOsConverter(
            List<Person> personList);

    /**
     * convert a person entity to PersonInfoDTO.
     * @param person entity
     * @return PersonInfoDTO
     */
    PersonInfoDTO personToPersonInfoDTOConverter(Person person);

    /**
     * convert a list of person entities into a list of PersonInfoDTOs.
     * @param personList a list of person entities
     * @return a list of PersonInfoDTOs
     */
    List<PersonInfoDTO> personsToPersonInfoDTOsConverter(
            List<Person> personList);

    /**
     * convert a person entity to PhoneAlertPersonDTO.
     * @param person entity
     * @return PhoneAlertPersonDTO
     */
    PhoneAlertPersonDTO personToPhoneInfoDTO(Person person);

    /**
     * convert a list of person entities into a list of PhoneAlertPersonDTOs.
     * @param personList a list of person entities
     * @return a list of PhoneAlertPersonDTOs
     */
    List<PhoneAlertPersonDTO> personToPhoneInfosDTO(List<Person> personList);

    /**
     * convert from person entity to PersonFireDTO.
     * @param person entity
     * @return PersonFireDTO
     */
    PersonFireDTO personToPersonFireDTOConverter(Person person);

    /**
     * convert a list of person entities into a list of PersonFireDTOs.
     * @param personList a list of person entities
     * @return a list of PersonFireDTOs
     */
    List<PersonFireDTO> personsToPersonFireDTOsConverter(
            List<Person> personList);

    /**
     * convert a person entity to FloodPersonDTO.
     * @param person entity
     * @return FloodPersonDTO
     */
    FloodPersonDTO personToFloodPersonDTOConverter(Person person);

    /**
     * convert a list of person entities into FloodPersonDTOs.
     * @param personList a list of person entities
     * @return a list of FloodPersonDTOs.
     */
    List<FloodPersonDTO> personsToFloodPersonDTOsConverter(
            List<Person> personList);

    /**
     * convert a person entity to ChildDTO.
     * @param person entity
     * @return ChildDTO
     */
    ChildDTO personToChildDTOConverter(Person person);

    /**
     * convert a list of person entities into ChildDTOs.
     * @param personList a list of person entities
     * @return a list of ChildDTOs
     */
    List<ChildDTO> personsToChildDTOsConverter(List<Person> personList);

    /**
     * convert a list of person entities' list into a list of ChildDTOs list.
     * @param persons a list of persons' lists
     * @return a list of ChildDTOs' list
     */
    List<List<ChildDTO>> childrenListToListConverter(
            List<List<Person>> persons);

    /**
     * convert a person entity to StationNumberPersonDTO.
     * @param person entity
     * @return StationNumberPersonDTO
     */
    StationNumberPersonDTO personToStationNumberPersonDTOConverter(
            Person person);

    /**
     * convert a list of person entities into a list of StationNumberPersonDTOs.
     * @param personList a person entities list
     * @return a list of StationNumberPersonDTOs
     */
    List<StationNumberPersonDTO> personsToStationNumberPersonDTOs(
            List<Person> personList);
}
