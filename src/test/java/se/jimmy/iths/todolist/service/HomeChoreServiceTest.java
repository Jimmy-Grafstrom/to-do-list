package se.jimmy.iths.todolist.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jimmy.iths.todolist.exceptions.HomeChoreNotFoundException;
import se.jimmy.iths.todolist.model.HomeChore;
import se.jimmy.iths.todolist.repository.HomeChoreRepository;
import se.jimmy.iths.todolist.validator.HomeChoreValidator;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HomeChoreServiceTest {
    @Mock
    private HomeChoreRepository repository;

    @Mock
    private HomeChoreValidator validator;

    @InjectMocks
    private HomeChoreService service;

    private HomeChore chore;

    @BeforeEach
    public void setUp() {
        chore = new HomeChore("Vacuum","Living room",false,10);
        chore.setId(1L);
    }

    @Test
    @DisplayName("getChore found and returns correctly")
    void getChoreCorrect() {
        when(repository.findById(1L)).thenReturn(Optional.of(chore));

        HomeChore result = service.getChore(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    @DisplayName("geChore not found and throws exception")
    void getChoreIncorrect() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(HomeChoreNotFoundException.class, () -> service.getChore(1L));
    }
}
