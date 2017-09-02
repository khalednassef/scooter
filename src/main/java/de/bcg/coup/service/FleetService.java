package de.bcg.coup.service;

import de.bcg.coup.service.exception.InvalidScooterCountException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class FleetService {

    /**
     * The approach here is to pass through the list of districts and choose
     * which district is the best for the fleet manager with respect to
     * minimizing the loss of resources. After choosing the FM district (index),
     * the number of fleet engineers needed is calculated correspondingly.
     *
     * @param scooters  a list of number of scooters per district.
     * @param fmLimit   the amount of scooters a Fleet Manager can maintain.
     * @param feLimit   the amount of scooters a Fleet Engineer can maintain.
     * @return  the minimum number of fleet engineers needed to help the FM
     *          maintain all scooters in all districts.
     */
    public int getMinFleetEngineers(List<Integer> scooters,
                                    Integer fmLimit,
                                    Integer feLimit) {
        if (scooters.isEmpty()) {
            return 0;
        }

        int fmIndex = findBestFleetManagerDistrictIndex(scooters, fmLimit, feLimit);

        return calculateFleetEngineerCount(
                scooters,
                fmLimit,
                feLimit,
                fmIndex
        );
    }

    private int findBestFleetManagerDistrictIndex(List<Integer> scooters,
                                                  Integer fmLimit,
                                                  Integer feLimit) {
        int index = 0;
        int temp;
        int wastedResources = Integer.MAX_VALUE;
        for (int i = 0; i < scooters.size(); i++) {
            Integer scooterPerDistrict = scooters.get(i);
            validate(scooterPerDistrict, i);

            temp = findCurrentWastedResources(
                    fmLimit,
                    feLimit,
                    scooterPerDistrict
            );
            if (temp < wastedResources) {
                index = i;
                wastedResources = temp;
            }
        }
        return index;
    }

    private int findCurrentWastedResources(Integer fmLimit,
                                           Integer feLimit,
                                           Integer scooterPerDistrict) {
        int temp;
        if (scooterPerDistrict >= fmLimit) {
            temp = scooterPerDistrict - fmLimit;
            if (temp > 0) {
                if (temp > feLimit) {
                    temp = feLimit - (temp % feLimit);
                } else {
                    temp = feLimit - temp;
                }
            }
        } else {
            temp = fmLimit - scooterPerDistrict;
        }
        return temp;
    }

    private void validate(Integer scootersCount, Integer districtIndex) {
        if (scootersCount < 0 || scootersCount > 1000) {
            throw new InvalidScooterCountException(
                    format("Invalid scooter count: %s in district index: %s", scootersCount, districtIndex)
            );
        }
    }

    private int calculateFleetEngineerCount(List<Integer> scooters,
                                            Integer fmLimit,
                                            Integer feLimit,
                                            int index) {
        int feCount = 0;
        for (int i = 0; i < scooters.size(); i++) {
            if (i == index) {
                int remaining = scooters.get(i) % fmLimit;
                feCount += Math.ceil((double) remaining / (double) feLimit);
            } else {
                feCount += Math.ceil((double) scooters.get(i) / (double) feLimit);
            }
        }
        return feCount;
    }

}
