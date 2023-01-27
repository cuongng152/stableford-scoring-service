package com.example.stablefordscoringservice.coursescore.service;

import com.example.stablefordscoringservice.StablefordScoringServiceApplication;
import com.example.stablefordscoringservice.entity.CourseScore;
import com.example.stablefordscoringservice.exceptions.CustomDataNotFoundException;
import com.example.stablefordscoringservice.exceptions.ServerErrorException;
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

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


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
    private CourseScore courseScore = new CourseScore(90, "Waterford", ts, 20, 189.6, 37, "19012022Morning");

    private CourseScore newScore = new CourseScore(93, "Waterford", ts, 19, 189.6, 37, "19012022Morning");

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
        when(repository.findById(String.valueOf(id))).thenReturn(Optional.of(courseScore));
        Optional<CourseScore> result = service.getScoreById(id.toString());
        assertEquals(Optional.of(courseScore), result);
        assertThat(result).isNotNull();
        verify(repository, times(1)).findById(String.valueOf(id));
    }

    @DisplayName("Throw an exception when data returns null")
    @Test
    public void throwExceptionWhenDataReturnsNull() {
        when(repository.findById(String.valueOf(id))).thenReturn(null);
        assertThrows(NullPointerException.class, () -> {
            service.getScoreById(id.toString());
        });
    }

    @DisplayName("JUnit test for update score by id")
    @Test
    @Disabled
    public void updateScoreById() {
        courseScore.setId(String.valueOf(id));
        when(repository.findById(String.valueOf(id))).thenReturn(Optional.of(courseScore));
        CourseScore result = service.updateScoreById(id.toString(), newScore);
        System.out.println(result);
        assertThat(result).isNotNull();
        assertEquals(Optional.ofNullable(result.getStroke()), Optional.of(93));
        assertEquals(Optional.ofNullable(result.getDailyHandicap()), Optional.of(19));
        verify(repository, times(1)).findById(String.valueOf(id));
    }

    @DisplayName("Throw an exception when data returns null when updating")
    @Test
    public void throwExceptionWhenDataReturnsNullInUpdating() {
        when(repository.findById(String.valueOf(id))).thenReturn(Optional.empty());
        assertThrows(CustomDataNotFoundException.class, () -> {
            service.updateScoreById(id.toString(), courseScore);
        });
    }

    @DisplayName("Throw an exception if unable to find course score by id in case repository failed")
    @Test
    public void throwExceptionIfDataNotFoundByIdAndRepositoryFailed() {
        assertThrows(CustomDataNotFoundException.class, () -> {
            service.getScoreById("1");
        });
    }

    @DisplayName("Throw an exception if unable to find course score by id")
    @Test
    public void throwExceptionIfDataNotFoundById() {
        when(repository.findById(String.valueOf(id))).thenReturn(Optional.empty());
        assertThrows(CustomDataNotFoundException.class, () -> {
            service.getScoreById(String.valueOf(id));
        });
    }
}
