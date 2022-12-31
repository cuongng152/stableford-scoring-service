package com.example.stablefordscoringservice.coursescore.service;

import com.example.stablefordscoringservice.StablefordScoringServiceApplication;
import com.example.stablefordscoringservice.entity.CourseScore;
import com.example.stablefordscoringservice.exceptions.CustomDataNotFoundException;
import com.example.stablefordscoringservice.exceptions.NullScoreException;
import com.example.stablefordscoringservice.repository.CourseScoreRepository;
import com.example.stablefordscoringservice.service.coursescore.CourseScoreImplementation;
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

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * This class needs Mockito and junit-jupiter to run the test
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = StablefordScoringServiceApplication.class)
public class CourseScoreServiceTest {
    @Mock
    private CourseScoreRepository repository;

    @InjectMocks
    private CourseScoreImplementation service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private UUID id = UUID.randomUUID();
    private String s = "2019-10-11T12:12:23.234Z";
    private Timestamp ts = Timestamp.from(Instant.parse(s));
    private CourseScore courseScore = new CourseScore(id, 90, "Waterford", ts, 20);

    private CourseScore newScore = new CourseScore(id, 93, "Waterford", ts, 19);

//    @DisplayName("JUnit test for returning list of stableford scores")
//    @Test
//    public void givenStablefordScoresList_whenGetAllStablefordScores_thenReturnStablefordScoreList() {
//        UUID id = UUID.randomUUID();
//        StablefordScore score = new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2);
//
//        /** given - precondition setup */
//        when(stablefordScoringRepository.findAll()).thenReturn(Arrays.asList(score));
//
//        /** when - action or the behavior that we are going to test */
//        List<StablefordScore> expectedResults = stablefordScoringService.getAllScores();
//
//        /** then - verify the expected vs actual */
//        assertThat(expectedResults).isNotNull();
//        assertThat(expectedResults.size()).isEqualTo(1);
//        assertEquals(Arrays.asList(score), expectedResults);
//        verify(stablefordScoringRepository, times(1)).findAll();
//    }

    @DisplayName("JUnit test for creating new course scores")
    @Test
    public void createNewCourseScore() {
        when(repository.save(courseScore)).thenReturn(courseScore);
        String code = service.addScore(courseScore);
        assertEquals(code, courseScore.getId().toString());
    }

    @DisplayName("JUnit test for find score by id")
    @Test
    public void findScoreById() {
        when(repository.findById(id)).thenReturn(Optional.of(courseScore));
        Optional<CourseScore> result = service.getScoreById(id.toString());
        assertEquals(Optional.of(courseScore), result);
        assertThat(result).isNotNull();
        verify(repository, times(1)).findById(id);
    }

    @DisplayName("Throw an exception when data returns null")
    @Test
    public void throwExceptionWhenDataReturnsNull() {
        when(repository.findById(id)).thenReturn(null);
        assertThrows(NullScoreException.class, () -> {
            service.getScoreById(id.toString());
        });
    }

    @DisplayName("Throw an exception when data not found")
    @Disabled
    @Test
    public void throwExceptionWhenDataIsNotFound() {
        when(repository.findAll()).thenReturn(null);
        assertThrows(CustomDataNotFoundException.class, () -> {
            service.getAllScores();
        });
    }

    @DisplayName("JUnit test for update score by id")
    @Test
    public void updateScoreById() {
        when(repository.findById(id)).thenReturn(Optional.of(courseScore));
        CourseScore result = service.updateScoreById(id.toString(), newScore);
        assertThat(result).isNotNull();
        assertEquals(Optional.ofNullable(result.getStroke()), Optional.of(93));
        assertEquals(Optional.ofNullable(result.getDailyHandicap()), Optional.of(19));
        verify(repository, times(1)).findById(id);
    }

    @DisplayName("Throw an exception when data returns null when updating")
    @Test
    public void throwExceptionWhenDataReturnsNullInUpdating() {
        when(repository.findById(id)).thenReturn(null);
        assertThrows(NullScoreException.class, () -> {
            service.updateScoreById(id.toString(), any(CourseScore.class));
        });
    }
}
