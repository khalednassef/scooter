package de.bcg.coup.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import de.bcg.coup.controller.entity.ParamsDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FleetApiIntegrationTest {

    @LocalServerPort
    private int serverPort;

    @Before
    public void setupRestAssuredURI() throws Exception {
        RestAssured.baseURI = "http://localhost:" + serverPort;
    }

    @Test
    public void shouldCalculateMinFleetEngineers() throws Exception {
        ArrayList<Integer> scooters = new ArrayList<>();
        scooters.add(11);
        scooters.add(15);
        scooters.add(13);
        ParamsDTO paramsDTO = new ParamsDTO(scooters, 9, 5);

        given()
                .when()
                .contentType(ContentType.JSON)
                .body(toJSON(paramsDTO))
                .post("/api/fleetEngineers")
                .then()
                .statusCode(200)
                .body("fleetEngineers", equalTo(7));
    }

    @Test
    public void shouldReturnBadRequestForInvalidValues() throws Exception {
        ArrayList<Integer> scooters = new ArrayList<>();
        int invalidScooterCount = -1;
        scooters.add(11);
        scooters.add(invalidScooterCount);
        scooters.add(13);
        ParamsDTO paramsDTO = new ParamsDTO(scooters, 9, 5);

        given()
                .when()
                .contentType(ContentType.JSON)
                .body(toJSON(paramsDTO))
                .post("/api/fleetEngineers")
                .then()
                .statusCode(400);
    }

    private String toJSON(ParamsDTO paramsDTO) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(paramsDTO);
    }

}
