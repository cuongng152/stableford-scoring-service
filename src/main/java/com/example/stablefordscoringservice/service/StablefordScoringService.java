package com.example.stablefordscoringservice.service;

import com.example.stablefordscoringservice.entiry.StablefordScore;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StablefordScoringService {
    List<StablefordScore> getAllScores();
    String addScore(StablefordScore score);
    Optional<StablefordScore> getScoreById(UUID id);
    StablefordScore updateScoreById(String id, StablefordScore updatedScore);
}
