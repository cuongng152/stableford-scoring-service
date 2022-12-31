package com.example.stablefordscoringservice.service.coursescore;

import com.example.stablefordscoringservice.entity.CourseScore;
import com.example.stablefordscoringservice.exceptions.CustomDataNotFoundException;
import com.example.stablefordscoringservice.exceptions.NullScoreException;
import com.example.stablefordscoringservice.repository.CourseScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseScoreImplementation implements CourseScoringService {

    @Autowired
    private CourseScoreRepository courseScoreRepository;
    @Override
    public List<CourseScore> getAllScores() {
        Iterable<CourseScore> result;
        try {
            result = courseScoreRepository.findAll();
        } catch (Exception e) {
            throw new CustomDataNotFoundException(e.getMessage());
        }
        return Arrays.asList(result.iterator().next());
    }

    @Override
    public String addScore(CourseScore score) {
        CourseScore result = courseScoreRepository.save(score);
        return result.getId().toString();
    }

    @Override
    public Optional<CourseScore> getScoreById(String id) {
        Optional<CourseScore> result = Optional.empty();
        Optional<CourseScore> score = courseScoreRepository.findById(UUID.fromString(id));
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
