package se.jimmy.iths.todolist.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import se.jimmy.iths.todolist.exceptions.WorkoutSessionNotFoundException;
import se.jimmy.iths.todolist.model.WorkoutSession;
import se.jimmy.iths.todolist.repository.WorkoutSessionRepository;
import se.jimmy.iths.todolist.validator.WorkoutSessionValidator;

import java.util.List;

@Service
public class WorkoutSessionService {

    private static final Logger logger = LoggerFactory.getLogger(WorkoutSessionService.class);

    private final WorkoutSessionRepository workoutSessionRepository;
    private final WorkoutSessionValidator validator;

    public WorkoutSessionService(WorkoutSessionRepository workoutSessionRepository, WorkoutSessionValidator validator) {
        this.workoutSessionRepository = workoutSessionRepository;
        this.validator = validator;
    }

    public List<WorkoutSession> getAllWorkoutSessions() {
        return workoutSessionRepository.findAll();
    }

    public WorkoutSession getById(Long id) {
        return workoutSessionRepository.findById(id).orElseThrow(() -> {
            logger.error("WorkoutSession with id {} not found", id);
            return new WorkoutSessionNotFoundException("Session not found");
        });
    }

    public WorkoutSession create(WorkoutSession workoutSession) {
        validator.validate(workoutSession);
        return workoutSessionRepository.save(workoutSession);
    }

    public WorkoutSession update(WorkoutSession workoutSession, Long id) {
        WorkoutSession sessionToUpdate = getById(id);
        sessionToUpdate.setExerciseType(workoutSession.getExerciseType());
        sessionToUpdate.setDurationInMinutes(workoutSession.getDurationInMinutes());
        sessionToUpdate.setIntensity(workoutSession.getIntensity());
        sessionToUpdate.setDate(workoutSession.getDate());
        validator.validate(sessionToUpdate);
        return workoutSessionRepository.save(sessionToUpdate);
    }

    public void delete(Long id) {
        try {
            getById(id);
            workoutSessionRepository.deleteById(id);
        } catch (WorkoutSessionNotFoundException e) {
            logger.error("Delete failed: ID {} not found", id);
            throw e;
        }
    }
}
