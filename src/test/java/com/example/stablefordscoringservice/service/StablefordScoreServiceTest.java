package com.example.stablefordscoringservice.service;

import com.example.stablefordscoringservice.StablefordScoringServiceApplication;
import com.example.stablefordscoringservice.entiry.StablefordScore;
import com.example.stablefordscoringservice.repository.StablefordScoringRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
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
        Long id = 1L;
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
        Long id = 1L;
        StablefordScore newScore = new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2);
        when(stablefordScoringRepository.save(newScore)).thenReturn(newScore);
        String code = stablefordScoringService.addScore(newScore);
        assertEquals(code, newScore.getCode());
    }
}
