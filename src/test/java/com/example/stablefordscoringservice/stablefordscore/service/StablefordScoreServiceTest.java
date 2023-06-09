package com.example.stablefordscoringservice.stablefordscore.service;

import com.example.stablefordscoringservice.StablefordScoringServiceApplication;
import com.example.stablefordscoringservice.entity.HoleAnalysis;
import com.example.stablefordscoringservice.entity.StablefordScore;
import com.example.stablefordscoringservice.exceptions.CustomDataNotFoundException;
import com.example.stablefordscoringservice.exceptions.NullScoreException;
import com.example.stablefordscoringservice.repository.StablefordScoringRepository;
import com.example.stablefordscoringservice.service.stablefordscore.StablefordScoringImplementation;
import org.junit.jupiter.api.BeforeEach;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    private static UUID id = UUID.randomUUID();
    private static StablefordScore score =  new StablefordScore("271220221", "475", 5, 2, 5, 3);

    private static StablefordScore newScore = new StablefordScore("271220221", "475", 5, 2, 5, 3);

    private static HoleAnalysis holeAnalysis = new HoleAnalysis(210.5, "Miss Hit", 3);

    @DisplayName("JUnit test for returning list of stableford scores")
    @Test
    public void givenStablefordScoresList_whenGetAllStablefordScores_thenReturnStablefordScoreList() {
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
        when(stablefordScoringRepository.save(newScore)).thenReturn(newScore);
        String code = stablefordScoringService.addScore(newScore);
        assertEquals(code, newScore.getId());
    }

    @DisplayName("JUnit test for find score by id")
    @Test
    public void findScoreById() {
        when(stablefordScoringRepository.findById(String.valueOf(id))).thenReturn(Optional.of(score));
        Optional<StablefordScore> result = stablefordScoringService.getScoreById(id.toString());
        assertThat(result).isNotNull();
        assertEquals(Optional.of(score), result);
        verify(stablefordScoringRepository, times(1)).findById(String.valueOf(id));
    }

    @DisplayName("JUnit test for find score by holecode")
    @Test
    public void findScoreByHoleCode() {
        when(stablefordScoringRepository.findStablefordScoresByHoleCode(score.getHoleCode())).thenReturn(Arrays.asList(score));
        List<StablefordScore> result = stablefordScoringService.getAllScoresByHoleCode(score.getHoleCode());
        assertThat(result).isNotNull();
        assertEquals(Arrays.asList(score), result);
        verify(stablefordScoringRepository, times(1)).findStablefordScoresByHoleCode(score.getHoleCode());
    }

    @DisplayName("Throw an exception when data returns null")
    @Test
    public void throwExceptionWhenDataReturnsNull() {
        when(stablefordScoringRepository.findById(String.valueOf(id))).thenReturn(null);
        assertThrows(NullScoreException.class, () -> {
            stablefordScoringService.getScoreById(id.toString());
        });
    }

    @DisplayName("JUnit test for update score by id")
    @Test
    public void updateScoreById() {
        holeAnalysis.setId(String.valueOf(UUID.randomUUID()));
        newScore.setHoleAnalysis(holeAnalysis);
        score.setHoleAnalysis(holeAnalysis);
        when(stablefordScoringRepository.findById(String.valueOf(id))).thenReturn(Optional.of(score));
        StablefordScore result = stablefordScoringService.updateScoreById(id.toString(), newScore);
        assertThat(result).isNotNull();
        assertEquals(result.getLength(), String.valueOf(475));
        assertEquals(result.getPar(), 5);
        verify(stablefordScoringRepository, times(1)).findById(String.valueOf(id));
    }

    @DisplayName("Throw an exception when data returns null when updating")
    @Test
    public void throwExceptionWhenDataReturnsNullInUpdating() {
        when(stablefordScoringRepository.findById(String.valueOf(id))).thenReturn(null);
        assertThrows(NullPointerException.class, () -> {
            stablefordScoringService.updateScoreById(id.toString(), any(StablefordScore.class));
        });
    }
}
