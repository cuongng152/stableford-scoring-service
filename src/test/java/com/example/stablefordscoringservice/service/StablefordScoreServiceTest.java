package com.example.stablefordscoringservice.service;

import com.example.stablefordscoringservice.entiry.StablefordScore;
import com.example.stablefordscoringservice.repository.StablefordScoringRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class StablefordScoreServiceTest {

    @Mock
    private StablefordScoringRepository stablefordScoringRepository;

    @InjectMocks
    private StablefordScoringImplementation stablefordScoringService;


    @DisplayName("JUnit test for returning list of stableford scores")
    @Test
    @Disabled
    public void givenStablefordScoresList_whenGetAllStablefordScores_thenReturnStablefordScoreList() {
        Long id = 1L;
        List<StablefordScore> actualListOfStablefordScore = new ArrayList<>(
                Arrays.asList(
                        new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2),
                        new StablefordScore(id, "271220221", "140", "3", "9", "4", "1", 200.00, "Left", 2)
                )
        );
        /** given - precondition setup */
        when(stablefordScoringRepository.findAll()).thenReturn(actualListOfStablefordScore);

        /** when - action or the behavior that we are going to test */
        List<StablefordScore> expectedResults = stablefordScoringService.getAllScores();

        /** then - verify the expected vs actual */
        assertThat(expectedResults).isNotNull();
        assertThat(expectedResults.size()).isEqualTo(2);
        verify(stablefordScoringRepository, times(1)).findAll();
        verifyNoInteractions(stablefordScoringRepository);
    }
}
