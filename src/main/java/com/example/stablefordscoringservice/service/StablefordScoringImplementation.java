package com.example.stablefordscoringservice.service;

import com.example.stablefordscoringservice.entiry.StablefordScore;
import com.example.stablefordscoringservice.repository.StablefordScoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StablefordScoringImplementation implements StablefordScoringService{

    @Autowired
    private StablefordScoringRepository stablefordScoringRepository;
    @Override
    public Iterable<StablefordScore> getAllScores() {
        StablefordScore stablefordScore = null;
        Iterable<StablefordScore> result = stablefordScoringRepository.findAll();
        return result;
    }
}
