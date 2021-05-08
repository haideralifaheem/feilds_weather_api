# Spring Boot REST API with Mongodb database
# How to get this project
you could clone the project

```sh
git clone https://github.com/haideralifaheem/fields_weather_api.git
```

# Field Weather API

Field Weather APi is based on spring boot REST application architecture.API main goal is to provide single REST end point to create new Field Data into Mongodb DataBase.
Rest micro service also call Agro Montering Service to create polygone.
Both Field data and Weather data is saved into MongoDb cluster.
Also Created end point to get the weather history based on created polygon id.

Project Prerequisits
Assumptions, Limitations and Facts:
1. Springboot 2.3.4 and Java 11 for this project.
2. Mongodb database(local or online cluster).collection name is Fields

for testing the Agro Weather Monitering service i used https://www.mockable.io/
i have created 2 mockable,1 for polygon and other for weather history.
mockable simply recieve ther request and send the hard coded json back.

To  run api using maven
```sh
./mvnw spring-boot:run
./mvnw package && java -jar target/fieldsWeather-0.0.1.jar
```

### Containerize It
    
Docker has a simple "Dockerfile" file format that it uses to specify the "layers" of an image. So let’s go ahead and create a Dockerfile in our Spring Boot project:
You can run it (if you are using Maven) with
```sh
$ docker build -t springio/fields_weather_rest_api .
$ docker run -p 8888:8888 springio/fields_weather_rest_api
```
Rest endpoint and Request calls
```bash
  http://{server}:{port}/fields/findAll
  http://{server}:{port}/fields/{fieldId} //Search Field by Id
  http://{server}:{port}/fields/Name/{name} //Search fields by name
  http://{server}:{port}/fields/save //Create new field and also call agro service to create new polygon
  http://{server}:{port}/fields/update/{fieldid} //Update field by calling field id and in request body field data
  http://{server}:{port}/fields/delete/{fieldid} //to delete the field with field id
```
Agro Monitering Service weather History provide weather history for last 7 days
```bash
  http://{server}:{port}/fields/{fieldId}/weather

```
Post method to Create/Update new field and also create polygon and save the new polygon into cluster and data is saved as refrence field into databse.

sample field json is as follow

```json
{
   "id":"a0f63e74-d7ef-4924-acb3-0e770ae9ec98",
   "name":"Potato field",
   "created":"2020-07-25T10:03:56.782Z",
   "updated":"",
   "countryCode":"DEU",
   "boundaries":{
      "id":"a0f63e74-d7ef-4924-acb3-0e960ae9ec98",
      "created":"2020-07-25T10:03:56.782Z",
      "updated":"",
      "geoJson":{
         "type":"Feature",
         "properties":{
            
         },
         "geometry":{
            "type":"Polygon",
            "coordinates":[
               [
                  [
                     -5.553604888914691,
                     33.88229680420605
                  ],
                  [
                     -5.5516736984239685,
                     33.88229680420605
                  ],
                  [
                     -5.5516736984239685,
                     33.88372189858022
                  ],
                  [
                     -5.555965232847882,
                     33.88390003370375
                  ],
                  [
                     -5.555965232847882,
                     33.88229680420605
                  ],
                  [
                     -5.553604888914691,
                     33.88229680420605
                  ]
               ]
            ]
         }
      }
   }
}
```

Weather History response json is as Follow
```json
{
   "weather":[
      {
         "timestamp":"1485705600",
         "temperature":288.15,
         "humidity":85,
         "temperatureMax":289.16,
         "temperatureMin":280.16
      },
      {
         "timestamp":"1485705700",
         "temperature":288.15,
         "humidity":85,
         "temperatureMax":289.16,
         "temperatureMin":280.16
      },
      "..."
   ]
}
```