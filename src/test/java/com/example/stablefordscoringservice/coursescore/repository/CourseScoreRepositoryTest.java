package com.example.stablefordscoringservice.coursescore.repository;

import com.example.stablefordscoringservice.entity.CourseScore;
import com.example.stablefordscoringservice.repository.CourseScoreRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseScoreRepositoryTest {

    @Autowired
    private CourseScoreRepository courseScoreRepository;

    private UUID id = UUID.randomUUID();
    private String s = "2019-10-11T12:12:23.234Z";
    private Timestamp ts = Timestamp.from(Instant.parse(s));
    private CourseScore courseScore = new CourseScore(id, 90, "Waterford", ts, 20);

    private CourseScore newInsert = courseScoreRepository.save(courseScore);

    private CourseScore newCourseScore = courseScoreRepository.save(courseScore);

    @Test
    public void shouldFindAllScores() {
        List<CourseScore> expected = Arrays.asList(newInsert);

        List<CourseScore> actual = Arrays.asList(courseScoreRepository.findAll().iterator().next());

        assertThat(actual).hasSize(1);
        assertEquals(actual, expected);
    }

    @Test
    @Disabled
    public void shouldAddNewScore() {
        List<CourseScore> scoreList = Arrays.asList(courseScoreRepository.findAll().iterator().next());
        assertEquals(scoreList.get(0), newCourseScore);
    }

    @Test
    public void shouldGetScoreById() {
        CourseScore newInsert = courseScoreRepository.save(courseScore);
        Optional<CourseScore> actual = courseScoreRepository.findById(newInsert.getId());

        assertEquals(actual, Optional.of(newInsert));
    }

    @Test
    @Disabled
    public void shouldUpdateScoreById() {
        Optional<CourseScore> result = courseScoreRepository.findById(newInsert.getId());
        result.get().setDailyHandicap(19);
        result.get().setStroke(93);

        CourseScore updatedScore = courseScoreRepository.save(result.get());

        assertEquals(Optional.ofNullable(updatedScore.getDailyHandicap()), Optional.of(19));
        assertEquals(Optional.ofNullable(updatedScore.getStroke()), Optional.of(93));
    }
}

