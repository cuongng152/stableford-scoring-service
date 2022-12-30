package com.example.stablefordscoringservice.service;

import com.example.stablefordscoringservice.entiry.StablefordScore;
import com.example.stablefordscoringservice.repository.StablefordScoringRepository;
import com.fasterxml.jackson.databind.ser.std.IterableSerializer;
import jakarta.xml.ws.http.HTTPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class StablefordScoringImplementation implements StablefordScoringService{

    @Autowired
    private StablefordScoringRepository stablefordScoringRepository;
    @Override
    public List<StablefordScore> getAllScores() {
        List<StablefordScore> result = new ArrayList<>();
        result = stablefordScoringRepository.findAll();
        return result;
    }

    @Override
    public String addScore(StablefordScore score) {
        StablefordScore newScore = new StablefordScore();
        newScore = stablefordScoringRepository.save(score);
        return newScore.getCode();
    }

    @Override
    public Optional<StablefordScore> getScoreById(UUID id) {
        Optional<StablefordScore> result = null;
        Optional<StablefordScore> score = stablefordScoringRepository.findById(id);
        if (score.isPresent()) {
             result = score;
        }
        return result;
    }

    @Override
    public StablefordScore updateScoreById(String id, StablefordScore updatedScore) {
        Optional<StablefordScore> score = stablefordScoringRepository.findById(UUID.fromString(id));
        if (score.isPresent()) {
            stablefordScoringRepository.save(updatedScore);
        }
        return updatedScore;
    }


}
