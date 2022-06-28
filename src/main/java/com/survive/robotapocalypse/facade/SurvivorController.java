package com.survive.robotapocalypse.facade;

import com.survive.robotapocalypse.facade.dto.MarkInfectedDTO;
import com.survive.robotapocalypse.facade.dto.SurvivorDTO;
import com.survive.robotapocalypse.model.MarkInfected;
import com.survive.robotapocalypse.model.Survivor;
import com.survive.robotapocalypse.service.SurvivorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Survivor", description = "The Survivor API.")
@Controller
@RequestMapping("/survivor")
public class SurvivorController {

    Logger logger = LoggerFactory.getLogger(SurvivorController.class);
    @Autowired
    private SurvivorService survivorService;

    @Operation(summary = "Insert a new survivor", description = "Persist a new survivor and generate its id.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Survivor created")})
    @PostMapping(produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<SurvivorDTO> insert(@Valid @RequestBody final SurvivorDTO dto) {
        Survivor survivor = SurvivorDTO.toSurvivor(dto);
        Survivor insertedSurvivor = survivorService.insert(survivor);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SurvivorDTO.fromSurvivor(insertedSurvivor));
    }

    @Operation(summary = "Update an existing survivor location.", description = "Update an already persisted survivor. only location is allowed from the update.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Survivor updated")})
    @PutMapping(value = "/{survivorId}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<SurvivorDTO> update(
            @Parameter(description = "Id of the survivor to update", required = true) @PathVariable("survivorId") final String survivorId,
            @Parameter(description = "", required = true) @Valid @RequestBody final SurvivorDTO dto) {
        Survivor survivor = SurvivorDTO.toSurvivor(dto);
        Survivor updateSurvivor = survivorService.update(survivorId, survivor);
        return ResponseEntity.ok(SurvivorDTO.fromSurvivor(updateSurvivor));
    }

    @Operation(summary = "Flag Survivor as Infected.", description = "API to flag Survivor as Infected.Only after 3 survivor mark a Survivor as infected,Survivor will be Zombie")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully marked as infected")})
    @PostMapping(path = "/mark-infected", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<MarkInfectedDTO> markInfected(
            @Parameter(description = "", required = true) @Valid @RequestBody final MarkInfectedDTO dto) {
        MarkInfected markInfected = MarkInfectedDTO.toMarkInfected(dto);
        MarkInfected mi = survivorService.markInfected(markInfected);
        return ResponseEntity.ok(MarkInfectedDTO.fromMarkInfected(mi));
    }

    @Operation(summary = "Get Infected Survivor", description = "Infected Survivor")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "List of Infected Survivor")})
    @GetMapping(path = "/infected", produces = {"application/json"})
    public ResponseEntity<List<Survivor>> infected() {
        List<Survivor> infectedSurvivors = survivorService.getInfectedSurvivors();
        return ResponseEntity.ok(infectedSurvivors);
    }

    @Operation(summary = "Get Non Infected Survivor", description = "Non Infected Survivor")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "List of Non Infected Survivor")})
    @GetMapping(path = "/non-infected", produces = {"application/json"})
    public ResponseEntity<List<Survivor>> nonInfected() {
        List<Survivor> nonInfectedSurvivors = survivorService.getNonInfectedSurvivors();
        return ResponseEntity.ok(nonInfectedSurvivors);
    }

    @Operation(summary = "Get Infected Survivor", description = "Infected Survivor")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Percentage of Infected Survivor")})
    @GetMapping(path = "/infected/percentage", produces = {"application/json"})
    public ResponseEntity<Double> percentAgeInfected() {
        double infectedPercent = survivorService.getInfectedSurvivorsPercentage();
        return ResponseEntity.ok(infectedPercent);
    }

    @Operation(summary = "Get Non Infected Survivor", description = "Non Infected Survivor")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Percentage of Non Infected Survivor")})
    @GetMapping(path = "/non-infected/percentage", produces = {"application/json"})
    public ResponseEntity<Double> percentAgeNonInfected() {
        double nonInfectedSurvivorsPercentage = survivorService.getNonInfectedSurvivorsPercentage();
        return ResponseEntity.ok(nonInfectedSurvivorsPercentage);
    }

}
