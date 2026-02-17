package se.jimmy.iths.todolist.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.jimmy.iths.todolist.exceptions.WorkoutSessionValidationException;
import se.jimmy.iths.todolist.model.WorkoutSession;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutSessionValidatorTest {
    private WorkoutSessionValidator validator;
    private WorkoutSession validSession;

    @BeforeEach
    void setUp() {
        validator = new WorkoutSessionValidator();
        validSession = new WorkoutSession("Running", 30, 5, LocalDate.now());
    }

    @Test
    @DisplayName("Should not throw exception for a valid session")
    void validate_ValidSession_ShouldNotThrow() {
        assertDoesNotThrow(() -> validator.validate(validSession));
    }

    @Test
    @DisplayName("Should throw exception when duration is invalid in session")
    void validate_InvalidSession_ShouldThrowException() {
        validSession.setDurationInMinutes(0);
        assertThrows(WorkoutSessionValidationException.class, () -> validator.validate(validSession));
    }

    @Test
    @DisplayName("Should not throw exception for a valid exercise type")
    void validateExerciseType_ValidType_ShouldNotThrow() {
        assertDoesNotThrow(() -> validator.validateExerciseType("Running"));
    }

    @Test
    @DisplayName("Should throw exception for null exercise type")
    void validateExerciseType_Null_ShouldThrowException() {
        assertThrows(WorkoutSessionValidationException.class, () -> validator.validateExerciseType(null));
    }

    @Test
    @DisplayName("Should throw exception for empty exercise type")
    void validateExerciseType_Empty_ShouldThrowException() {
        assertThrows(WorkoutSessionValidationException.class, () -> validator.validateExerciseType(""));
    }

    @Test
    @DisplayName("Should throw exception for blank exercise type")
    void validateExerciseType_Blank_ShouldThrowException() {
        assertThrows(WorkoutSessionValidationException.class, () -> validator.validateExerciseType("   "));
    }

    @Test
    @DisplayName("Should not throw exception for a valid duration")
    void validateDuration_ValidDuration_ShouldNotThrow() {
        assertDoesNotThrow(() -> validator.validateDuration(1));
    }

    @Test
    @DisplayName("Should throw exception for zero duration")
    void validateDuration_Zero_ShouldThrowException() {
        assertThrows(WorkoutSessionValidationException.class, () -> validator.validateDuration(0));
    }

    @Test
    @DisplayName("Should throw exception for negative duration")
    void validateDuration_Negative_ShouldThrowException() {
        assertThrows(WorkoutSessionValidationException.class, () -> validator.validateDuration(-10));
    }

    @Test
    @DisplayName("Should not throw exception for minimum valid intensity (1)")
    void validateIntensity_Minimum_ShouldNotThrow() {
        assertDoesNotThrow(() -> validator.validateIntensity(1));
    }

    @Test
    @DisplayName("Should not throw exception for maximum valid intensity (10)")
    void validateIntensity_Maximum_ShouldNotThrow() {
        assertDoesNotThrow(() -> validator.validateIntensity(10));
    }

    @Test
    @DisplayName("Should throw exception for intensity below range (0)")
    void validateIntensity_TooLow_ShouldThrowException() {
        assertThrows(WorkoutSessionValidationException.class, () -> validator.validateIntensity(0));
    }

    @Test
    @DisplayName("Should throw exception for intensity above range (11)")
    void validateIntensity_TooHigh_ShouldThrowException() {
        assertThrows(WorkoutSessionValidationException.class, () -> validator.validateIntensity(11));
    }

    @Test
    @DisplayName("Should not throw exception for current date")
    void validateDate_Today_ShouldNotThrow() {
        assertDoesNotThrow(() -> validator.validateDate(LocalDate.now()));
    }

    @Test
    @DisplayName("Should not throw exception for past date")
    void validateDate_PastDate_ShouldNotThrow() {
        assertDoesNotThrow(() -> validator.validateDate(LocalDate.now().minusDays(1)));
    }

    @Test
    @DisplayName("Should throw exception for null date")
    void validateDate_Null_ShouldThrowException() {
        assertThrows(WorkoutSessionValidationException.class, () -> validator.validateDate(null));
    }

    @Test
    @DisplayName("Should throw exception for future date")
    void validateDate_FutureDate_ShouldThrowException() {
        assertThrows(WorkoutSessionValidationException.class, () -> validator.validateDate(LocalDate.now().plusDays(1)));
    }
}
