package com.example.stablefordscoringservice.service;

import com.example.stablefordscoringservice.StablefordScoringServiceApplication;
import com.example.stablefordscoringservice.entity.StablefordScore;
import com.example.stablefordscoringservice.exceptions.CustomDataNotFoundException;
import com.example.stablefordscoringservice.exceptions.NullScoreException;
import com.example.stablefordscoringservice.repository.StablefordScoringRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/** This class needs Mockito and junit-jupiter to run the test*/
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = StablefordScoringServiceApplication.class)
public class StablefordScoreServiceTest {

    @Mock
    private StablefordScoringRepository stablefordScoringRepository;

    @InjectMocks
    private StablefordScoringImplementation stablefordScoringService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("JUnit test for returning list of stableford scores")
    @Test
    public void givenStablefordScoresList_whenGetAllStablefordScores_thenReturnStablefordScoreList() {
        UUID id = UUID.randomUUID();
        StablefordScore score =  new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2);

        /** given - precondition setup */
        when(stablefordScoringRepository.findAll()).thenReturn(Arrays.asList(score));

        /** when - action or the behavior that we are going to test */
        List<StablefordScore> expectedResults = stablefordScoringService.getAllScores();

        /** then - verify the expected vs actual */
        assertThat(expectedResults).isNotNull();
        assertThat(expectedResults.size()).isEqualTo(1);
        assertEquals(Arrays.asList(score), expectedResults);
        verify(stablefordScoringRepository, times(1)).findAll();
    }

    @DisplayName("JUnit test for creating new stableford scores")
    @Test
    public void createNewStablefordScore() {
        UUID id = UUID.randomUUID();
        StablefordScore newScore = new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2);
        when(stablefordScoringRepository.save(newScore)).thenReturn(newScore);
        String code = stablefordScoringService.addScore(newScore);
        assertEquals(code, newScore.getCode());
    }

    @DisplayName("JUnit test for find score by id")
    @Test
    public void findScoreById() {
        UUID id = UUID.randomUUID();
        StablefordScore score = new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2);
        when(stablefordScoringRepository.findById(id)).thenReturn(Optional.of(score));
        Optional<StablefordScore> result = stablefordScoringService.getScoreById(id.toString());
        assertThat(result).isNotNull();
        assertEquals(Optional.of(score), result);
        verify(stablefordScoringRepository, times(1)).findById(id);
    }

    @DisplayName("Throw an exception when data returns null")
    @Test
    public void throwExceptionWhenDataReturnsNull() {
        UUID id = UUID.randomUUID();
        when(stablefordScoringRepository.findById(id)).thenReturn(null);
        assertThrows(NullScoreException.class, () -> {
            stablefordScoringService.getScoreById(id.toString());
        });
    }

    @DisplayName("Throw an exception when data not found")
    @Disabled
    @Test
    public void throwExceptionWhenDataIsNotFound() {
        when(stablefordScoringRepository.findAll()).thenReturn(null);
        assertThrows(CustomDataNotFoundException.class, () -> {
            stablefordScoringService.getAllScores();
        });
    }

    @DisplayName("JUnit test for update score by id")
    @Test
    public void updateScoreById() {
        UUID id = UUID.randomUUID();
        StablefordScore score = new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2);
        StablefordScore newScore = new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 210.00, "Miss Hit", 3);
        when(stablefordScoringRepository.findById(id)).thenReturn(Optional.of(score));
        StablefordScore result = stablefordScoringService.updateScoreById(id.toString(), newScore);
        assertThat(result).isNotNull();
        assertEquals(result.getTeeOffLength(), Double.valueOf(210.00));
        assertEquals(result.getTeeOffDirection(), "Miss Hit");
        verify(stablefordScoringRepository, times(1)).findById(id);
    }

    @DisplayName("Throw an exception when data returns null when updating")
    @Test
    public void throwExceptionWhenDataReturnsNullInUpdating() {
        UUID id = UUID.randomUUID();
        when(stablefordScoringRepository.findById(id)).thenReturn(null);
        assertThrows(NullScoreException.class, () -> {
            stablefordScoringService.updateScoreById(id.toString(), any(StablefordScore.class));
        });
    }
}
