package com.example.stablefordscoringservice.service;

import com.example.stablefordscoringservice.entity.StablefordScore;

import java.util.List;
import java.util.Optional;

public interface StablefordScoringService {
    List<StablefordScore> getAllScores();
    String addScore(StablefordScore score);
    Optional<StablefordScore> getScoreById(String id);
    StablefordScore updateScoreById(String id, StablefordScore updatedScore);
}
