package com.example.stablefordscoringservice.service;

import com.example.stablefordscoringservice.entiry.StablefordScore;

import java.util.List;

public interface StablefordScoringService {
    Iterable<StablefordScore> getAllScores();
}
