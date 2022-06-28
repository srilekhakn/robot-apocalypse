package com.survive.robotapocalypse.service;

import com.survive.robotapocalypse.dao.RobotRepository;
import com.survive.robotapocalypse.model.Robot;
import com.survive.robotapocalypse.service.exception.NoRecordFound;
import com.survive.robotapocalypse.service.exception.RecordAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RobotService {

    @Autowired
    private RobotRepository robotRepository;

    /**
     * Persists the Robot.
     * <p>
     * The methods checks that there is No Robot with Same id and Saves Robot
     *
     * @param robot the robot object
     * @return the persisted Robot.
     * @throws RecordAlreadyExistException if there is an existing Robot for the SerialNumber.
     */
    public Robot insert(final Robot robot) throws RecordAlreadyExistException {
        boolean robotExist = robotRepository.existsBySerialNumber(robot.getSerialNumber());
        if (robotExist) {
            throw new RecordAlreadyExistException("Robot Already Exist with SerialNumber");
        }
        return robotRepository.save(robot);
    }

    /**
     * Fetch the Robots.
     * <p>
     * Gets the list of Robots
     *
     * @return the list of Robots.
     * @throws NoRecordFound if there is no records of Robots in the DB
     */
    public List<Robot> fetch(String sortBy) throws NoRecordFound {
        List<Robot> robots = robotRepository.findAll(Sort.by(sortBy));

        if (robots.isEmpty()) {
            throw new NoRecordFound("No Record Found");
        }
        return robots;
    }


}
