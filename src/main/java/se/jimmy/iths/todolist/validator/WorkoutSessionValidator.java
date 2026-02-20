package se.jimmy.iths.todolist.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import se.jimmy.iths.todolist.exceptions.WorkoutSessionValidationException;
import se.jimmy.iths.todolist.model.WorkoutSession;

import java.time.LocalDate;

@Component
public class WorkoutSessionValidator {

    private static final Logger logger = LoggerFactory.getLogger(WorkoutSessionValidator.class);

    public void validate(WorkoutSession workoutSession) {
        try {
            validateExerciseType(workoutSession.getExerciseType());
            validateDuration(workoutSession.getDurationInMinutes());
            validateIntensity(workoutSession.getIntensity());
            validateDate(workoutSession.getDate());
        } catch (WorkoutSessionValidationException e) {
            logger.warn("Validation failed for WorkoutSession: {}", e.getMessage());
            throw e;
        }
    }

    public void validateExerciseType(String exerciseType) {
        if (exerciseType == null || exerciseType.isBlank()) {
            throw new WorkoutSessionValidationException("Exercise type cannot be empty");
        }
    }

    public void validateDuration(int duration) {
        if (duration <= 0) {
            throw new WorkoutSessionValidationException("Duration must be a positive number of minutes");
        }
    }

    public void validateIntensity(int intensity) {
        if (intensity <= 0 || intensity > 10) {
            throw new WorkoutSessionValidationException("Intensity must be between 1-10");
        }
    }

    public void validateDate(LocalDate date) {
        if (date == null) {
            throw new WorkoutSessionValidationException("Workout date cannot be null");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new WorkoutSessionValidationException("Workout date cannot be in the future");
        }
    }
}
