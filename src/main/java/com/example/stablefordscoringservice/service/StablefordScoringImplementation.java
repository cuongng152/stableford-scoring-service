package com.example.stablefordscoringservice.service;

import com.example.stablefordscoringservice.entity.StablefordScore;
import com.example.stablefordscoringservice.exceptions.CustomDataNotFoundException;
import com.example.stablefordscoringservice.exceptions.NullScoreException;
import com.example.stablefordscoringservice.repository.StablefordScoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StablefordScoringImplementation implements StablefordScoringService{

    @Autowired
    private StablefordScoringRepository stablefordScoringRepository;
    @Override
    public List<StablefordScore> getAllScores() {
        List<StablefordScore> result;
        try {
            result = stablefordScoringRepository.findAll();
        } catch (Exception e) {
            throw new CustomDataNotFoundException(e.getMessage());
        }
        return result;
    }

    @Override
    public String addScore(StablefordScore score) {
        StablefordScore newScore;
        newScore = stablefordScoringRepository.save(score);
        return newScore.getCode();
    }

    @Override
    public Optional<StablefordScore> getScoreById(String id) {
        Optional<StablefordScore> result = Optional.empty();
        Optional<StablefordScore> score = stablefordScoringRepository.findById(UUID.fromString(id));
        try {
            if (score.isPresent()) {
                result = score;
            }
        } catch (NullPointerException e) {
            throw new NullScoreException(e.getMessage());
        }

        return result;
    }

    @Override
    public StablefordScore updateScoreById(String id, StablefordScore updatedScore) {
        Optional<StablefordScore> score = stablefordScoringRepository.findById(UUID.fromString(id));
        try {
            if (score.isPresent()) {
                stablefordScoringRepository.save(updatedScore);
            }
        } catch (NullPointerException e) {
            throw new NullScoreException(e.getMessage());
        }
        return updatedScore;
    }


}
