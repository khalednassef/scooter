package de.bcg.coup.service;

import de.bcg.coup.service.exception.InvalidScooterCountException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FleetServiceTest {

    private FleetService fleetService = new FleetService();

    @Test
    public void shouldGetMinFleetEngineersExample1() throws Exception {
        ArrayList<Integer> scooters = new ArrayList<>();
        scooters.add(11);
        scooters.add(15);
        scooters.add(13);

        assertEquals(7, fleetService.getMinFleetEngineers(scooters, 9, 5));
    }

    @Test
    public void shouldGetMinFleetEngineersExample2() throws Exception {
        ArrayList<Integer> scooters = new ArrayList<>();
        scooters.add(15);
        scooters.add(10);

        assertEquals(3, fleetService.getMinFleetEngineers(scooters, 12, 5));
    }

    @Test
    public void shouldReturnZeroForEmptyList() throws Exception {
        ArrayList<Integer> scooters = new ArrayList<>();
        assertEquals(0, fleetService.getMinFleetEngineers(scooters, 5, 9));
    }

    @Test(expected = InvalidScooterCountException.class)
    public void shouldThrowInvalidScooterCountException() throws Exception {
        ArrayList<Integer> scooters = new ArrayList<>();
        scooters.add(15);
        scooters.add(10);
        scooters.add(9999999);

        fleetService.getMinFleetEngineers(scooters, 3, 90);
    }

}
