package com.survive.robotapocalypse.facade;

import com.survive.robotapocalypse.facade.dto.RobotDTO;
import com.survive.robotapocalypse.model.Robot;
import com.survive.robotapocalypse.model.RobotEnum;
import com.survive.robotapocalypse.service.RobotService;
import io.swagger.v3.oas.annotations.Operation;
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
import java.util.Optional;

@Tag(name = "Robot", description = "The Robot API.")
@Controller
@RequestMapping("/robot")
public class RobotController {

    @Autowired
    private RobotService robotService;
    Logger logger = LoggerFactory.getLogger(RobotController.class);

    @Operation(summary = "Insert a new robot", description = "Persist a new robot and generate its id.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Robot created")})
    @PostMapping(produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<RobotDTO> insert(@Valid @RequestBody final RobotDTO dto) {
        Robot robot = RobotDTO.toRobot(dto);
        Robot insertedRobot = robotService.insert(robot);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RobotDTO.fromRobot(insertedRobot));
    }

    @Operation(summary = "Get List of robots", description = "Get List of Robots")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Robot List")})
    @GetMapping(value = "/", produces = {"application/json"})
    public ResponseEntity<List<Robot>> fetch(@RequestParam("sortBy") Optional<RobotEnum> sortBy) {

        List<Robot> robotList = robotService.fetch(sortBy.isEmpty()?null:sortBy.get().getRobotVariable());
        return ResponseEntity.status(HttpStatus.OK)
                .body(robotList);
    }
}