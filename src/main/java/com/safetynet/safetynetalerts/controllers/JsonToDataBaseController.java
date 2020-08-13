package com.safetynet.safetynetalerts.controllers;


import com.safetynet.safetynetalerts.ReadDataFromJson;
import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.domain.MedicalRecord;
import com.safetynet.safetynetalerts.domain.Person;
import com.safetynet.safetynetalerts.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author Yahia CHERIFI
 * This controller allows inserting data retrieved from a json file
 * to database
 */
@RestController
@RequestMapping("/jsonToDatabase")
public class JsonToDataBaseController {

    /**
     * fireStationService to inject.
     */
    private final FireStationService fireStationService;

    /**
     * Class logger.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(JsonToDataBaseController.class);

    /**
     * Constructor injection.
     * @param service fireStationService
     */
    @Autowired
    public JsonToDataBaseController(final FireStationService service) {
        this.fireStationService = service;
    }

    /**
     * insert data from a json file.
     */
    @PostMapping("/insert")
    public void jsonToDatabase() {
        LOGGER.debug("Post method sent from JsonToDatabaseController");
        try {
            //new instance of the ReadDataFromJson
            ReadDataFromJson readDataFromJson =  new ReadDataFromJson();
            //store fire stations retrieved from the json file
            List<FireStation> fireStations = readDataFromJson
                    .getFireStationsFromJsonFile();
            //store persons retrieved from the json file
            List<Person> persons = readDataFromJson
                    .getPersonsFromJsonFile();
            //store fire medical records retrieved from the json file
            List<MedicalRecord> medicalRecords = readDataFromJson
                    .getMedicalRecordsFromJsonFile();
            //link the different data and save them to database
            //loop through firestations
            for (FireStation f:fireStations) {
                //loop through persons
                for (Person p:persons) {
                    if (p.getAddress().equalsIgnoreCase(f.getAddress())) {
                        //loop through medical records
                        for (MedicalRecord m: medicalRecords) {
                            if (m.getFirstName().equalsIgnoreCase(
                                    p.getFirstName()) && m.getLastName()
                                    .equalsIgnoreCase(p.getLastName())) {
                                p.setMedicalRecord(m);
                                f.addPerson(p);
                            }
                        }
                        fireStationService.save(f);
                    }
                }
            }
            LOGGER.info("Data inserted successfully to database");
        } catch (ParseException | IOException e) {
            LOGGER.error("Something happened during data insertion");
        }
    }
}
