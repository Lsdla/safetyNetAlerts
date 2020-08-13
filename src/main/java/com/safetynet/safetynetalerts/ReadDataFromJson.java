package com.safetynet.safetynetalerts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.domain.FireStation;
import com.safetynet.safetynetalerts.domain.MedicalRecord;
import com.safetynet.safetynetalerts.domain.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Yahia CHERIFI
 * This class reads data from a json file
 * It stores the read data in suitable lists
 */
public class ReadDataFromJson {

    /**
     * The path of the json file.
     */
    private static final String FILE_PATH
            = "./src/main/resources/json/data.json";

    /**
     * jackson object mapper.
     */
    private ObjectMapper mapper = null;

    /**
     * used to store retrieved fire stations.
     */
    private final List<FireStation> stations = new ArrayList<>();

    /**
     * used to store retrieve persons.
     */
    private final List<Person> personList = new ArrayList<>();

    /**
     * used to store the retrieved medical records.
     */
    private final List<MedicalRecord> medicalRecordList = new ArrayList<>();

    /**
     * the JSONParser parses json.
     */
    private JSONParser jsonParser = null;

    /**
     * Object.
     */
    private Object object = null;

    /**
     * Json object: used to store objects(key-value pairs).
     */
    private JSONObject jsonObject = null;

    /**
     * ListIterator.
     */
    private ListIterator iterator;

    /**
     * Retrieve persons from json file.
     * @return a list of persons
     * @throws IOException if problems occurred during file reading
     * @throws ParseException if problems occurred during parsing the json file
     */
    public List<Person> getPersonsFromJsonFile()
            throws IOException, ParseException {
        mapper = new ObjectMapper();
        jsonParser = new JSONParser();
        object = jsonParser.parse(
                new InputStreamReader(new FileInputStream(FILE_PATH),
                        StandardCharsets.UTF_8));
        jsonObject = (JSONObject) object;
        JSONArray persons = (JSONArray) jsonObject.get("persons");
        iterator = persons.listIterator();
        while (iterator.hasNext()) {
            personList.add(mapper
                    .readValue(iterator.next().toString(), Person.class));
        }
        return personList;
    }

    /**
     * Retrieve fire stations from json file.
     * @return a list of fire stations
     * @throws IOException if problems occurred during file reading
     * @throws ParseException if problems occurred during parsing the json file
     */
    public List<FireStation> getFireStationsFromJsonFile()
            throws IOException, ParseException {
        mapper = new ObjectMapper();
        jsonParser = new JSONParser();
        object = jsonParser.parse(
                new InputStreamReader(new FileInputStream(FILE_PATH),
                        StandardCharsets.UTF_8));
        jsonObject = (JSONObject) object;
        JSONArray fireStations = (JSONArray) jsonObject.get("firestations");
        iterator = fireStations.listIterator();
        while (iterator.hasNext()) {
            this.stations.add(mapper.readValue(
                    iterator.next().toString(), FireStation.class));
        }
        return this.stations;
    }

    /**
     * Retrieve medical records from json file.
     * @return a list of medical records.
     * @throws IOException if problems occurred during file reading
     * @throws ParseException if problems occurred during parsing the json file
     */
    public List<MedicalRecord> getMedicalRecordsFromJsonFile()
            throws IOException, ParseException {
        mapper = new ObjectMapper();
        jsonParser = new JSONParser();
        object = jsonParser.parse(
                new InputStreamReader(new FileInputStream(FILE_PATH),
                        StandardCharsets.UTF_8));
        jsonObject = (JSONObject) object;
        JSONArray medicalRecords = (JSONArray) jsonObject.get("medicalrecords");
        iterator = medicalRecords.listIterator();
        while (iterator.hasNext()) {
            medicalRecordList.add(mapper.readValue(
                    iterator.next().toString(), MedicalRecord.class));
        }
        return medicalRecordList;
    }
}
