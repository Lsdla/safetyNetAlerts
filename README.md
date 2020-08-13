# safetyNetAlerts
The main purpose of safetyNetAlerts is sending information to emergency services.

The application should provide information about people exposed to potential dangers
such as fires, floods, hurricanes and so on.
To achieve this task, SafetyNetAlerts must collect specific information
about people who living in an area exposed to danger.

# Getting Started
These instructions will get you a copy of the project up and running on your local
machine for development and testing purposes.

#Prerequisites
What things you need to install and how will you install them:
- Java 1.8 or later.
- Maven 3.6.3 (It is optional since the maven wrapper is present in the project)
- MySql 8.0.
- You can choose to install Postman 7. to send the different requests
as you can use your favorite web browser.

#Installing
A step by step series of examples that tell you how to get a development environment:

1.Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:

https://maven.apache.org/install.html

3.Install MySql:

https://dev.mysql.com/downloads/mysql/

3.Install Postman:

https://learning.postman.com/docs/getting-started/installation-and-updates/

After downloading the mysql 8.0 installer and installing it,
you will be asked to configure the password for the default root account.
This code uses the default `root` account to connect, and the password used is `rootroot`.
If you add another user/credentials, make sure to change those present in the code base.
The actual credentials can be changed from the `application.properties` file.

#Note
You have to run the sql command present in `DataBase.sql` script(that you will find in `safetynetalerts/src/main/resources/script`) in order to create the `alerts_prod_db` database.
The different tables and columns will be created automatically by the Java Persistence API during the first launch of safetyNetAlerts.

#Running App

After installing all the required softwares and creating the database, you will be ready to import the code
into your favorite IDE and run the `SafetyNetAlertsApplication.java` to launch the application.

The application can be run, also, from the command line (for Windows users) or from the terminal (for Linux and IOS users).
To do so, you have to follow these steps :

- Open the command line.
- Browse into the `safetynetalerts` folder.
- Run either `mvn package` command if you have maven installed on your machine or
`./mvnw package` command if you do not have maven on your machine.
Note that running one of these commands will create the different tables and columns in the `alerts_prod_db` database. 
- After running one of the previous commands, a new folder named `target` will appear.
- Browse to this new folder and list its content. You will see a generated `jar` file named `safetynetalerts-0.0.1-SNAPSHOT.jar`.
- To execute this jar file, you need to run this command: `java -jar safetynetalerts-0.0.1-SNAPSHOT.jar`.
- The application is now ready. Choose either Postman or your favourite web browser to perform any CRUD operations.


#CRUD Operations

This application provides multiple endpoints and supports different CRUD operations.

#Endpoint
In addition to the actuator's `health`, `info`, `metrics` and `httptrace`
endpoints that can be accessed respectively from
`http://localhost:8080/actuator/health`, `http://localhost:8080/actuator/info`,
`http://localhost:8080/actuator/metrics` and `http://localhost:8080/actuator/httptrace`,

SafetyNetAlerts allows CRUD operations on the following endpoints:

Some examples of the expected responses will be shown below the urls.

#### **Json file data insertion**

SafetyNet Alerts application provides a new endpoint (**from version 1.0.1 up**)
that allows inserting data retrieved from json file to database.
This operation can be done by via this url:

`http://localhost:8080/jsonToDatabase/insert`

Its main purpose is retrieving each single person from a json file,
linking it to the appropriate fire station and medical record and insert them to database.

#### **FireStation**

**a.GET**

- The different fire stations can be retrieved via this url: `http://localhost:8080/fireStation/fireStations`

_[  
    {"address": "1509 Culver St", "station": 3},  
    {"address": "29 15th St", "station": 2},  
    {"address": "834 Binoc Ave", "station": 3}  
]_


- A list of persons covered by a single fire station can be retrieved via this url: `http://localhost:8080/fireStation/stationNumber?id=<fire_station_id>`.
This url returns, also, the number of children and adults covered by the same fire station.

_{  
    "persons": [  
        {  
            "firstName": "John",
            "lastName": "Boyd",
            "address": "1509 Culver St",
            "phone": "841-874-6512",
            "age": 36.4
        },  
        {  
            "firstName": "Jacob",
            "lastName": "Boyd",
            "address": "1509 Culver St",
            "phone": "841-874-6513",
            "age": 31.4
        },  
        {  
            "firstName": "Tenley",
            "lastName": "Boyd",
            "address": "1509 Culver St",
            "phone": "841-874-6512",
            "age": 8.5
        },  
        {  
            "firstName": "Roger",
            "lastName": "Boyd",
            "address": "1509 Culver St",
            "phone": "841-874-6512",
            "age": 2.9
        },  
        {  
            "firstName": "Felicia",
            "lastName": "Boyd",
            "address": "1509 Culver St",
            "phone": "841-874-6544",
            "age": 34.6
        }  
    ],  
    "numberOfChildren": 2,  
    "numberOfAdults": 3  
}_

**b.POST**

- You can add a new fire station to the database by using this url:

`http://localhost:8080/fireStation/add`

_{  
"address":"951 LoneTree Rd",  
"station":"2"  
}_

- A fire station, and a list of its covered persons can be added at the same time from the same url:

_{  
"address":"951 LoneTree Rd",  
"station":"2",  
    "persons":[  
    { "firstName":"Eric",  
    "lastName":"Cadigan",  
    "address":"951 LoneTree Rd",  
    "city":"Culver", "zip":"97451",  
    "phone":"841-874-7458",  
    "email":"gramps@email.com",  
    "medicalRecord": {  
    "firstName":"Eric",  
    "lastName":"Cadigan",  
    "birthDate":"1945-08-06",  
    "medications":["tradoxidine:400mg"],  
    "allergies":[] } }]  
}_


**c.PUT**

- Update an existing fire station via this url `http://localhost:8080/fireStation/update`:

 _{  
     "id": 2,  
     "address": "1509 Culver",  
     "station": 112  
 }_  
 
Notice that the fire station **id** must be provided to update the fire station successfully.

**d.DELETE**

- For deleting a fire station `http://localhost:8080/fireStation/delete/fire_station_id`

Returns 200 with `Fire station deleted successfully`.

As it can return a 404 error with this message`No fire station that has + id +  was found` 
if no fire station id matches the provided one.

#### **Person**

**a.GET**

- This endpoint `http://localhost:8080/person/persons` retrieves all persons from the database.

- This url returns information related to a given person `http://localhost:8080/person/personInfo/firstName&lastName`

_[  
    {  
        "firstName": "John",  
        "lastName": "Boyd",  
        "address": "1509 Culver St",  
        "email": "jaboyd@email.com",  
        "medicalRecord": {  
            "age": 36.4,  
            "allergies": [  
                "nillacilan"  
            ],  
            "medications": [  
                "aznol:350mg",
                "hydrapermazol:100mg"
            ]  
        }  
    }  
]_  

**b.POST**

You can add a new person to database `http://localhost:8080/person/add`

_{  
        "firstName":"John",  
        "lastName":"Boyd",  
        "address":"1556 Culver St",  
        "city":"Culver",  
        "zip":"97451",  
        "phone":"827741-7334-6512",  
        "email":"jabeeo2zz2yd@email.com",  
        "medicalRecord": {  
	       		"firstName":"John",  
	       		"lastName":"Boyd",  
	       		"birthDate":"2003-03-06",  
	       		"medications":["aznol:350mg", "hydrapermazol:100mg"],  
	       		"allergies":["nillacilan"]  
	       		}  
}_

**c.PUT**

- An existing person can be updated via this url: `http://localhost:8080/person/update`

_{  
        "id": 1,
        "firstName":"John",  
        "lastName":"Boyd",  
        "address":"1556 Culver St",  
        "city":"Culver",  
        "zip":"97451",  
        "phone":"827741-7334-6512",  
        "email":"jabeeo2zz2yd@email.com",  
        "medicalRecord": {  
	       		"firstName":"John",  
	       		"lastName":"Boyd",  
	       		"birthDate":"2003-03-06",  
	       		"medications":["aznol:350mg", "hydrapermazol:100mg"],  
	       		"allergies":["nillacilan"]  
	       		}  

}_

Note that the person's **id** must be provided to update the person successfully.

**d.DELETE**

- An existing person can be deleted from the database `http://localhost:8080/person/delete/firstName&lastName`

This request may return 200ok if the deletion successes. The person's associated medical record will be deleted too.
It can also return a 404 error if no person that has the provided first and last names found in the database.

#### **MedicalRecord**

**a.GET**

- The medical records can be retrieved via this url `http://localhost:8080/medicalRecord/records`

**b.POST**

- To add a new medical record: `http://localhost:8080/medicalRecord/add`

_{  
"firstName":"John",  
"lastName":"Boyd",  
"birthDate":"2012-12-20",  
"medications":["some medications"],  
"allergies":["some allergies"]  
}_

**c.PUT**
- To update an existing medical record: `http://localhost:8080/medicalRecord/update`

_{  
"id": 1,  
"firstName":"John",  
"lastName":"Boyd",  
"birthDate":"2012-12-20",  
"medications":["some medications"],  
"allergies":["some allergies"]  
}_

The **id** must be provided to update a give medical record.

**d.DELETE**

- To delete a medical record: `http://localhost:8080/medicalRecord/delete/firstName&lastName`

This request may return a 200ok if the medical record deleted
as it can return a 404 error if no medical record found in the database.

#### **ChildAlert**

**a.GET**

- To get a list of children,and their household members, who live in a given address: `http://localhost:8080/childAlert?address=<address>`

This request may return an empty list if no children found in the provided address.

#### **phoneAlert**

**a.GET**

- To retrieve all the phone numbers of persons covered by a given fire station: `http://localhost:8080/phoneAlert/fire_station_id`

This request may return 200ok if there is a matching id,
as it can return a 404 error if no matching fire station id found in the database.

#### **communityEmail**

**a.GET**

- To retrieve all the email addresses of people who live in a given city: `http://localhost:8080/communityEmail?city=<city>`

#### **fire**

**a.GET**

- To retrieve all people who live in a given address, and the fire station that covers them:`http://localhost:8080/fire?address=<address>`

#### **flood**

**a.GET**

- To retrieve people covered by a fire station or by a list of fire stations: `http://localhost:8080/flood/stations?<a list of fire_station_ids>`

`http://localhost:8080/flood/stations?stations=id=6&id=1` will return:

_[  
    {  
        "id": 2,  
        "address": "29 15th St",  
        "persons": [  
            {  
                "firstName": "Jonanathan",  
                "lastName": "Marrack",  
                "phone": "841-874-6513",  
                "medicalRecord": {  
                    "age": 31.6,  
                    "allergies": [],  
                    "medications": []  
                }
            }
        ]
    },  
    {  
        "id": 6,  
        "address": "112 Steppes Pl",  
        "persons": [  
            {
                "firstName": "Tony",  
                "lastName": "Cooper",  
                "phone": "841-874-6874",  
                "medicalRecord": {  
                    "age": 26.4,  
                    "allergies": [  
                        "shellfish"
                    ],  
                    "medications": [  
                        "hydrapermazol:300mg",  
                        "dodoxadin:30mg"
                    ]  
                }
            }
        ]
    }
]_  


#Testing

Use either `mvn test` if you have maven on your machine or `./mvnw test` if you do not have maven on your machine.

You can find the generated test reports in the `target` directory.
