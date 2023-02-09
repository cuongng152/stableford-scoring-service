package com.example.stablefordscoringservice.service.stablefordscore;

import com.example.stablefordscoringservice.entity.StablefordScore;
import com.example.stablefordscoringservice.exceptions.CustomDataNotFoundException;
import com.example.stablefordscoringservice.exceptions.NullScoreException;
import com.example.stablefordscoringservice.exceptions.ServerErrorException;
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
        } catch (ServerErrorException e) {
            throw new ServerErrorException(e.getMessage());
        }
        return result;
    }

    @Override
    public String addScore(StablefordScore score) {
        StablefordScore newScore;
        score.setId(String.valueOf(UUID.randomUUID()));
        score.getHoleAnalysis().setId(String.valueOf(UUID.randomUUID()));
        try {
            newScore = stablefordScoringRepository.save(score);
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
        return newScore.getId();
    }

    @Override
    public Optional<StablefordScore> getScoreById(String id) {
        Optional<StablefordScore> result;
        Optional<StablefordScore> score;
        try {
            score = stablefordScoringRepository.findById(id);
            if (score.isPresent()) {
                result = score;
            } else {
                throw new CustomDataNotFoundException("Unable to find a course score by ID: " + id + ". Please contact us for more details.");
            }
        } catch (NullPointerException e) {
            throw new NullScoreException(e.getMessage());
        }

        return result;
    }

    @Override
    public StablefordScore updateScoreById(String id, StablefordScore updatedScore) {
        Optional<StablefordScore> score;
        try {
            score = stablefordScoringRepository.findById(id);
            if (score.isPresent()) {
                updatedScore.setId(id);
                updatedScore.getHoleAnalysis().setId(score.get().getHoleAnalysis().getId());
                stablefordScoringRepository.saveAndFlush(updatedScore);
            } else {
                throw new CustomDataNotFoundException("Unable to find a course score by ID: " + id + ". Please contact us for more details.");
            }
        } catch (ServerErrorException e) {
            throw new ServerErrorException(e.getMessage());
        }
        return updatedScore;
    }

    @Override
    public List<StablefordScore> getAllScoresByHoleCode(String holeCode) {
        List<StablefordScore> result;
        try {
            result = stablefordScoringRepository.findStablefordScoresByHoleCode(holeCode);
        } catch (ServerErrorException e) {
            throw new ServerErrorException(e.getMessage());
        }
        return result;
    }
}
