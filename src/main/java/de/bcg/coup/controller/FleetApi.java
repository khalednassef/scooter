package de.bcg.coup.controller;

import de.bcg.coup.controller.entity.FleetEngineerCountDTO;
import de.bcg.coup.controller.entity.ParamsDTO;
import de.bcg.coup.service.FleetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/api")
public class FleetApi {

    @Autowired
    private FleetService fleetService;

    /**
     * Calculates minimum amount of fleet engineers needed to help
     * the fleet manager maintain all scooters given in paramsDTO.
     *
     * @param paramsDTO input for min fleet engineers calculation.
     * @return min fleet engineers needed to cover all scooters.
     */
    @RequestMapping(path = "/fleetEngineers", method = POST)
    public ResponseEntity<FleetEngineerCountDTO> calculateMinFleetEngineers(@RequestBody @Valid ParamsDTO paramsDTO) {
        int result = fleetService.getMinFleetEngineers(
                paramsDTO.getScooters(),
                paramsDTO.getC(),
                paramsDTO.getP()
        );

        return ResponseEntity.ok(new FleetEngineerCountDTO(result));
    }

}
