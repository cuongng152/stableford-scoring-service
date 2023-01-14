package com.example.stablefordscoringservice.service.coursescore;

import com.example.stablefordscoringservice.entity.CourseScore;
import com.example.stablefordscoringservice.exceptions.CustomDataNotFoundException;
import com.example.stablefordscoringservice.exceptions.NullScoreException;
import com.example.stablefordscoringservice.exceptions.ServerErrorException;
import com.example.stablefordscoringservice.repository.CourseScoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseScoreImplementation implements CourseScoringService {

    @Autowired
    private CourseScoreRepository courseScoreRepository;

    private Logger logger = LoggerFactory.getLogger(CourseScoreImplementation.class);

    @Override
    public List<CourseScore> getAllScores() {
        Iterable<CourseScore> result = courseScoreRepository.findAll();
        List<CourseScore> retList;
        if (((ArrayList) result).isEmpty()) {
            throw new CustomDataNotFoundException("No course scores data found. Please contact us for more details.");
        }
        retList = Streamable.of(result).toList();
        return retList;
    }

    @Override
    public String addScore(CourseScore score) {
        CourseScore result;
        score.setId(UUID.randomUUID().toString());
        result = courseScoreRepository.save(score);
        return result.getId();
    }

    @Override
    public Optional<CourseScore> getScoreById(String id) {
        Optional<CourseScore> result;
        try {
            Optional<CourseScore> score = courseScoreRepository.findById(id);
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
        Optional<CourseScore> score;
        CourseScore update;
        updatedScore.setId(id);
        try {
            score = courseScoreRepository.findById(id);
            if (score.isPresent()) {
                update = courseScoreRepository.save(updatedScore);
            } else {
                throw new CustomDataNotFoundException("Unable to find a course score by ID: " + id + ". Please contact us for more details.");
            }
        } catch (ServerErrorException e) {
            throw new NullScoreException(e.getMessage());
        }
        return update;
    }
}
