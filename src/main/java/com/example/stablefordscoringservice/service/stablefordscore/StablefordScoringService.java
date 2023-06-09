package com.example.stablefordscoringservice.service.stablefordscore;

import com.example.stablefordscoringservice.entity.StablefordScore;

import java.util.List;
import java.util.Optional;

public interface StablefordScoringService {
    List<StablefordScore> getAllScores();
    String addScore(StablefordScore score);
    Optional<StablefordScore> getScoreById(String id);
    StablefordScore updateScoreById(String id, StablefordScore updatedScore);
    List<StablefordScore> getAllScoresByHoleCode(String holeCode);
}
