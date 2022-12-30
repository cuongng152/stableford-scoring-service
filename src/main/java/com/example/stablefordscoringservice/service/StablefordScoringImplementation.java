package com.example.stablefordscoringservice.service;

import com.example.stablefordscoringservice.entiry.StablefordScore;
import com.example.stablefordscoringservice.repository.StablefordScoringRepository;
import com.fasterxml.jackson.databind.ser.std.IterableSerializer;
import jakarta.xml.ws.http.HTTPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StablefordScoringImplementation implements StablefordScoringService{

    @Autowired
    private StablefordScoringRepository stablefordScoringRepository;
    @Override
    public List<StablefordScore> getAllScores() {
        List<StablefordScore> result = new ArrayList<>();
        try {
            result = stablefordScoringRepository.findAll();
        } catch (HTTPException e) {
            System.out.println(e);
        }
        return result;
//        List<StablefordScore> retListOfScores = new ArrayList<>();
//        Long id = 1L;
//        retListOfScores.add(new StablefordScore(
//                id, "271220221", "475", "5", "2", "5", "3", 190.00, "Hit", 2
//        ));
//        retListOfScores.add(new StablefordScore(
//                id, "271220221", "140", "3", "9", "4", "1", 200.00, "Left", 2
//        ));
//        return retListOfScores;
    }

    @Override
    public String addScore(StablefordScore score) {
        StablefordScore newScore = new StablefordScore();
        newScore = stablefordScoringRepository.save(score);
        return newScore.getCode();
    }


}
