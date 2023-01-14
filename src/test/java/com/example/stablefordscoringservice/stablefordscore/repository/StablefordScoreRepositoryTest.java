package com.example.stablefordscoringservice.stablefordscore.repository;

import com.example.stablefordscoringservice.entity.HoleAnalysis;
import com.example.stablefordscoringservice.entity.StablefordScore;
import com.example.stablefordscoringservice.repository.StablefordScoringRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(SpringRunner.class)
@DataJpaTest
public class StablefordScoreRepositoryTest {

    @Autowired
    private StablefordScoringRepository stablefordScoringRepository;
    UUID id = UUID.randomUUID();

    UUID id2 = UUID.randomUUID();
    private final StablefordScore newScore = new StablefordScore("271220221", "475", 5, 2, 5, 3);
    private final HoleAnalysis newAnalysis = new HoleAnalysis(210.00, "Hit", 2);

    @Test
    public void shouldFindAllScores() {
        newScore.setId(String.valueOf(id));
        newAnalysis.setId(String.valueOf(id2));
        newScore.setHoleAnalysis(newAnalysis);
        StablefordScore newInsert = stablefordScoringRepository.save(newScore);

        List<StablefordScore> expected = Arrays.asList(newInsert);

        List<StablefordScore> actual = stablefordScoringRepository.findAll();

        assertThat(actual).hasSize(1);
        assertEquals(actual, expected);
        assertEquals(Optional.ofNullable(actual.get(0).getHoleAnalysis().getPutt()), Optional.of(2));
    }

    @Test
    public void shouldAddNewScore() {
        newScore.setId(String.valueOf(id));
        newAnalysis.setId(String.valueOf(id2));
        newScore.setHoleAnalysis(newAnalysis);
        stablefordScoringRepository.save(newScore);
        List<StablefordScore> scoreList = stablefordScoringRepository.findAll();
        assertEquals(scoreList.get(0).getHoleCode(), "271220221");
        assertEquals(Optional.ofNullable(scoreList.get(0).getHoleAnalysis().getPutt()), Optional.of(2));
    }

    @Test
    public void shouldGetScoreById() {
        newScore.setId(String.valueOf(id));
        newAnalysis.setId(String.valueOf(id2));
        newScore.setHoleAnalysis(newAnalysis);
        StablefordScore newInsert = stablefordScoringRepository.save(newScore);
        Optional<StablefordScore> actual = stablefordScoringRepository.findById(newInsert.getId());

        assertEquals(actual, Optional.of(newInsert));
        assertEquals(Optional.of(actual.get().getHoleAnalysis().getPutt()), Optional.of(2));
    }

    @Test
    public void shouldUpdateScoreById() {
        newScore.setId(String.valueOf(id));
        newAnalysis.setId(String.valueOf(id2));
        newScore.setHoleAnalysis(newAnalysis);
        StablefordScore newInsert = stablefordScoringRepository.save(newScore);
        Optional<StablefordScore> result = stablefordScoringRepository.findById(newInsert.getId());
        result.get().setLength(String.valueOf(495));
        result.get().setPar(Integer.valueOf(String.valueOf(5)));

        StablefordScore updatedScore = stablefordScoringRepository.save(result.get());

        assertEquals(updatedScore.getLength(), String.valueOf(495));
        assertEquals(updatedScore.getPar(), 5);
        assertEquals(updatedScore.getHoleAnalysis().getTeeOffDirection(), "Hit");
    }
}
