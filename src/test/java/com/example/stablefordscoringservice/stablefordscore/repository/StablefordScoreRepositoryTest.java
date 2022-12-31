package com.example.stablefordscoringservice.stablefordscore.repository;

import com.example.stablefordscoringservice.entity.StablefordScore;
import com.example.stablefordscoringservice.repository.StablefordScoringRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StablefordScoreRepositoryTest {

    @Autowired
    private StablefordScoringRepository stablefordScoringRepository;

    @Test
    public void shouldFindAllScores() {
        UUID id = UUID.randomUUID();
        StablefordScore score1 = new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2);
        StablefordScore newInsert = stablefordScoringRepository.save(score1);

        List<StablefordScore> expected = Arrays.asList(newInsert);

        List<StablefordScore> actual = stablefordScoringRepository.findAll();

        assertThat(actual).hasSize(1);
        assertEquals(actual, expected);
    }

    @Test
    public void shouldAddNewScore() {
        UUID id = UUID.randomUUID();
        StablefordScore newScore = new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2);
        stablefordScoringRepository.save(newScore);
        List<StablefordScore> scoreList = stablefordScoringRepository.findAll();
        assertEquals(scoreList.get(0).getCode(), "271220221");
    }

    @Test
    public void shouldGetScoreById() {
        UUID id = UUID.randomUUID();
        StablefordScore score = new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2);

        StablefordScore newInsert = stablefordScoringRepository.save(score);
        Optional<StablefordScore> actual = stablefordScoringRepository.findById(newInsert.getId());

        assertEquals(actual, Optional.of(newInsert));
    }

    @Test
    public void shouldUpdateScoreById() {
        UUID id = UUID.randomUUID();
        StablefordScore score = new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2);
        StablefordScore newInsert = stablefordScoringRepository.save(score);
        Optional<StablefordScore> result = stablefordScoringRepository.findById(newInsert.getId());
        result.get().setTeeOffLength(210.00);
        result.get().setTeeOffDirection("Miss Hit");

        StablefordScore updatedScore = stablefordScoringRepository.save(result.get());

        assertEquals(updatedScore.getTeeOffDirection(), "Miss Hit");
        assertEquals(updatedScore.getTeeOffLength(), Double.valueOf(210.00));
    }
}
