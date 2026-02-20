package se.jimmy.iths.todolist.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import se.jimmy.iths.todolist.model.WorkoutSession;
import se.jimmy.iths.todolist.repository.WorkoutSessionRepository;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class WorkoutSessionIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WorkoutSessionRepository repository;


    @Test
    void createWorkout_ShouldSaveToDatabase() throws Exception {
        mockMvc.perform(post("/workout")
                        .param("date", LocalDate.now().toString())
                        .param("exerciseType", "Running")
                        .param("durationInMinutes", "45")
                        .param("intensity", "7"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/workout"));

        List<WorkoutSession> sessions = repository.findAll();
        assertEquals(1, sessions.size());
        assertEquals("Running", sessions.getFirst().getExerciseType());
    }

    @Test
    void getWorkoutDetail_ShouldReturnExistingWorkout() throws Exception {
        WorkoutSession session = new WorkoutSession("Swimming", 40, 6, LocalDate.now());
        WorkoutSession savedSession = repository.save(session);

        mockMvc.perform(get("/workout/" + savedSession.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("workoutSession"))
                .andExpect(content().string(containsString("Swimming")))
                .andExpect(content().string(containsString("40")));
    }

    @Test
    void getAllWorkouts_ShouldReturnAllSessions() throws Exception {
        repository.save(new WorkoutSession("Cycling", 60, 8, LocalDate.now()));
        repository.save(new WorkoutSession("Yoga", 30, 2, LocalDate.now()));

        mockMvc.perform(get("/workout"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("workoutSessions", hasSize(2)))
                .andExpect(content().string(containsString("Cycling")))
                .andExpect(content().string(containsString("Yoga")));
    }
}
