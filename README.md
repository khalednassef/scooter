# scooter

To run the project, please make sure to have maven installed then run the following command:

```
mvn package && java -jar target/scooter-0.0.1-SNAPSHOT.jar
```

Endpoint documentation:

POST `http://localhost:8080/api/fleetEngineers`

Request body:
```
{
	"scooters": [11, 15, 13],
	"c": 9,
	"p": 5
}
```

Response
```
{
    "fleetEngineers": 7
}
```


Example curl:
```
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ "scooters": [11, 15, 13], "c": 9, "p": 5 }' 'http://localhost:8080/api/fleetEngineers'
```
