package com.example.stablefordscoringservice.service.coursescore;

import com.example.stablefordscoringservice.entity.CourseScore;
import com.example.stablefordscoringservice.exceptions.CustomDataNotFoundException;
import com.example.stablefordscoringservice.exceptions.DataExistException;
import com.example.stablefordscoringservice.exceptions.NullScoreException;
import com.example.stablefordscoringservice.exceptions.ServerErrorException;
import com.example.stablefordscoringservice.repository.CourseScoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseScoreImplementation implements CourseScoringService {

    @Autowired
    private CourseScoreRepository courseScoreRepository;

    private Logger logger = LoggerFactory.getLogger(CourseScoreImplementation.class);

    @Override
    public List<CourseScore> getAllScores() {
        Iterable<CourseScore> result = courseScoreRepository.findAll();
        List<CourseScore> retList = new ArrayList<>();
        if (((ArrayList) result).isEmpty() ) {
            throw new CustomDataNotFoundException("No course scores data found. Please contact us for more details.");
        }
        retList = Streamable.of(result).toList();
        return retList;
    }

    @Override
    public String addScore(CourseScore score) {
        CourseScore result;
        Optional<CourseScore> existingScore = courseScoreRepository.findById(score.getId());
        if (existingScore.isPresent()) {
            logger.trace("Score with ID: " + score.getId() + "is already exist.");
            throw new DataExistException("Score with ID: " + score.getId() + "is already exist.");
        } else {
             result = courseScoreRepository.save(score);
        }
        return result.getId().toString();
    }

    @Override
    public Optional<CourseScore> getScoreById(String id) {
        Optional<CourseScore> result;
        try {
            Optional<CourseScore> score = courseScoreRepository.findById(UUID.fromString(id));
            if (score.isPresent()) {
                result = score;
            } else {
                throw new CustomDataNotFoundException("Unable to find a course score by ID: " + id + ". Please contact us for more details.");
            }
        } catch (IllegalArgumentException e) {
            logger.trace(String.valueOf(e.getStackTrace()));
            throw new ServerErrorException(e.getMessage());
        }
        return result;
    }

    @Override
    public CourseScore updateScoreById(String id, CourseScore updatedScore) {
        Optional<CourseScore> score = courseScoreRepository.findById(UUID.fromString(id));
        try {
            if (score.isPresent()) {
                courseScoreRepository.save(updatedScore);
            }
        } catch (NullPointerException e) {
            throw new NullScoreException(e.getMessage());
        }
        return updatedScore;
    }
}
