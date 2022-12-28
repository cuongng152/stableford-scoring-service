package com.example.stablefordscoringservice.repository;

import com.example.stablefordscoringservice.entiry.StablefordScore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StablefordScoreRepositoryTest {

    @Autowired
    private StablefordScoringRepository stablefordScoringRepository;

    @Test
    public void shouldFindAllScores() {
        Long id = 1L;
        StablefordScore score1 = new StablefordScore(id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2);
        StablefordScore score2 =  new StablefordScore(id, "271220221", "140", "3", "9", "4", "1", 200.00, "Left", 2);

        List<StablefordScore> expected = Arrays.asList(score1, score2);

        List<StablefordScore> actual = stablefordScoringRepository.findAll();

        assertThat(actual).hasSize(2);
        assertEquals(actual, expected);
    }
}
