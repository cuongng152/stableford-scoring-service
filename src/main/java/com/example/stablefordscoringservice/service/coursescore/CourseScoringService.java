package com.example.stablefordscoringservice.service.coursescore;

import com.example.stablefordscoringservice.entity.CourseScore;
import com.example.stablefordscoringservice.entity.StablefordScore;

import java.util.List;
import java.util.Optional;

public interface CourseScoringService {
    List<CourseScore> getAllScores();
    String addScore(CourseScore score);
    Optional<CourseScore> getScoreById(String id);
    CourseScore updateScoreById(String id, CourseScore updatedScore);
    List<CourseScore> getAllCourseScoresByHoleCode(String holeCode);
}
