package com.example.stablefordscoringservice.coursescore.repository;

import com.example.stablefordscoringservice.entity.CourseScore;
import com.example.stablefordscoringservice.repository.CourseScoreRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.util.Streamable;

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
    private CourseScore courseScore = new CourseScore(90, "Waterford", ts, 20,189.6, 37, "19012022Morning");

    @Test
    public void shouldFindAllScores() {
        courseScore.setId(String.valueOf(id));
        courseScoreRepository.save(courseScore);
        List<CourseScore> expected = Arrays.asList(courseScore);

        List<CourseScore> actual = Arrays.asList(courseScoreRepository.findAll().iterator().next());

        assertThat(actual).hasSize(1);
        assertEquals(actual, expected);
    }

    @Test
    public void shouldAddNewScore() {
        courseScore.setId(String.valueOf(id));
        courseScoreRepository.save(courseScore);
        List<CourseScore> scoreList = Streamable.of(courseScoreRepository.findAll()).toList();
        assertEquals(scoreList.get(0), courseScore);
    }

    @Test
    public void shouldGetScoreById() {
        courseScore.setId(String.valueOf(id));
        CourseScore newInsert = courseScoreRepository.save(courseScore);
        Optional<CourseScore> actual = courseScoreRepository.findById(newInsert.getId());

        assertEquals(actual, Optional.of(newInsert));
    }

    @Test
    public void shouldUpdateScoreById() {
        courseScore.setId(String.valueOf(id));
        courseScoreRepository.save(courseScore);
        Optional<CourseScore> result = courseScoreRepository.findById(courseScore.getId());
        result.get().setDailyHandicap(19);
        result.get().setStroke(93);

        CourseScore updatedScore = courseScoreRepository.save(result.get());

        assertEquals(Optional.ofNullable(updatedScore.getDailyHandicap()), Optional.of(19));
        assertEquals(Optional.ofNullable(updatedScore.getStroke()), Optional.of(93));
    }
}

