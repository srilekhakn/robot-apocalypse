package com.survive.robotapocalypse.service;

import com.survive.robotapocalypse.dao.MarkInfectedRepository;
import com.survive.robotapocalypse.dao.ResourcesRepository;
import com.survive.robotapocalypse.dao.SurvivorRepository;
import com.survive.robotapocalypse.model.MarkInfected;
import com.survive.robotapocalypse.model.Resources;
import com.survive.robotapocalypse.model.ResourcesEnum;
import com.survive.robotapocalypse.model.Survivor;
import com.survive.robotapocalypse.service.exception.NotFoundException;
import com.survive.robotapocalypse.service.exception.RecordAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SurvivorService {

    @Autowired
    private SurvivorRepository survivorRepository;

    @Autowired
    private MarkInfectedRepository markInfectedRepository;

    @Autowired
    private ResourcesRepository resourcesRepository;

    Logger logger = LoggerFactory.getLogger(SurvivorService.class);

    /**
     * Persists the Survivor.
     * <p>
     * The methods checks that there is No Survivor with Same id and Saves survivor
     *
     * @param survivor the survivor object
     * @return the persisted survivor.
     * @throws RecordAlreadyExistException if there is an existing survivor for the survivor's period.
     */
    public Survivor insert(final Survivor survivor) throws RecordAlreadyExistException {
        boolean existingSurvivor = survivorRepository.existsBySurvivorId(survivor.getSurvivorId());
        if (existingSurvivor) {
            logger.error("Survivor already exist with the given Id");
            throw new RecordAlreadyExistException("Survivor already exist with the given Id");
        }
        Set<ResourcesEnum> resourcesEnumSet = survivor.getResourcesEnums();
        Set<Resources> resourcesSet = new HashSet<>();
        for (ResourcesEnum re : resourcesEnumSet) {
            resourcesSet.add(resourcesRepository.findById(re.getResourceId()).orElse(new Resources(re.getResourceId(), re.getResourceName())));
        }
        survivor.setResources(resourcesSet);
        return survivorRepository.save(survivor);
    }

    /**
     * Saves the survivor for a given id. Not all fields can be updated, only Location can be updated.
     * <p>
     *
     *
     * @param survivorId the id of the survivor to update.
     * @param survivor   the survivor object.
     * @return the save survivor.
     * @throws IllegalArgumentException if the survivor type is different to a persisted one.
     * @throws NotFoundException        if the given survivor id is not present in the database.
     */
    public Survivor update(final String survivorId, final Survivor survivor)
            throws IllegalArgumentException, NotFoundException {
        Optional<Survivor> existingRecord = survivorRepository.findById(survivorId);
        if (existingRecord.isEmpty()) {
            throw new NotFoundException("Survivor not found!");
        }
        Survivor existingSurvivor = existingRecord.get();

        // Check if user is trying to Update Gender
        // This check can be done for other fields as well
        if (!existingSurvivor.getGender().equals(survivor.getGender())) {
            throw new IllegalArgumentException("Survivor Gender can't be changed from".concat(existingSurvivor.getGender().toString()).concat("to").concat(survivor.getGender().toString()));
        }

        existingSurvivor.setLatitude(survivor.getLatitude());
        existingSurvivor.setLongitude(survivor.getLongitude());

        return survivorRepository.save(existingSurvivor);
    }

    /**
     * Mark survivor as Infected
     * <p>
     *
     *
     * @param markInfected the markInfected object.
     * @return save the markInfected.
     * @throws IllegalArgumentException if the survivor type is different to a persisted one.
     * @throws NotFoundException        if the given survivor id is not present in the database.
     */
    public MarkInfected markInfected(final MarkInfected markInfected)
            throws NotFoundException {
        boolean survivorStatus = survivorRepository.existsById(markInfected.getSurvivorId());
        boolean infectedStatus = survivorRepository.existsById(markInfected.getInfectedSurvivorId());
        if (!survivorStatus){
            throw new NotFoundException("SurvivorId not found!");
        }
        if (!infectedStatus){
            throw new NotFoundException("Infected survivorId not found!");
        }
        boolean statusCheck = markInfectedRepository.existsBySurvivorIdAndInfectedSurvivorId(markInfected.getSurvivorId(),markInfected.getInfectedSurvivorId());

        if (statusCheck) {
            throw new RecordAlreadyExistException("You have already marked for this infected");
        }

        MarkInfected savedRecord = markInfectedRepository.save(markInfected);

        int count = markInfectedRepository.countByInfectedSurvivorId(markInfected.getInfectedSurvivorId());

        if(count >= 3){
             Survivor updateRecord = survivorRepository.findBySurvivorId(markInfected.getInfectedSurvivorId());
             updateRecord.setInfected(true);
             survivorRepository.save(updateRecord);
        }

        return savedRecord;
    }

    /**
     * Get Infected Survivors
     * <p>
     *
     * @return List of infected.
     * @throws NotFoundException   if no records found.
     */
    public List<Survivor> getInfectedSurvivors(){
        return survivorRepository.findByInfected(true);
    }

    /**
     * Get Non-Infected Survivors
     * <p>
     *
     * @return List of Non-infected.
     * @throws NotFoundException   if no records found.
     */
    public List<Survivor> getNonInfectedSurvivors(){
        return survivorRepository.findByInfected(false);
    }

    /**
     * Get Infected Survivors percentage
     * <p>
     *
     * @return List of infected percentage.
     * @throws NotFoundException   if no records found.
     */
    public double getInfectedSurvivorsPercentage(){
        long infectedCount = survivorRepository.countByInfected(true);
        long totalCount = survivorRepository.count();
        return ((double) infectedCount/totalCount)*100;
    }

    /**
     * Get Non-Infected Survivors percentage
     * <p>
     *
     * @return List of non-infected percentage.
     * @throws NotFoundException   if no records found.
     */
    public double getNonInfectedSurvivorsPercentage(){
        long nonInfectedCount = survivorRepository.countByInfected(false);
        long totalCount = survivorRepository.count();
        return ((double) nonInfectedCount/totalCount)*100;
    }
}